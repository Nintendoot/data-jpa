package by.nintendo.datajpa.service;

import by.nintendo.datajpa.model.Category;
import by.nintendo.datajpa.model.Pet;
import by.nintendo.datajpa.storage.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Pet pet) {
        Category category = categoryRepository.findCategoryByName(pet.getCategory().getName());
        if (category != null) {
            return category;
        } else {
            return pet.getCategory();
        }
    }
}
