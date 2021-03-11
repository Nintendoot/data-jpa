package by.nintendo.datajpa.storage;

import by.nintendo.datajpa.model.Key;
import by.nintendo.datajpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<Key,Long> {
    public Key findKeyByName(String name);
    public Key findKeyByUser(User user);
}
