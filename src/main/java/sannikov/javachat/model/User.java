package sannikov.javachat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private Long role;

    @ManyToMany()
    @JoinTable(name = "user_chat", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private Collection<Chat> chats;

    @OneToMany(mappedBy = "user")
    private Collection<Message> messages;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Collection<Chat> getChats() {
        return chats;
    }
    public void setChats(Collection<Chat> chats) {
        this.chats = chats;
    }

    public void addChat(Chat chat) {
        this.chats.add(chat);
    }
    public void clearChats(){this.chats.clear();}
    public void deleteChat(Chat chat) {
        this.chats.remove(chat);
    }

    public Collection<Message> getMessages() {
        return messages;
    }
    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }
    public void clearMessage() {
        this.messages.clear();
    }

    public Long getRole() {
        return role;
    }
    public void setRole(Long role) {
        this.role = role;
    }
}