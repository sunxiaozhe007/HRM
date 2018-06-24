package service;

import dao.*;
import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import util.PageModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �����ӿ�ʵ����
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService {
    /**
     *�Զ�ע��־ò�dao����
     */
    @Autowired
    private UserDao userDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private JobDao jobDao;
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private DocumentDao documentDao;


    /**
     *�û�����ӿ�ʵ��
     */
    //login����ʵ��
    @Transactional(readOnly = true)
    @Override
    public User login(String loginname, String password) {
        System.out.println("HrmServiceImpl login -->>");
        return userDao.selectByLoginnameAndPassword(loginname,password);
    }


    //findUserById����ʵ��
    @Transactional(readOnly = true)
    @Override
    public User findUserById(Integer id) {
        return userDao.selectById(id);
    }

    //findUser����ʵ��
    @Transactional(readOnly = true)
    @Override
    public List<User> findUser(User user, PageModel pageModel) {
        //��ǰ��Ҫ��ҳ������������
        Map<String,Object> params = new HashMap<>();
        params.put("user",user);
        int recordCount = userDao.count(params);
        System.out.println("recordCount -->>" + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0){
            //��ʼ��ҳ��ѯ���ݣ���ѯ�ڼ�ҳ������
            params.put("pageModel",pageModel);
        }
        List<User> users = userDao.selectByPage(params);
        return users;
    }


    //removeUserById����ʵ��
    @Override
    public void removeUserById(Integer id) {
        userDao.deleteById(id);
    }


    //modifyUser����ʵ��
    @Override
    public void modifyUser(User user) {
        userDao.update(user);
    }


    //addUser����ʵ��
    @Override
    public void addUser(User user) {
        userDao.save(user);
    }


    /**
     *Ա������ӿ�ʵ��
     */

    //findEmployee����ʵ��
    @Transactional(readOnly = true)
    @Override
    public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
        //��ǰ��Ҫ��ҳ������������
        Map<String,Object> params = new HashMap<>();
        params.put("employee",employee);
        int recordCount = employeeDao.count(params);
        System.out.println("recordCount -->> "+ recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0){
            //��ʼ��ҳ��ѯ����:��ѯ�ڼ�ҳ������
            params.put("pageModel",pageModel);
        }
        List<Employee> employees = employeeDao.selectByPage(params);
        return employees;
    }


    //removeEmployeeById����ʵ��
    @Override
    public void removeEmployeeById(Integer id) {
        employeeDao.deleteById(id);
    }


    //findEmployeeById����ʵ��
    @Transactional(readOnly = true)
    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeDao.selectById(id);
    }


    //addEmployee����ʵ��
    @Override
    public void addEmployee(Employee employee) {
        employeeDao.save(employee);
    }


    //modifyEmployee����ʵ��
    @Override
    public void modifyEmployee(Employee employee) {
        employeeDao.update(employee);
    }


    /**
     *���ŷ���ӿ�ʵ��
     */

    //findDept����ʵ��
    @Override
    @Transactional(readOnly = true)
    public List<Dept> findDept(Dept dept, PageModel pageModel) {
        //��ǰ��Ҫ��ҳ������������
        Map<String,Object> params = new HashMap<>();
        params.put("dept",dept);
        int recordCount = deptDao.count(params);
        System.out.println("recordCount --> "+ recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0){
            //��ʼ��ҳ��ѯ����ѯ�ڼ�ҳ����
            params.put("pageModel",pageModel);
        }
        List<Dept> depts = deptDao.selectByPage(params);
        return depts;
    }


    //findAllDept����ʵ��
    @Override
    @Transactional(readOnly = true)
    public List<Dept> findAllDept() {
        return deptDao.selectAllDept();
    }


    //removeDeptById����ʵ��
    @Override
    public void removeDeptById(Integer id) {
        deptDao.deleteById(id);
    }


    //addDept����ʵ��
    @Override
    public void addDept(Dept dept) {
        deptDao.save(dept);
    }


    //findDeptById����ʵ��
    @Transactional(readOnly = true)
    @Override
    public Dept findDeptById(Integer id) {
        return deptDao.selectById(id);
    }


    //modifyDept����ʵ��
    @Override
    public void modifyDept(Dept dept) {
        deptDao.update(dept);
    }


    /**
     *ְλ�ӿ�ʵ��
     */

    //findAllJob����ʵ��
    @Transactional(readOnly = true)
    @Override
    public List<Job> findAllJob() {
        return jobDao.selectAllJob();
    }


    //findJob����ʵ��
    @Transactional(readOnly = true)
    @Override
    public List<Job> findJob(Job job, PageModel pageModel) {
        //��ǰ��Ҫ��ҳ����������
        Map<String,Object> params = new HashMap<>();
        params.put("job",job);
        int recordCount = jobDao.count(params);
        System.out.println("recordCount -->>" + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0){
            //��ʼ��ҳ��ѯ���ݣ���ѯ�ڼ�ҳ������
            params.put("pageModel",pageModel);
        }
        List<Job> jobs = jobDao.selectByPage(params);
        return jobs;
    }


    //removeJobById����ʵ��
    @Override
    public void removeJobById(Integer id) {
        jobDao.deleteById(id);
    }


    //addJob����ʵ��
    @Override
    public void addJob(Job job) {
        jobDao.save(job);
    }


    //findJobById����ʵ��
    @Transactional(readOnly = true)
    @Override
    public Job findJobById(Integer id) {
       return jobDao.selectById(id);
    }


    //modifyJob����ʵ��
    @Override
    public void modifyJob(Job job) {
        jobDao.update(job);
    }


    /**
     *����ӿ�ʵ��
     */

    //findNotice�ӿ�ʵ��
    @Transactional(readOnly = true)
    @Override
    public List<Notice> findNotice(Notice notice, PageModel pageModel) {
        //��ǰ��Ҫ��ҳ����������
        Map<String,Object> params = new HashMap<>();
        params.put("notice",notice);
        int recordCount = noticeDao.count(params);
        System.out.println("recordCount" + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0){
            //��ʼ��ҳ��ѯ���ݣ���ѯ�ڼ�ҳ����
            params.put("pageModel",pageModel);
        }
        List<Notice> notices = noticeDao.selectByPage(params);
        return notices;
    }


    //findNoticeById����ʵ��
    @Transactional(readOnly = true)
    @Override
    public Notice findNoticeById(Integer id) {
        return noticeDao.selectById(id);
    }


    //removeNoticeById����ʵ��
    @Override
    public void removeNoticeById(Integer id) {
        noticeDao.deleteById(id);
    }


    //addNotice����ʵ��
    @Override
    public void addNotice(Notice notice) {
        noticeDao.save(notice);
    }


    //modifyNotice����ʵ��
    @Override
    public void modifyNotice(Notice notice) {
        noticeDao.update(notice);
    }


    /**
     *�ļ��ӿ�ʵ��
     */

    //findDocument����ʵ��
    @Transactional(readOnly = true)
    @Override
    public List<Document> findDocument(Document document, PageModel pageModel) {
        //��ǰ��Ҫ��ҳ������������
        Map<String,Object> params = new HashMap<>();
        params.put("document",document);
        int recordCount = documentDao.count(params);
        System.out.println("recordCount -->> " + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0){
            //��ʼ��ҳ��ѯ����ѯ�ڼ�ҳ����
            params.put("pageModel",pageModel);
        }
        List<Document> documents = documentDao.selectByPage(params);
        return documents;
    }


    //addDocument����ʵ��
    @Override
    public void addDocument(Document document) {
        documentDao.save(document);
    }


    //findDocumentById����ʵ��
    @Transactional(readOnly = true)
    @Override
    public Document findDocumentById(Integer id) {
        return documentDao.selectById(id);
    }


    //removeDocumentById����ʵ��
    @Override
    public void removeDocumentById(Integer id) {
        documentDao.deleteById(id);
    }


    //modifyDocument����ʵ��
    @Override
    public void modifyDocument(Document document) {
        documentDao.update(document);
    }
}
