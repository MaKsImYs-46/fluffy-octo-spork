package sannikov.javachat.model;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @Column(name = "text", unique = true)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_id", referencedColumnName = "chat_id", nullable = false)
    private Chat chat;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getText(){
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public User getUser(){
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Chat getChat(){
        return chat;
    }
    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
