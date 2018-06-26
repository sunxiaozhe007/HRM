package controller;

import domain.Dept;
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
public class DeptController {
    /**
     * �Զ�ע��service
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * ����login����
     */
    @RequestMapping(value = "/dept/selectDept")
    public String selectDept(Model model, Integer pageIndex, @ModelAttribute Dept dept){
        System.out.println("selectDept -->>");
        System.out.println("pageIndex = " + pageIndex);
        System.out.println("dept = " + dept);
        PageModel pageModel = new PageModel();
        System.out.println("getPageIndex = " + pageModel.getPageIndex());
        System.out.println("getPageSize = " + pageModel.getPageSize());
        System.out.println("getRecordCount = " + pageModel.getRecordCount());

        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }

        /**
         * ��ѯ�û���Ϣ
         */
        List<Dept> depts = hrmService.findDept(dept,pageModel);
        model.addAttribute("depts",depts);
        model.addAttribute("pageModel",pageModel);
        return "dept/dept";
    }

    /**
     * ����ɾ����������
     */
    @RequestMapping(value = "dept/removeDept")
    public ModelAndView removeDept(String ids,ModelAndView mv){
        //�ֽ�id�ַ���
        String[] idArray = ids.split(",");
        for (String id : idArray){
            //����idɾ������
            hrmService.removeDeptById(Integer.valueOf(id));
        }
        //���ÿͻ�����ת����ѯ����
        mv.setViewName("redirect:/dept/selectDept");
        //����
        return mv;
    }


    /**
     * ������Ӳ�������
     */
    @RequestMapping(value = "/dept/addDept")
    public ModelAndView addDept(String flag,@ModelAttribute Dept dept,ModelAndView mv){
        if (flag.equals("1")){
            //������ת�����ҳ��
            mv.setViewName("dept/showAddDept");
        }else {
            //ִ����Ӳ���
            hrmService.addDept(dept);
            //���ÿͻ�����ת����ѯ����
            mv.setViewName("redirect:/dept/selectDept");
        }
        //����
        return mv;
    }


    /**
     * �����޸Ĳ�������
     */
    @RequestMapping(value = "/dept/updateDept")
    public ModelAndView updateDept(String flag,@ModelAttribute Dept dept,ModelAndView mv){
        if (flag.equals("1")){
            //����id��ѯ����
            Dept target = hrmService.findDeptById(dept.getId());
            //����Model����
            mv.addObject("dept",dept);
            //������ת���޸�ҳ��
            mv.setViewName("dept/showUpdateDept");
        }else {
            //ִ���޸Ĳ���
            hrmService.modifyDept(dept);
            //���ÿͻ�����ת����ѯ����
            mv.setViewName("redirect:/dept/selectDept");
        }
        //����
        return mv;
    }
}
