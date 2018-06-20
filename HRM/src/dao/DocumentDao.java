package dao;

import dao.provider.DocumentDynaProvider;
import domain.Document;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static util.HrmConstants.DEPTTABLE;
import static util.HrmConstants.DOCUMENTTABLE;

public interface DocumentDao {
    //��̬��ѯ
    @SelectProvider(type = DocumentDynaProvider.class,method = "selectWithParam")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "CREATE_DATE",property = "createDate",javaType = java.util.Date.class),
            @Result(column = "USER_ID",property = "user",one = @One(select = "dao.UserDao.selectById",fetchType = FetchType.EAGER))
    })
    List<Document> selectByPage(Map<String,Object> params);
    @SelectProvider(type = DocumentDynaProvider.class,method = "count")
    Integer count(Map<String,Object> params);

    //��̬�����ĵ�
    @SelectProvider(type = DocumentDynaProvider.class,method = "insertDocument")
    void save(Document document);

    @Select("select * from " + DOCUMENTTABLE +" where id = #{id}")
    Document selectById(int id);

    //����idɾ���ĵ�
    @Delete(" delete from " + DOCUMENTTABLE +" where id = #{id}")
    void deleteById(Integer id);

    //��̬�޸��ĵ�
    @SelectProvider(type = DocumentDynaProvider.class,method = "updateDocument")
    void update(Document document);
}
