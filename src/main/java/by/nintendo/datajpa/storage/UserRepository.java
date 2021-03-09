package by.nintendo.datajpa.storage;


import by.nintendo.datajpa.model.User;
import by.nintendo.datajpa.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByName(String string);
    public User findUserByUserStatus(UserStatus status);

}