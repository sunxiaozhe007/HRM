package controller;

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
public class JobController {

    /**
     * �Զ�ע��service
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * ����login����
     */
    @RequestMapping(value = "/job/selectJob")
    public String selectJob(Model model, Integer pageIndex, @ModelAttribute Job job){
        System.out.println("selectJob -->>" + job);
        PageModel pageModel = new PageModel();
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }

        /**
         * ��ѯ�û���Ϣ
         */
        List<Job> jobs = hrmService.findJob(job,pageModel);
        model.addAttribute("jobs",jobs);
        model.addAttribute("pageModel",pageModel);
        return "job/job";
    }


    /**
     * ����ɾ��ְλ����
     */
    @RequestMapping(value = "/job/removeJob")
    public ModelAndView removeJob(String ids,ModelAndView mv){
        //�ֽ�id�ַ���
        String[] idArray = ids.split(",");
        for (String id : idArray){
            //����idɾ��ְλ
            hrmService.removeJobById(Integer.valueOf(id));
        }
        //���ÿͻ�����ת����ѯ����
        mv.setViewName("redirect:/job/selectJob");
        //����
        return mv;
    }


    /**
     * �����������
     */
    @RequestMapping(value = "/job/addJob")
    public ModelAndView addJob(String flag,@ModelAttribute Job job,ModelAndView modelAndView){
        if (flag.equals("1")){
            //������ת�����ҳ��
            modelAndView.setViewName("job/showAddJob");
        }else {
            //ִ����Ӳ���
            hrmService.addJob(job);
            //���ÿͻ�����ת����ѯ����
            modelAndView.setViewName("redirect:/job/selectJob");
        }
        //����
        return modelAndView;
    }


    /**
     * �����޸�ְλ����
     */
    @RequestMapping(value = "/job/updateJob")
    public ModelAndView updateJob(String flag,@ModelAttribute Job job,ModelAndView mv){
        if (flag.equals("1")){
            //����id��ѯ����
            Job target = hrmService.findJobById(job.getId());
            //����Model����
            mv.addObject("job",target);
            //������ת���޸�ҳ��
            mv.setViewName("job/showUpdateJob");
        }else {
            //ִ���޸Ĳ���
            hrmService.modifyJob(job);
            //���ÿͻ�����ת����ѯ����
            mv.setViewName("redirect:/job/selectJob");
        }
        //����
        return mv;
    }
}
