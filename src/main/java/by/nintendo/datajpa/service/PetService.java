package by.nintendo.datajpa.service;

import by.nintendo.datajpa.exception.PetNotFoundException;
import by.nintendo.datajpa.model.Pet;
import by.nintendo.datajpa.model.PetStatus;
import by.nintendo.datajpa.storage.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
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
