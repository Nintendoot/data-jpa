package by.nintendo.datajpa.service;

import by.nintendo.datajpa.model.Role;
import by.nintendo.datajpa.model.User;
import by.nintendo.datajpa.storage.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class UserServiceTest {
 @Autowired
 UserRepository userRepository = Mockito.mock(UserRepository.class);

 @BeforeEach
 public void initBd() {
  userRepository.save(new User(1, "name1", "sdcdsc", "dcsd", "rrferf@gmail.com", "sdcsdc", "scscsc", Role.USER));
  userRepository.save(new User(2, "name2", "sdcdsc", "dcsfdd", "rrferf@gmail.com", "sdcsdc", "sdfcscsc", Role.USER));
  userRepository.save(new User(3, "name3", "sdcdsc", "dcsd", "rrferf@gmail.com", "sdcsdc", "scscsc", Role.USER));
 }

    @Test
    void getUserByName() {
  User user=userRepository.findUserByName("name1");
  assertEquals(userRepository.findAll().get(0),user);

    }

    @Test
    void deleateUser() {
  userRepository.deleteById(1L);
  assertTrue(userRepository.findAll().size()==2);
    }

    @Test
    void allUsers() {
     assertNotNull(userRepository.findAll());
    // userRepository.deleteById(1L);
     assertTrue(userRepository.findAll().size()==3);
    }
    @Test
    void createUser() {
        User user = new User(4, "hbibkb", "sdcdsc", "dcsd", "rrferf@gmail.com", "sdcsdc", "scscsc", Role.USER);
    userRepository.save(user);
        assertEquals(userRepository.findAll().size(), 4);
    }

    @Test
    void updateUser() {
     User user = new User(1, "hbibkb", "sdcdsc", "dcsd", "rrferf@gmail.com", "sdcsdc", "scscsc", Role.USER);
     userRepository.save(user);
     assertEquals(userRepository.findAll().get(0),user);
    }
}