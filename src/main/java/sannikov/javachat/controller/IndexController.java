package sannikov.javachat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sannikov.javachat.additional.GeneralFunctions;
import sannikov.javachat.model.User;
import sannikov.javachat.additional.LoginUser;
import sannikov.javachat.service.ChatService;
import sannikov.javachat.service.UserService;

import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute LoginUser user) {
        User get_user = userService.check(user);
        ModelAndView modelAndView = new ModelAndView();
        if(get_user == null){
            LoginUser loginUser = new LoginUser();
            modelAndView.addObject("user", loginUser);
            modelAndView.setViewName("/index");
        }
        else{
            if(get_user.getRole() == 1){
                return GeneralFunctions.AdminMain(userService, chatService);
            }
            else {
                modelAndView.addObject("user", get_user);
                modelAndView.setViewName("/User/choosechat");
            }
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/index", "/"})
    public String getIndex(Model model)
    {
        LoginUser user = new LoginUser();
        model.addAttribute("user", user);
        return "/index";
    }
}
