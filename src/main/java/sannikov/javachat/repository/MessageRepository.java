package sannikov.javachat.repository;
import sannikov.javachat.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long>
{
}
