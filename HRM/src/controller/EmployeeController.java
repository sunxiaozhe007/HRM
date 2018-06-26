package controller;

import domain.Dept;
import domain.Employee;
import domain.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.HrmService;
import util.PageModel;

import java.util.List;
 @Controller
public class EmployeeController {

    /**
     * �Զ�ע��Service
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * �����ѯ����
     */
    @RequestMapping(value = "/employee/selectEmployee")
    public String selectEmployee(Integer pageIndex, Integer job_id, Integer dept_id, @ModelAttribute Employee employee, Model model){

        System.out.println(job_id);
        System.out.println(dept_id);
        System.out.println(pageIndex);
        System.out.println(employee.getName());
        //ģ����ѯʱ�ж��Ƿ��й������󴫵ݣ�����У���������װ��������
        this.genericAssiciation(job_id,dept_id,employee);
        //������ҳ����
        PageModel pageModel = new PageModel();
        //�������pageIndex��Ϊnull��������pageIndex������ʾ�ڼ�ҳ
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        //��ѯְλ��Ϣ������ģ����ѯ
        List<Job> jobs = hrmService.findAllJob();
        //��ѯ������Ϣ������ģ����ѯ
        List<Dept> depts = hrmService.findAllDept();
        //��ѯԱ����Ϣ
        List<Employee> employees = hrmService.findEmployee(employee,pageModel);
        //����Model����
        model.addAttribute("employees",employees);
        model.addAttribute("jobs",jobs);
        model.addAttribute("depts",depts);
        model.addAttribute("pageModel",pageModel);
        //����Ա��ҳ��
        return "employee/employee";
    }


    /**
     * �������Ա������
     */
    @RequestMapping(value = "/employee/addEmployee")
    public String addEmployee(String flag,Integer job_id,Integer dept_id,@ModelAttribute Employee employee,Model mv){
        if (flag.equals("1")){
            //��ѯְλ��Ϣ
            List<Job> jobs = hrmService.findAllJob();
            //��ѯ������Ϣ
            List<Dept> depts = hrmService.findAllDept();
            //����Model����
            mv.addAttribute("jobs",jobs);
            mv.addAttribute("depts",depts);
            //�������Ա��ҳ��
            return "employee/showAddEmployee";
        }else {
            //�ж��Ƿ��й������󴫵ݣ�����У�������������
            this.genericAssiciation(job_id, dept_id, employee);
            //��Ӳ���
            hrmService.addEmployee(employee);
            //���ÿͻ�����ת����ѯ����
            return "redirect:/employee/selectEmployee";
        }
    }

    /**
     * ����ɾ��Ա������
     */
    @RequestMapping(value = "employee/removeEmployee")
    public ModelAndView removeEmployee(String ids,ModelAndView mv){
        //�ֽ�id�ַ���
        String[] idArray = ids.split(",");
        for (String id : idArray){
            //����idɾ��Ա��
            hrmService.removeEmployeeById(Integer.valueOf(id));
        }
        //���ÿͻ�����ת����ѯ����
        mv.setViewName("redirect:/employee/selectEmployee");
        //����
        return mv;
    }

    /**
     * �����޸�Ա������
     */
    @RequestMapping(value = "/employee/updateEmployee")
    public ModelAndView updateEmployee(String flag,Integer job_id,Integer dept_id,@ModelAttribute Employee employee,ModelAndView mv){
        if (flag.equals("1")){
            //����id��ѯԱ��
            Employee target = hrmService.findEmployeeById(employee.getId());
            //��Ҫ��ѯְλ��Ϣ
            List<Job> jobs = hrmService.findAllJob();
            //��Ҫ��ѯ������Ϣ
            List<Dept> depts = hrmService.findAllDept();
            //����Model����
            mv.addObject("jobs",jobs);
            mv.addObject("depts",depts);
            mv.addObject("employee",target);
            //����Ա���޸ĸ�ҳ��
            mv.setViewName("employee/showUpdateEmployee");
        }else {
            //��������װ����
            this.genericAssiciation(job_id, dept_id, employee);
            System.out.println("updateEmployee -->>" +employee);
            //ִ���޸Ĳ���
            hrmService.modifyEmployee(employee);
            //���ÿͻ�����ת����ѯ����
            mv.setViewName("redirect:/employee/selectEmployee");
        }
        //����
        return mv;
    }


    /**
     * ���ڲ��ź�ְλ��Employee���Ƕ������ӳ��
     * ���Բ���ֱ�ӽ��ܲ�������Ҫ����Job�����Dept����
     */
    private void genericAssiciation(Integer job_id,Integer dept_id,Employee employee){
        if (job_id != null){
            Job job = new Job();
            job.setId(job_id);
            employee.setJob(job);
        }
        if (dept_id != null){
            Dept dept = new Dept();
            dept.setId(dept_id);
            employee.setDept(dept);
        }
    }


}
