package controller;

import domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AAA {

    @RequestMapping("/test")
    public User getJson(){
        User user = new User();
        user.setUsername("sun");
        user.setPassword("123");
        user.setId(2);
        return user;
    }
}
