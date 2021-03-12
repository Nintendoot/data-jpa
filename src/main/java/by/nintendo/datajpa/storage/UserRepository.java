package by.nintendo.datajpa.storage;


import by.nintendo.datajpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByName(String string);
}
