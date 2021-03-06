package by.nintendo.datajpa.storage;

import by.nintendo.datajpa.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByName(String name);
}
