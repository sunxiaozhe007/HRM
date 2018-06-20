package dao.provider;

import domain.Job;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static util.HrmConstants.JOBTABLE;

public class JobDynaProvider {
    //��ҳ��̬��ѯ
    public String selectWithParam(Map<String,Object> params){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM(JOBTABLE);
                if (params.get("job") != null){
                    Job job = (Job) params.get("job");
                    if (job.getName() != null && !job.getName().equals("")){
                        WHERE(" name LIKE CONCAT ('%',#{job.name},'%')");
                    }
                }
            }
        }.toString();
        if (params.get("pageModel") != null){
            sql +=" limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    //��̬��ѯ������
    public String count(Map<String,Object> params){
        return new SQL(){
            {
                SELECT("count(*)");
                FROM(JOBTABLE);
                if (params.get("job") != null){
                    Job job = (Job) params.get("job");
                    if (job.getName() != null && !job.getName().equals("")){
                        WHERE("name LIKE CONCAT('%',#{job.name},'%')");
                    }
                }
            }
        }.toString();
    }

    //��̬����
    public String insertJob(Job job){
        return new SQL(){
            {
                INSERT_INTO(JOBTABLE);
                if (job.getName() != null && !job.getName().equals("")){
                    VALUES("name","#{name}");
                }
                if (job.getRemark() != null && !job.getRemark().equals("")){
                    VALUES("remark","#{remark}");
                }
            }
        }.toString();
    }

    //��̬����
    public String updateJob(Job job){
        return new SQL(){
            {
                UPDATE(JOBTABLE);
                if (job.getName() != null){
                    SET(" name = #{name} ");
                }
                if (job.getRemark() != null){
                    SET(" remark = #{remark} ");
                }
                WHERE("id = #{id} ");
            }
        }.toString();
    }
}
