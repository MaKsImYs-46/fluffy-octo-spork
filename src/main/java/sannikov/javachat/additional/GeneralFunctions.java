package sannikov.javachat.additional;

import org.springframework.web.servlet.ModelAndView;
import sannikov.javachat.model.Chat;
import sannikov.javachat.model.Message;
import sannikov.javachat.model.User;
import sannikov.javachat.repository.MessageRepository;
import sannikov.javachat.service.ChatService;
import sannikov.javachat.service.UserService;

import java.util.Collection;
import java.util.List;

public class GeneralFunctions {
    
    public static ModelAndView AdminMain(UserService userService, ChatService chatService){
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.getAllUsers();
        List<Chat> chats = chatService.getAllChats();
        modelAndView.addObject("users", users);
        modelAndView.addObject("chats", chats);
        modelAndView.setViewName("/Admin/main");
        return modelAndView;
    }
    
    public static void deletemessages(Collection<Message> messages, MessageRepository messageRepository){
        List<Message> mes = (List<Message>) messages;
        for(int i = 0; i < mes.size(); i++)
            messageRepository.delete(mes.get(i));
    }
}
