package controller;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.HrmService;
import util.HrmConstants;
import util.PageModel;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * �û������������
 */
@Controller
public class UserController {

    /**
     * �Զ�ע��UserService
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;


    /**
     * �����½����
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("loginname") String loginname, @RequestParam("password")String password,
                              HttpSession session, ModelAndView mv){
        //�ж��û��Ƿ���Ե�½
        User user = hrmService.login(loginname,password);
        System.out.println(user);
        if (user != null){
            //���û����浽session��
            session.setAttribute(HrmConstants.USER_SESSION,user);
            //�ͻ�����ת��mainҳ��
            mv.setViewName("redirect:/main");
        }else {
            //���õ�½ʧ����ʾ��Ϣ
            mv.addObject("message","��½��������������������룡");
            //��ת����½ҳ��
            mv.setViewName("forward:/loginForm");
        }
        return mv;
    }

    /**
     * �����ѯ����
     */
    @RequestMapping(value = "/user/selectUser")
    public String selectUser(Integer pageIndex, @ModelAttribute User user, Model model){
        System.out.println("user = " + user);
        System.out.println(pageIndex);
        PageModel pageModel = new PageModel();
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        //��ѯ�û���Ϣ
        System.out.println(pageModel);
        List<User> users = hrmService.findUser(user,pageModel);

        model.addAttribute("users",users);
        model.addAttribute("pageModel",pageModel);
        return "/user/user";
    }

    /**
     * ����ɾ���û�����
     */
    @RequestMapping(value = "/user/removeUser")
    public ModelAndView removeUser(String ids,ModelAndView mv){
        //�ֽ�id�ַ���
        String[] idArray = ids.split(",");
        for (String id : idArray){
            //����idɾ��Ա��
            hrmService.removeUserById(Integer.parseInt(id));
        }

        //���ÿͻ�����ת����ѯ����
       mv.setViewName("redirect:/user/selectUser");
        //����
        return mv;
    }

    /**
     * �����޸��û�����
     */
    @RequestMapping(value = "/user/updateUser")
    public ModelAndView updateUser(String flag, @ModelAttribute User user, ModelAndView mv){
        if (flag.equals("1")){
            //����id��ѯ�û�
            User target = hrmService.findUserById(user.getId());
            //����Model����
            mv.addObject("user",target);
            //����Ա���޸�ҳ��
            mv.setViewName("user/showUpdateUser");
        }else {
            //ִ���޸Ĳ���
            hrmService.modifyUser(user);
            //���ÿͻ�����ת����ѯ����
            mv.setViewName("redirect:/user/selectUser");
        }
        //����
        return mv;
    }

    /**
     * �����������
     */
    @RequestMapping(value = "/user/addUser")
    public ModelAndView addUser(String flag, @ModelAttribute User user, ModelAndView mv){
        if (flag.equals("1")){
            //������ת�����ҳ��
            mv.setViewName("/user/showAddUser");
        }else {
            //ִ����Ӳ���
            hrmService.addUser(user);
            //���ÿͻ�����ת����ѯ����
            mv.setViewName("redirect:/user/selectUser");
        }

        //����
        return mv;
    }
}
