package sannikov.javachat.service;
import org.springframework.beans.factory.annotation.Autowired;
import sannikov.javachat.additional.GeneralFunctions;
import sannikov.javachat.model.Chat;
import sannikov.javachat.model.User;
import sannikov.javachat.repository.ChatRepository;
import org.springframework.stereotype.Service;
import sannikov.javachat.repository.MessageRepository;

import java.util.Collection;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    public List<Chat> getAllChats(){
        return (List<Chat>) chatRepository.findAll();
    }

    public List<Chat> getOtherChats(List<Chat> userchats){
        List<Chat> otherchats = getAllChats();
        for(int i = 0; i < userchats.size(); i++){
            for(int j = 0; j < otherchats.size(); j++){
                if(otherchats.get(j).getId() == userchats.get(i).getId()){
                    otherchats.remove(j);
                    break;
                }
            }
        }
        return otherchats;
    }

    public Chat returnChat(Long id){
        return chatRepository.findById(id).get();
    }

    public void deleteUserInChat(User user, Chat chat){
        chat.deleteUser(user);
        chatRepository.save(chat);
    }

    public void addUserInChat(User user, Chat chat){
        chat.addUser(user);
        chatRepository.save(chat);
    }

    public void saveChat(Chat chat){
        chatRepository.save(chat);
    }

    public void addChat(){
        Integer k = 1;
        String name;
        List<Chat> chats = getAllChats();
        while (true){
            name = "Chat" + k.toString();
            boolean b = true;
            for(int i = 0; i < chats.size(); i++){
                if(chats.get(i).getName().equals(name)){
                    b = false;
                    break;
                }
            }
            if(b)
                break;
            else
                k++;
        }

        Chat chat = new Chat();
        chat.setName(name);
        chatRepository.save(chat);
    }

    public void deleteChat(Long id){
        GeneralFunctions.deletemessages(chatRepository.findById(id).get().getMessages(), messageRepository);
        chatRepository.deleteById(id);
    }
}
