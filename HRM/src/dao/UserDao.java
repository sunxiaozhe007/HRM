package dao;

import dao.provider.UserDynaSqlProvider;
import domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static util.HrmConstants.USERTABLE;

public interface UserDao {
    //���ݵ�½���������ѯԱ��
    @Select("select * from " + USERTABLE + " where loginname = #{loginname} and password = #{password}")
    User selectByLoginnameAndPassword(@Param("loginname")String loginname,@Param("password")String password);

    //����id��ѯ�û�
    @Select("select * from " + USERTABLE + " where id = #{id}")
    User selectById(Integer id);

    //����idɾ���û�
    @Delete("delete from " + USERTABLE + " where id = #{id}")
    void deleteById(Integer id);

    //��̬�޸ĸ��û�
    @SelectProvider(type = UserDynaSqlProvider.class,method = "updateUser")
    void update(User user);

    //��̬��ѯ
    @SelectProvider(type = UserDynaSqlProvider.class, method = "selectWithParam")
    List<User> selectByPage(Map<String,Object> params);

    //���ݲ�����ѯ�û�����
    @SelectProvider(type = UserDynaSqlProvider.class,method = "count")
    Integer count(Map<String,Object> params);

    //��̬�����û�
    @SelectProvider(type = UserDynaSqlProvider.class,method = "insertUser")
    void save(User user);


}
