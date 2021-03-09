package by.nintendo.datajpa.service;

import by.nintendo.datajpa.model.Key;
import by.nintendo.datajpa.model.UserStatus;
import by.nintendo.datajpa.storage.KeyRepository;
import org.springframework.stereotype.Service;

@Service
public class KeyService {
    private final KeyRepository keyRepository;

    public KeyService(KeyRepository keyRepository) {
        this.keyRepository = keyRepository;
    }

    public UserStatus validKey(String key) {
        Key key1 = keyRepository.findKeyByName(key);
        if (key1 != null) {
            if (key1.getUser().getUserStatus().equals(UserStatus.ADMIN)) {
                return UserStatus.ADMIN;
            } else {
                return UserStatus.USER;
            }
        } else {
            return UserStatus.QUEST;
        }

    }


}
