package sannikov.javachat.service;

import org.springframework.beans.factory.annotation.Autowired;
import sannikov.javachat.additional.GeneralFunctions;
import sannikov.javachat.additional.LoginUser;
import sannikov.javachat.model.Chat;
import sannikov.javachat.model.Message;
import sannikov.javachat.model.User;
import sannikov.javachat.repository.MessageRepository;
import sannikov.javachat.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    public User check(LoginUser user){
        List<User> users = (List<User>) userRepository.findAll();
        for(int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(user.getName()) &&
                    users.get(i).getPassword().equals(user.getPassword()))
                return users.get(i);
        }
        return null;
    }

    public List<User> getAllUsers(){
        List<User> users = (List<User>) userRepository.findAll();
        for(int i = 0; i < users.size();i++){
            if(users.get(i).getRole() == 1)
                users.remove(i);
        }
        return users;
    }

    public void deleteUser(Long id){
        GeneralFunctions.deletemessages(userRepository.findById(id).get().getMessages(), messageRepository);
        userRepository.deleteById(id);
    }

    public User returnUser(Long id){
        return userRepository.findById(id).get();
    }

    public void deleteChatInUser(User user, Chat chat){
        user.deleteChat(chat);
        userRepository.save(user);
    }

    public void addChatInUser(User user, Chat chat){
        user.addChat(chat);
        userRepository.save(user);
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public void addUser(){
        Integer k = 1;
        String name;
        List<User> users = getAllUsers();
        while (true){
            name = "User" + k.toString();
            boolean b = true;
            for(int i = 0; i < users.size(); i++){
                if(users.get(i).getName().equals(name)){
                    b = false;
                    break;
                }
            }
            if(b)
                break;
            else
                k++;
        }

        User user = new User();
        user.setName(name);
        user.setPassword("12345");
        user.setRole((long)2);
        userRepository.save(user);
    }
}
