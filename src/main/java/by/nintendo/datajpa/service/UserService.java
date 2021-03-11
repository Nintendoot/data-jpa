package by.nintendo.datajpa.service;

import by.nintendo.datajpa.exception.NoSuchUserException;
import by.nintendo.datajpa.exception.UserAlreadyExistsException;
import by.nintendo.datajpa.exception.WrongPasswordException;
import by.nintendo.datajpa.model.Key;
import by.nintendo.datajpa.model.Role;
import by.nintendo.datajpa.model.User;
import by.nintendo.datajpa.storage.KeyRepository;
import by.nintendo.datajpa.storage.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final KeyRepository keyRepository;

    @Autowired
    public UserService(UserRepository userRepository, KeyRepository keyRepository) {

        this.userRepository = userRepository;
        this.keyRepository = keyRepository;
    }

    public User getUserByName(String name) {
        if (userRepository.findUserByName(name) != null) {
            return userRepository.findUserByName(name);
        } else {
            throw new NoSuchUserException("This user was not found.");
        }
    }

    public void deleateUser(String name) {
        User user = userRepository.findUserByName(name);
        if (user != null) {
            if (keyRepository.findKeyByUser(user) != null) {
                keyRepository.delete(keyRepository.findKeyByUser(user));
            } else {
                userRepository.delete(user);
            }
        } else {
            throw new NoSuchUserException("This user was not found.");
        }

    }

    public List<User> allUsers() {
        if (userRepository.findAll().size() != 0) {
            return userRepository.findAll();
        } else {
            throw new NoSuchUserException("No users!");
        }

    }

    public boolean checkUserInMemory(User user) {
        return userRepository.findAll().stream().
                anyMatch(x -> x.getName().equals(user.getName()));
    }

    public void createUser(User user) {
        if (!checkUserInMemory(user)) {
            if (user.getName().equals("admin") && user.getPassword().equals("admin")) {
                user.setRole(Role.ADMIN);
                userRepository.save(user);
            } else {
                user.setRole(Role.USER);
                userRepository.save(user);
            }
        } else {
            throw new UserAlreadyExistsException("User already exists.");
        }
    }

    public String auth(User user) {
        User us = getUserByName(user.getName());
        if (us != null) {
            if (us.equals(user)) {
                if (keyRepository.findKeyByUser(us) != null) {
                    return keyRepository.findKeyByUser(us).getName();
                } else {
                    UUID key = UUID.randomUUID();
                    String ui = key.toString();
                    keyRepository.save(new Key(ui, us));
                    return ui;
                }
            } else {
               throw new WrongPasswordException("Wrong password!");
            }
        } else {
            throw new NoSuchUserException("user not found");
        }
    }


    public void updateUser(String name, User user) {
        User us = getUserByName(name);
        if (us != null) {
            user.setId(us.getId());
            userRepository.save(user);
        } else {
            throw new NoSuchUserException("User not found.");
        }
    }


}



