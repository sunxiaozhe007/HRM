package dao.provider;

import domain.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static util.HrmConstants.USERTABLE;

public class UserDynaSqlProvider {
    //分页动态查询
    public String selectWithParam(Map<String,Object> params){
        String sql = new SQL(){
            {
                SELECT ("*");
                FROM(USERTABLE);
                if (params.get("user")!=null){
                    User user = (User) params.get("user");

                    if (user.getUsername()!=null && !user.getUsername().equals("")){
                        WHERE("username LIKE CONCAT ('%',#{user.username},'%')");
                    }
                    if (user.getUserstatus()!=null && !user.getUserstatus().equals("")){
                        WHERE("userstatus LIKE CONCAT ('%',#{user.userstatus},'%')");
                    }
                }

            }
        }.toString();
        if (params.get("pageModel") != null){
            sql +=" limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }


    //动态查询总数量
    public String count(Map<String,Object> params){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM(USERTABLE);
                if (params.get("user") != null){
                    User user = (User) params.get("user");
                    if (user.getUserstatus() != null && !user.getUserstatus().equals("")){
                        WHERE("username LIKE CONCAT ('%',#{user.username},'%')");
                    }
                    if (user.getUserstatus() != null && !user.getUserstatus().equals("")){
                        WHERE("userstatus LIKE CONCAT ('%',#{user.userstatus},'%')");
                    }
                }
            }
        }.toString();
    }



    //动态插入
    public String insertUser(User user){
        return new SQL(){
            {
                INSERT_INTO(USERTABLE);
                if (user.getUsername() != null && !user.getUsername().equals("")){
                    VALUES("username","#{username}");
                }
                if (user.getUserstatus() != null && !user.getUserstatus().equals("")){
                    VALUES("userstatus","#{userstatus}");
                }
                if (user.getLoginname() != null && !user.getLoginname().equals("")){
                    VALUES("loginname","#{loginname}");
                }
                if (user.getPassword() != null && !user.getPassword().equals("")){
                    VALUES("password","#{password}");
                }
            }
        }.toString();
    }


    //动态更新
    public String updateUser(User user){
        return new SQL(){
            {
                UPDATE(USERTABLE);
                if (user.getUsername() != null){
                    SET("username = #{username}");
                }
                if (user.getLoginname() != null){
                    SET("loginname = #{loginname}");
                }
                if (user.getPassword() != null){
                    SET("password = #{password}");
                }
                if (user.getUserstatus() != null){
                    SET("userstatus = #{userstatus}");
                }
                if (user.getCreateDate() != null){
                    SET("create_date = #{createDate}");
                }
                WHERE(" id = #{id} ");
            }
        }.toString();
    }
}
