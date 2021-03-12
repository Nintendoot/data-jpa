package by.nintendo.datajpa.resource;

import by.nintendo.datajpa.model.User;
import by.nintendo.datajpa.model.Role;
import by.nintendo.datajpa.storage.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserResourceTest {
    @Autowired
    UserRepository userRepository = Mockito.mock(UserRepository.class);

    @BeforeEach
    public void initBd() {
        userRepository.save(new User(0, "name1", "sdcdsc", "dcsd", "rrferf@gmail.com", "sdcsdc", "scscsc", Role.USER));
        userRepository.save(new User(1, "name2", "sdcdsc", "dcsfdd", "rrferf@gmail.com", "sdcsdc", "sdfcscsc", Role.USER));
        userRepository.save(new User(2, "name3", "sdcdsc", "dcsd", "rrferf@gmail.com", "sdcsdc", "scscsc", Role.USER));
    }

    @Test
    void getUserByName() {
        User user = userRepository.findUserByName("name1");
        User user1 = userRepository.findUserByName("sdsd");
        assertEquals(userRepository.findAll().get(0), user);
        assertNull(user1);
    }

    @Test
    void updateUser() {
        User user = userRepository.findUserByName("name1");
        User user1 = userRepository.findUserByName("sdsd");
        assertNotNull(user);
        assertEquals(userRepository.findAll().set(0, user), user);

    }

    @Test
    void getAll() {
        assertNotNull(userRepository.findAll());
        assertEquals(userRepository.findAll().size(),3);
    }

    @Test
    void createUser() {
        User user2 = userRepository.save(new User(0, "name4", "sdcdsc", "dcsd", "rrferf@gmail.com", "sdcsdc", "scscsc", Role.USER));
        userRepository.save(user2);
        assertEquals(userRepository.findAll().get(3), user2);
        assertFalse(userRepository.findAll().isEmpty());
    }

    @Test
    void deleateUserByName() {
        userRepository.delete(userRepository.findUserByName("name1"));
        assertEquals(userRepository.findAll().size(), 2);
    }
}