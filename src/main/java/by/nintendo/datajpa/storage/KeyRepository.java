package by.nintendo.datajpa.storage;

import by.nintendo.datajpa.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyRepository extends JpaRepository<Key,Long> {
    public Key findKeyByName(String name);
}
