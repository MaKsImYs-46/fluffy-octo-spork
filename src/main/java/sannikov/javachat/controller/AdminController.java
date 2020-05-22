package sannikov.javachat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sannikov.javachat.additional.GeneralFunctions;
import sannikov.javachat.model.Chat;
import sannikov.javachat.model.User;
import sannikov.javachat.service.ChatService;
import sannikov.javachat.service.UserService;

import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatService;

    @RequestMapping(path = "/deleteuser/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return returnToMain();
    }

    @RequestMapping(path = "/edituser/{id}")
    public ModelAndView editUser(@PathVariable("id") Long id){
        User user = userService.returnUser(id);
        List<Chat> userchats = (List<Chat>) user.getChats();
        List<Chat> otherchats = chatService.getOtherChats(userchats);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.addObject("userchats", userchats);
        modelAndView.addObject("otherchats", otherchats);
        modelAndView.setViewName("/Admin/edituser");
        return modelAndView;
    }

    @RequestMapping(path = "/return", method = RequestMethod.GET)
    public ModelAndView returnToMain(){
        return GeneralFunctions.AdminMain(userService, chatService);
    }

    @RequestMapping(path = "/deletechatinuser/{chat_id}/{user_id}")
    public ModelAndView deleteChatInUser(@PathVariable("chat_id") Long chat_id, @PathVariable("user_id") Long user_id){
        User user = userService.returnUser(user_id);
        Chat chat = chatService.returnChat(chat_id);
        userService.deleteChatInUser(user, chat);
        //chatService.deleteUserInChat(user, chat);
        return editUser(user_id);
    }

    @RequestMapping(path = "/addchatinuser/{chat_id}/{user_id}")
    public ModelAndView addChatInUser(@PathVariable("chat_id") Long chat_id, @PathVariable("user_id") Long user_id){
        User user = userService.returnUser(user_id);
        Chat chat = chatService.returnChat(chat_id);
        userService.addChatInUser(user, chat);
        //chatService.addUserInChat(user, chat);
        return editUser(user_id);
    }

    @RequestMapping(path = "/edituser", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute User user){
        userService.saveUser(user);
        return returnToMain();
    }

    @RequestMapping(path = "/adduser", method = RequestMethod.POST)
    public ModelAndView addUser(){
        userService.addUser();
        return returnToMain();
    }

    @RequestMapping(path = "/addchat", method = RequestMethod.POST)
    public ModelAndView addChat(){
        chatService.addChat();
        return returnToMain();
    }

    @RequestMapping(path = "/deletechat/{id}")
    public ModelAndView deleteChat(@PathVariable("id") Long id){
        chatService.deleteChat(id);
        return GeneralFunctions.AdminMain(userService, chatService);
    }
}
