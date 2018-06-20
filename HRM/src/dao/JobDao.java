package dao;

import dao.provider.JobDynaProvider;
import domain.Job;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static util.HrmConstants.JOBTABLE;

public interface JobDao {
    @Select("select * from "+ JOBTABLE +" where id = #{id}")
    Job selectById(int id);
    @Select("select * from " +JOBTABLE +" ")
    List<Job> selectAllJob();

    //动态查询
    @SelectProvider(type = JobDynaProvider.class,method = "selectWithParam")
    List<Job> selectByPage(Map<String,Object> params);
    @SelectProvider(type = JobDynaProvider.class,method = "count")
    Integer count(Map<String,Object> params);

    //根据id删除部门
    @Delete(" delete from " + JOBTABLE +" where id = #{id}")
    void deleteById(Integer id);

    //动态插入部门
    @SelectProvider(type = JobDynaProvider.class,method = "insertJob")
    void save(Job job);

    //动态修改用户
    @SelectProvider(type = JobDynaProvider.class,method = "updateJob")
    void update(Job job);
}
