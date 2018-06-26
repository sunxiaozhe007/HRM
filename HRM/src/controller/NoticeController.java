package controller;


import domain.Notice;
import domain.User;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.HrmService;
import util.HrmConstants;
import util.PageModel;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NoticeController {
    /**
     * �Զ�ע��Service
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;

    /**
     * ����login����
     */
    @RequestMapping(value = "/notice/selectNotice")
    public String selectNotice(Model model, Integer pageIndex, @ModelAttribute Notice notice){
        System.out.println(pageIndex);
        PageModel pageModel = new PageModel();
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }

        //��ѯ�û���Ϣ
        List<Notice> notices = hrmService.findNotice(notice,pageModel);
        model.addAttribute("notices",notices);
        model.addAttribute("pageModel",pageModel);
        return "notice/notice";
    }


    /**
     * �����������
     */
    @RequestMapping(value = "/notice/previewNotice")
    public String previewNNotice(Integer id,Model model){
        Notice notice = hrmService.findNoticeById(id);
        model.addAttribute("notice",notice);
        //����
        return "notice/previewNotice";
    }


    /**
     * ����ɾ����������
     */
    @RequestMapping(value = "/notice/removeNotice")
    public ModelAndView removeNotice(String ids,ModelAndView mv){
        //�ֽ�id�ַ���
        String[] idArray = ids.split(",");
        for (String id : idArray){
            //����idɾ������
            hrmService.removeNoticeById(Integer.valueOf(id));
        }
        //���ÿͻ�����ת����ѯ����
        mv.setViewName("redirect:/notice/selectNotice");
        //����
        return mv;
    }


    /**
     * �����������
     */
    @RequestMapping(value = "/notice/addNotice")
    public  ModelAndView addNotice(String flag, @ModelAttribute Notice notice, HttpSession session,ModelAndView mv){
        if (flag.equals("1")){
            mv.setViewName("/notice/showAddNotice");
        }else {
            User user = (User) session.getAttribute(HrmConstants.USER_SESSION);
            notice.setUser(user);
            hrmService.addNotice(notice);
            mv.setViewName("redirect:/notice/selectNotice");
        }
        //����
        return mv;
    }
    /**
     * �����޸�����
     */
    @RequestMapping(value = "/notice/updateNotice")
    public ModelAndView updateNotice(String flag, @ModelAttribute Notice notice,ModelAndView mv,HttpSession session){
        if (flag.equals("1")){
            Notice target = hrmService.findNoticeById(notice.getId());
            mv.addObject("notice",target);
            mv.setViewName("/notice/showUpdateNotice");
        }else {
            hrmService.modifyNotice(notice);
            mv.setViewName("redirect:/notice/selectNotice");
        }
        //����
        return mv;
    }
}
