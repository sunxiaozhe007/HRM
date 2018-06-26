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
 * 用户请求处理控制器
 */
@Controller
public class UserController {

    /**
     * 自动注入UserService
     */
    @Autowired
    @Qualifier("hrmService")
    private HrmService hrmService;


    /**
     * 处理登陆请求
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("loginname") String loginname, @RequestParam("password")String password,
                              HttpSession session, ModelAndView mv){
        //判断用户是否可以登陆
        User user = hrmService.login(loginname,password);
        System.out.println(user);
        if (user != null){
            //将用户保存到session中
            session.setAttribute(HrmConstants.USER_SESSION,user);
            //客户端跳转到main页面
            mv.setViewName("redirect:/main");
        }else {
            //设置登陆失败提示信息
            mv.addObject("message","登陆名或密码错误，请重新输入！");
            //跳转到登陆页面
            mv.setViewName("forward:/loginForm");
        }
        return mv;
    }

    /**
     * 处理查询请求
     */
    @RequestMapping(value = "/user/selectUser")
    public String selectUser(Integer pageIndex, @ModelAttribute User user, Model model){
        System.out.println("user = " + user);
        System.out.println(pageIndex);
        PageModel pageModel = new PageModel();
        if (pageIndex != null){
            pageModel.setPageIndex(pageIndex);
        }
        //查询用户信息
        System.out.println(pageModel);
        List<User> users = hrmService.findUser(user,pageModel);

        model.addAttribute("users",users);
        model.addAttribute("pageModel",pageModel);
        return "/user/user";
    }

    /**
     * 处理删除用户请求
     */
    @RequestMapping(value = "/user/removeUser")
    public ModelAndView removeUser(String ids,ModelAndView mv){
        //分解id字符串
        String[] idArray = ids.split(",");
        for (String id : idArray){
            //根据id删除员工
            hrmService.removeUserById(Integer.parseInt(id));
        }

        //设置客户端跳转到查询请求
       mv.setViewName("redirect:/user/selectUser");
        //返回
        return mv;
    }

    /**
     * 处理修改用户请求
     */
    @RequestMapping(value = "/user/updateUser")
    public ModelAndView updateUser(String flag, @ModelAttribute User user, ModelAndView mv){
        if (flag.equals("1")){
            //根据id查询用户
            User target = hrmService.findUserById(user.getId());
            //设置Model数据
            mv.addObject("user",target);
            //返回员工修改页面
            mv.setViewName("user/showUpdateUser");
        }else {
            //执行修改操作
            hrmService.modifyUser(user);
            //设置客户端跳转到查询请求
            mv.setViewName("redirect:/user/selectUser");
        }
        //返回
        return mv;
    }

    /**
     * 处理添加请求
     */
    @RequestMapping(value = "/user/addUser")
    public ModelAndView addUser(String flag, @ModelAttribute User user, ModelAndView mv){
        if (flag.equals("1")){
            //设置跳转到添加页面
            mv.setViewName("/user/showAddUser");
        }else {
            //执行添加操作
            hrmService.addUser(user);
            //设置客户端跳转到查询请求
            mv.setViewName("redirect:/user/selectUser");
        }

        //返回
        return mv;
    }
}
