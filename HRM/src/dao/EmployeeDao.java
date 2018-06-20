package dao;

import dao.provider.EmployeeDynaProvider;
import domain.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static util.HrmConstants.EMPLOYYEETABLE;

public interface EmployeeDao {

    //根据参数查询员工总数
    @SelectProvider(type = EmployeeDynaProvider.class,method = "count")
    Integer count(Map<String,Object> params);
    //根据参数动态查询员工
    @SelectProvider(type = EmployeeDynaProvider.class,method = "selectWithParam")
    @Results(value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "CARD_ID", property = "cardId"),
            @Result(column = "POST_CODE", property = "postCode"),
            @Result(column = "QQ_NUM", property = "qqNum"),
            @Result(column = "BIRTHDAY", property = "birthday" , javaType = java.util.Date.class),
            @Result(column = "CREATE_DATE" , property = "createDate" , javaType = java.util.Date.class),
            @Result(column = "DEPT_ID" , property = "dept" , one = @One(select = "dao.DeptDao.selectById" , fetchType = FetchType.EAGER)),
            @Result(column = "JOB_ID" , property = "job" , one = @One(select = "dao.JobDao.selectById" , fetchType = FetchType.EAGER)),
    })
    List<Employee> selectByPage(Map<String,Object> params);

    //动态插入员工
    @SelectProvider(type = EmployeeDynaProvider.class,method = "insertEmployee")
    void save (Employee employee);

    //根据id删除与员工
    @Delete(" delete from " + EMPLOYYEETABLE + " where id = #{id} ")
    void deleteById(Integer id);

    //根据id查询员工
    @Select("select * from " + EMPLOYYEETABLE + " where ID = #{id}")
    @Results({
            @Result(id = true, column = "id" , property = "id"),
            @Result(column = "CARD_ID",property = "cardId"),
            @Result(column = "POST_CODE" , property = "postCode"),
            @Result(column = "QQ_NUM" , property = "qqNum"),
            @Result(column = "BIRTHDAY" , property = "birthday" , javaType = java.util.Date.class),
            @Result(column = "CREATE_DATE" , property = "createDate", javaType = java.util.Date.class),
            @Result(column = "DEPT_ID" , property = "dept" , one = @One(select = "dao.DeptDao.selectById", fetchType = FetchType.EAGER)),
            @Result(column = "JOB_ID" , property = "job", one = @One(select = "dao.JobDao.selectById", fetchType = FetchType.EAGER))
    })
    Employee selectById(Integer id);

    //动态修改该员工
    @SelectProvider(type = EmployeeDynaProvider.class,method = "updateEmployee")
    void update(Employee employee);
}
