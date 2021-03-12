package by.nintendo.datajpa.storage;

import by.nintendo.datajpa.model.Key;
import by.nintendo.datajpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<Key, Long> {
    Key findKeyByName(String name);

    Key findKeyByUser(User user);
}
