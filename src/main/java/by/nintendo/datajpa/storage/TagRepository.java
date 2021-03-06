package by.nintendo.datajpa.storage;

import by.nintendo.datajpa.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findTagByName(String name);
}
