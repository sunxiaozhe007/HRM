package dao;

import dao.provider.NoticeDynaProvider;
import domain.Notice;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

import static util.HrmConstants.NOTICETABLE;

public interface NoticeDao {

    // ��̬��ѯ
    @SelectProvider(type = NoticeDynaProvider.class,method = "selectWithParam")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "CREATE_DATE",property = "createDate",javaType = java.util.Date.class),
            @Result(column = "USER_ID",property = "user",one = @One(select = "dao.UserDao.selectById",fetchType = FetchType.EAGER))
    })
    List<Notice> selectByPage(Map<String,Object> params);

    @SelectProvider(type = NoticeDynaProvider.class,method = "count")
    Integer count(Map<String,Object> params);

    @Select("select * from " + NOTICETABLE + " where id = #{id}")
    Notice selectById(int id);

    //����idɾ������
    @Delete(" delete from " + NOTICETABLE + " where id = #{id} ")
    void deleteById(Integer id);

    //��̬���빫��
    @SelectProvider(type = NoticeDynaProvider.class,method = "insertNotice")
    void save(Notice notice);

    //��̬�޸Ĺ���
    @SelectProvider(type = NoticeDynaProvider.class,method = "updateNotice")
    void update(Notice notice);
}
