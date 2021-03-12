package by.nintendo.datajpa.service;

import by.nintendo.datajpa.exception.PetNotFoundException;
import by.nintendo.datajpa.model.Category;
import by.nintendo.datajpa.model.Pet;
import by.nintendo.datajpa.model.PetStatus;
import by.nintendo.datajpa.model.Tag;
import by.nintendo.datajpa.storage.CategoryRepository;
import by.nintendo.datajpa.storage.PetRepository;
import by.nintendo.datajpa.storage.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;
    private final TagRepository tagRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PetService(PetRepository petRepository, TagRepository tagRepository, CategoryRepository categoryRepository) {
        this.petRepository = petRepository;
        this.tagRepository = tagRepository;
        this.categoryRepository = categoryRepository;
    }

    public Optional<Pet> getById(long id) {
        if (petRepository.existsById(id)) {
            return petRepository.findById(id);
        } else {
            throw new PetNotFoundException("Pet not found.");
        }
    }

    public void deleatePet(long id) {
        if (petRepository.existsById(id)) {
            petRepository.delete(petRepository.getOne(id));
        } else {
            throw new PetNotFoundException("Pet not found.");
        }
    }

    public void createPet(Pet pet) {
        Category cate = categoryRepository.findCategoryByName(pet.getCategory().getName());
        Category category = cate != null ? cate : pet.getCategory();
        List<Tag> tags = new ArrayList<>();
        for (Tag tag : pet.getTags()) {
            Tag t = tagRepository.findTagByName(tag.getName());
            Tag ta = t != null ? t : tag;
            tags.add(ta);
        }
        pet.setTags(tags);
        pet.setCategory(category);
        petRepository.save(pet);
    }

    public List<Pet> allPet() {
        return petRepository.findAll();
    }

    public void updateByForm(long id, Pet pet) {
        if (petRepository.existsById(id)) {
            Pet p = petRepository.getOne(id);
            p.setName(pet.getName());
            p.setStatus(pet.getStatus());
            petRepository.save(p);
        } else {
            throw new PetNotFoundException("Pet not found.");
        }
    }

    public void updatePet(Pet pet) {
        if (petRepository.existsById(pet.getId())) {
            petRepository.save(pet);
        } else {
            throw new PetNotFoundException("Pet not found.");
        }
    }

    public List<Pet> getPetsByStatus(PetStatus status) {
        return petRepository.findAllByStatus(status);
    }
}
