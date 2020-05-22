package sannikov.javachat.repository;

import sannikov.javachat.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{

}
