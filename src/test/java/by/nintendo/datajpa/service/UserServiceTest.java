package by.nintendo.datajpa.service;

import by.nintendo.datajpa.model.Role;
import by.nintendo.datajpa.model.User;
import by.nintendo.datajpa.storage.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserServiceTest {
   @Autowired
    UserRepository userRepositoryMock;
    @Autowired
    private UserService userService;

    @Test
    void getUserByName() {

    }

    @Test
    void deleateUser() {
    }

    @Test
    void allUsers() {

    }

    @Test
    void checkUserInMemory() {
    }

    @Test
    void createUser() {
        User user = new User(1, "hbibkb", "sdcdsc", "dcsd", "rrferf@gmail.com", "sdcsdc", "scscsc", Role.USER);
    User user3= userRepositoryMock.save(user);
//        userRepositoryMock.save(user);
//        assertTrue(userRepositoryMock.findAll().isEmpty());
      List<User> userList= userRepositoryMock.findAll();
        //Mockito.when(userService.createUser(user)).
        //User user1 = userRepositoryMock.getOne(1L);
        assertEquals(user3, user);
    }

    @Test
    void auth() {
    }

    @Test
    void updateUser() {
    }
}