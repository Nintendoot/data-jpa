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
    private final TagService tagService;
    private final CategoryService categoryService;

    @Autowired
    public PetService(PetRepository petRepository, TagService tagService, CategoryService categoryService) {
        this.petRepository = petRepository;
        this.tagService = tagService;
        this.categoryService = categoryService;
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
        Category category = categoryService.createCategory(pet);
        pet.setCategory(category);
        List<Tag> tags = tagService.createTag(pet.getTags());
        pet.setTags(tags);
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

    public boolean checkStatusForOrder(Pet pet) {
        if (pet.getStatus().equals(PetStatus.AVAILABLE)) {
            pet.setStatus(PetStatus.SOLD);
            return true;
        } else {
            return false;
        }
    }
}
