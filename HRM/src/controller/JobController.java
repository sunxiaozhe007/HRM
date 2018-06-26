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
     * 自动注入service
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * 处理login请求
     */
    @RequestMapping(value = "/job/selectJob")
    public String selectJob(Model model, Integer pageIndex, @ModelAttribute Job job){
        System.out.println("selectJob -->>" + job);
        PageModel pageModel = new PageModel();
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }

        /**
         * 查询用户信息
         */
        List<Job> jobs = hrmService.findJob(job,pageModel);
        model.addAttribute("jobs",jobs);
        model.addAttribute("pageModel",pageModel);
        return "job/job";
    }


    /**
     * 处理删除职位请求
     */
    @RequestMapping(value = "/job/removeJob")
    public ModelAndView removeJob(String ids,ModelAndView mv){
        //分解id字符串
        String[] idArray = ids.split(",");
        for (String id : idArray){
            //根据id删除职位
            hrmService.removeJobById(Integer.valueOf(id));
        }
        //设置客户端跳转到查询请求
        mv.setViewName("redirect:/job/selectJob");
        //返回
        return mv;
    }


    /**
     * 处理添加请求
     */
    @RequestMapping(value = "/job/addJob")
    public ModelAndView addJob(String flag,@ModelAttribute Job job,ModelAndView modelAndView){
        if (flag.equals("1")){
            //设置跳转到添加页面
            modelAndView.setViewName("job/showAddJob");
        }else {
            //执行添加操作
            hrmService.addJob(job);
            //设置客户端跳转到查询请求
            modelAndView.setViewName("redirect:/job/selectJob");
        }
        //返回
        return modelAndView;
    }


    /**
     * 处理修改职位请求
     */
    @RequestMapping(value = "/job/updateJob")
    public ModelAndView updateJob(String flag,@ModelAttribute Job job,ModelAndView mv){
        if (flag.equals("1")){
            //根据id查询部门
            Job target = hrmService.findJobById(job.getId());
            //设置Model数据
            mv.addObject("job",target);
            //设置跳转到修改页面
            mv.setViewName("job/showUpdateJob");
        }else {
            //执行修改操作
            hrmService.modifyJob(job);
            //设置客户端跳转到查询请求
            mv.setViewName("redirect:/job/selectJob");
        }
        //返回
        return mv;
    }
}
