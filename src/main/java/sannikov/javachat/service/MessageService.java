package sannikov.javachat.service;
import org.springframework.beans.factory.annotation.Autowired;
import sannikov.javachat.model.Chat;
import sannikov.javachat.model.Message;
import sannikov.javachat.model.User;
import sannikov.javachat.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void saveMessage(String text, User user, Chat chat){
        Message message = new Message();
        message.setUser(user);
        message.setChat(chat);
        message.setText(text);
        messageRepository.save(message);
    }
}
