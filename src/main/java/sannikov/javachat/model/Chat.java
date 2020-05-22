package sannikov.javachat.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_id")
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String name;

    @ManyToMany()
    @JoinTable(name = "user_chat", joinColumns = @JoinColumn(name = "chat_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Collection<User> users;

    @OneToMany(mappedBy = "chat")
    private Collection<Message> messages;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }
    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
    public void clearUsers(){this.users.clear();}
    public void deleteUser(User user) {
        this.users.remove(user);
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
}
