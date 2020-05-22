package sannikov.javachat.repository;

import sannikov.javachat.model.Chat;
import org.springframework.data.repository.CrudRepository;

public interface ChatRepository extends CrudRepository<Chat, Long>
{
}
