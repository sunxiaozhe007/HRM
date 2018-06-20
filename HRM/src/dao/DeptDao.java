package dao;

import dao.provider.DeptDynaSqlProvider;
import domain.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

import static util.HrmConstants.DEPTTABLE;

public interface DeptDao {

    //��̬��ѯ
    @SelectProvider(type = DeptDynaSqlProvider.class,method = "selectWithParam")
    List<Dept> selectByPage(Map<String,Object> params);
    @SelectProvider(type = DeptDynaSqlProvider.class,method = "count")
    Integer count(Map<String,Object> params);
    @Select(" select * from " + DEPTTABLE +"")
    List<Dept> selectAllDept();
    @Select("select * from " + DEPTTABLE +" where id = #{id}")
    Dept selectById(int id);

    //����idɾ������
    @Delete("delete from "+ DEPTTABLE +" where id = #{id}")
    void deleteById(Integer id);

    //��̬���벿��
    @SelectProvider(type = DeptDynaSqlProvider.class,method = "insertDept")
    void save(Dept dept);

    //��̬�޸��û�
    @SelectProvider(type = DeptDynaSqlProvider.class,method = "updateDept")
    void update(Dept dept);
}
