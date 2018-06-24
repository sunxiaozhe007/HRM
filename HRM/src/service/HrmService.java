package service;

import domain.*;
import util.PageModel;

import java.util.List;

public interface HrmService {
    /**
     * �û���½
     */
    User login(String loginname,String password);

    /**
     * ����id��ѯ�û�
     */
    User findUserById(Integer id);

    /**
     * ��������û�
     */
    List<User> findUser(User user, PageModel pageModel);

    /**
     * ����idɾ���û�
     */
    void removeUserById(Integer id);

    /**
     * �޸����û�
     */
    void modifyUser(User user);

    /**
     * ����û�
     */
    void addUser(User user);

    /**
     * ��ȡ����Ա��
     */
    List<Employee> findEmployee(Employee employee,PageModel pageModel);

    /**
     * ����idɾ���û�
     */
    void removeEmployeeById(Integer id);

    /**
     * ����id��ѯԱ��
     */
    Employee findEmployeeById(Integer id);


    /**
     * ���Ա��
     */
    void addEmployee(Employee employee);

    /**
     * �޸�Ա��
     */
    void modifyEmployee(Employee employee);

    /**
     * ��ȡ���в���,��ҳ��ѯ
     */
    List<Dept> findDept(Dept dept,PageModel pageModel);

    /**
     * ��ȡ���в���
     */
    List<Dept> findAllDept();

    /**
     * ����idɾ������
     */
    public void removeDeptById(Integer id);

    /**
     * ��Ӳ���
     */
    void addDept(Dept dept);

    /**
     * ����id��ѯ����
     */
    Dept findDeptById(Integer id);

    /**
     * �޸Ĳ���
     */
    void modifyDept(Dept dept);

    /**
     * ��ȡ����ְλ
     */
    List<Job> findAllJob();

    /**
     * ��ȡ����ְλ����ҳ��ѯ
     */
    List<Job> findJob(Job job,PageModel pageModel);

    /**
     * ����idɾ��ְλ
     */
    public void removeJobById(Integer id);

    /**
     * ���ְλ
     */
    void addJob(Job job);

    /**
     * ����id��ѯְλ
     */
    Job findJobById(Integer id);

    /**
     * �޸�ְλ
     */
    void modifyJob(Job job);

    /**
     * ��ȡ���й���
     */
    List<Notice> findNotice(Notice notice,PageModel pageModel);

    /**
     * ����id��ѯ����
     */
    Notice findNoticeById(Integer id);

    /**
     * ����idɾ������
     */
    public void removeNoticeById(Integer id);

    /**
     * ��ӹ���
     */
    void addNotice(Notice notice);

    /**
     * �޸Ĺ���
     */
    void modifyNotice(Notice notice);

    /**
     * ��ȡ�����ĵ�
     */
    List<Document> findDocument(Document document,PageModel pageModel);

    /**
     * ����ĵ�
     */
    void addDocument(Document document);

    /**
     * ����id��ѯ�ĵ�
     */
    Document findDocumentById(Integer id);

    /**
     * ����idɾ���ĵ�
     */
   public void removeDocumentById(Integer id);

    /**
     * �޸��ĵ�
     */
    void modifyDocument(Document document);
}
