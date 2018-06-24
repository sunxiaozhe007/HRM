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
 * 服务层接口实现类
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service("hrmService")
public class HrmServiceImpl implements HrmService {
    /**
     *自动注入持久层dao对象
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
     *用户服务接口实现
     */
    //login方法实现
    @Transactional(readOnly = true)
    @Override
    public User login(String loginname, String password) {
        System.out.println("HrmServiceImpl login -->>");
        return userDao.selectByLoginnameAndPassword(loginname,password);
    }


    //findUserById方法实现
    @Transactional(readOnly = true)
    @Override
    public User findUserById(Integer id) {
        return userDao.selectById(id);
    }

    //findUser方法实现
    @Transactional(readOnly = true)
    @Override
    public List<User> findUser(User user, PageModel pageModel) {
        //当前需要分页的总数据条数
        Map<String,Object> params = new HashMap<>();
        params.put("user",user);
        int recordCount = userDao.count(params);
        System.out.println("recordCount -->>" + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0){
            //开始分页查询数据：查询第几页的数据
            params.put("pageModel",pageModel);
        }
        List<User> users = userDao.selectByPage(params);
        return users;
    }


    //removeUserById方法实现
    @Override
    public void removeUserById(Integer id) {
        userDao.deleteById(id);
    }


    //modifyUser方法实现
    @Override
    public void modifyUser(User user) {
        userDao.update(user);
    }


    //addUser方法实现
    @Override
    public void addUser(User user) {
        userDao.save(user);
    }


    /**
     *员工服务接口实现
     */

    //findEmployee方法实现
    @Transactional(readOnly = true)
    @Override
    public List<Employee> findEmployee(Employee employee, PageModel pageModel) {
        //当前需要分页的总数据条数
        Map<String,Object> params = new HashMap<>();
        params.put("employee",employee);
        int recordCount = employeeDao.count(params);
        System.out.println("recordCount -->> "+ recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0){
            //开始分页查询数据:查询第几页的数据
            params.put("pageModel",pageModel);
        }
        List<Employee> employees = employeeDao.selectByPage(params);
        return employees;
    }


    //removeEmployeeById方法实现
    @Override
    public void removeEmployeeById(Integer id) {
        employeeDao.deleteById(id);
    }


    //findEmployeeById方法实现
    @Transactional(readOnly = true)
    @Override
    public Employee findEmployeeById(Integer id) {
        return employeeDao.selectById(id);
    }


    //addEmployee方法实现
    @Override
    public void addEmployee(Employee employee) {
        employeeDao.save(employee);
    }


    //modifyEmployee方法实现
    @Override
    public void modifyEmployee(Employee employee) {
        employeeDao.update(employee);
    }


    /**
     *部门服务接口实现
     */

    //findDept方法实现
    @Override
    @Transactional(readOnly = true)
    public List<Dept> findDept(Dept dept, PageModel pageModel) {
        //当前需要分页的总数据条数
        Map<String,Object> params = new HashMap<>();
        params.put("dept",dept);
        int recordCount = deptDao.count(params);
        System.out.println("recordCount --> "+ recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0){
            //开始分页查询：查询第几页数据
            params.put("pageModel",pageModel);
        }
        List<Dept> depts = deptDao.selectByPage(params);
        return depts;
    }


    //findAllDept方法实现
    @Override
    @Transactional(readOnly = true)
    public List<Dept> findAllDept() {
        return deptDao.selectAllDept();
    }


    //removeDeptById方法实现
    @Override
    public void removeDeptById(Integer id) {
        deptDao.deleteById(id);
    }


    //addDept方法实现
    @Override
    public void addDept(Dept dept) {
        deptDao.save(dept);
    }


    //findDeptById方法实现
    @Transactional(readOnly = true)
    @Override
    public Dept findDeptById(Integer id) {
        return deptDao.selectById(id);
    }


    //modifyDept方法实现
    @Override
    public void modifyDept(Dept dept) {
        deptDao.update(dept);
    }


    /**
     *职位接口实现
     */

    //findAllJob方法实现
    @Transactional(readOnly = true)
    @Override
    public List<Job> findAllJob() {
        return jobDao.selectAllJob();
    }


    //findJob方法实现
    @Transactional(readOnly = true)
    @Override
    public List<Job> findJob(Job job, PageModel pageModel) {
        //当前需要分页总数据条数
        Map<String,Object> params = new HashMap<>();
        params.put("job",job);
        int recordCount = jobDao.count(params);
        System.out.println("recordCount -->>" + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0){
            //开始分页查询数据：查询第几页的数据
            params.put("pageModel",pageModel);
        }
        List<Job> jobs = jobDao.selectByPage(params);
        return jobs;
    }


    //removeJobById方法实现
    @Override
    public void removeJobById(Integer id) {
        jobDao.deleteById(id);
    }


    //addJob方法实现
    @Override
    public void addJob(Job job) {
        jobDao.save(job);
    }


    //findJobById方法实现
    @Transactional(readOnly = true)
    @Override
    public Job findJobById(Integer id) {
       return jobDao.selectById(id);
    }


    //modifyJob方法实现
    @Override
    public void modifyJob(Job job) {
        jobDao.update(job);
    }


    /**
     *公告接口实现
     */

    //findNotice接口实现
    @Transactional(readOnly = true)
    @Override
    public List<Notice> findNotice(Notice notice, PageModel pageModel) {
        //当前需要分页的数据总数
        Map<String,Object> params = new HashMap<>();
        params.put("notice",notice);
        int recordCount = noticeDao.count(params);
        System.out.println("recordCount" + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0){
            //开始分页查询数据：查询第几页数据
            params.put("pageModel",pageModel);
        }
        List<Notice> notices = noticeDao.selectByPage(params);
        return notices;
    }


    //findNoticeById方法实现
    @Transactional(readOnly = true)
    @Override
    public Notice findNoticeById(Integer id) {
        return noticeDao.selectById(id);
    }


    //removeNoticeById方法实现
    @Override
    public void removeNoticeById(Integer id) {
        noticeDao.deleteById(id);
    }


    //addNotice方法实现
    @Override
    public void addNotice(Notice notice) {
        noticeDao.save(notice);
    }


    //modifyNotice方法实现
    @Override
    public void modifyNotice(Notice notice) {
        noticeDao.update(notice);
    }


    /**
     *文件接口实现
     */

    //findDocument方法实现
    @Transactional(readOnly = true)
    @Override
    public List<Document> findDocument(Document document, PageModel pageModel) {
        //当前需要分页的总数据条数
        Map<String,Object> params = new HashMap<>();
        params.put("document",document);
        int recordCount = documentDao.count(params);
        System.out.println("recordCount -->> " + recordCount);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0){
            //开始分页查询：查询第几页数据
            params.put("pageModel",pageModel);
        }
        List<Document> documents = documentDao.selectByPage(params);
        return documents;
    }


    //addDocument方法实现
    @Override
    public void addDocument(Document document) {
        documentDao.save(document);
    }


    //findDocumentById方法实现
    @Transactional(readOnly = true)
    @Override
    public Document findDocumentById(Integer id) {
        return documentDao.selectById(id);
    }


    //removeDocumentById方法实现
    @Override
    public void removeDocumentById(Integer id) {
        documentDao.deleteById(id);
    }


    //modifyDocument方法实现
    @Override
    public void modifyDocument(Document document) {
        documentDao.update(document);
    }
}
