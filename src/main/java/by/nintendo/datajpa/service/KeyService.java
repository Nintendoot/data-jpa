package by.nintendo.datajpa.service;

import by.nintendo.datajpa.model.Key;
import by.nintendo.datajpa.model.User;
import by.nintendo.datajpa.model.Role;
import by.nintendo.datajpa.storage.KeyRepository;
import org.springframework.stereotype.Service;

@Service
public class KeyService {
    private final KeyRepository keyRepository;

    public KeyService(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }

    public Role validKey(String key) {
        Key key1 = keyRepository.findKeyByName(key);
        if (key1 != null) {
            if (key1.getUser().getRole().equals(Role.ADMIN)) {
                return Role.ADMIN;
            } else {
                return Role.USER;
            }
        } else {
            return Role.QUEST;
        }

    }

    public void saveKey(String name, User user){
        keyRepository.save(new Key(name,user));
    }






}
