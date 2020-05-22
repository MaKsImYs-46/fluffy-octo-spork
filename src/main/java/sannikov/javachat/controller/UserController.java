package sannikov.javachat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sannikov.javachat.model.Chat;
import sannikov.javachat.model.User;
import sannikov.javachat.service.ChatService;
import sannikov.javachat.service.MessageService;
import sannikov.javachat.service.UserService;

@Controller
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ChatService chatService;

    @Autowired
    MessageService messageService;

    private ModelAndView returnToChat(User user, Chat chat){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("chat", chat);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/User/chatwindow");
        return  modelAndView;
    }

    @RequestMapping(path = "/choosechat/{user_id}/{chat_id}")
    public ModelAndView chooseChat(@PathVariable("user_id") Long user_id, @PathVariable("chat_id") Long chat_id){
        Chat chat = chatService.returnChat(chat_id);
        User user = userService.returnUser(user_id);
        return returnToChat(user, chat);
    }

    @RequestMapping(path = "/sendmessage/{user_id}/{chat_id}")
    public ModelAndView chooseChat(@PathVariable("user_id") Long user_id, @PathVariable("chat_id") Long chat_id, String text){
        Chat chat = chatService.returnChat(chat_id);
        User user = userService.returnUser(user_id);
        messageService.saveMessage(text, user, chat);
        return returnToChat(user, chat);
    }

    @RequestMapping(path = "/return/{id}")
    public ModelAndView returnToChooseChat(@PathVariable("id") Long id){
        User user = userService.returnUser(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/User/choosechat");
        return modelAndView;
    }
}
