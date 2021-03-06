package by.nintendo.datajpa.resource;


import by.nintendo.datajpa.model.Pet;

import by.nintendo.datajpa.service.PetService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(path = "/pet")
public class PetResource {

    private final PetService petService;

    public PetResource(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public ResponseEntity<Object> createPet( @Valid @RequestBody Pet pet) {
        log.info("Pet"+pet.getName());
        petService.createPet(pet);
        return new ResponseEntity<>("Pet create", HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<Object> updatepet(@Valid @RequestBody Pet pet) {
        petService.updatePet(pet);
        return new ResponseEntity<>("Pet update.", HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<?> getAll() {
        List<Pet> pets = petService.allPet();
        if (pets.size() != 0) {
            return new ResponseEntity<>(pets, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No pets.", HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Pet>> getById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(petService.getById(id), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateByIdForm(@PathVariable(name = "id") long id, @RequestBody Pet pet) {
        petService.updateByForm(id, pet);
        return new ResponseEntity<>("Pet update", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleatePetById(@PathVariable(name = "id") long id) {
        petService.deleatePet(id);
        return new ResponseEntity<>("Pet delete.", HttpStatus.OK);
    }

    @GetMapping(path = "/findByStatus")
    public ResponseEntity<?> statusPets(@Valid @RequestBody Pet pet) {
        List<Pet> patStatus = petService.getPetsByStatus(pet.getStatus());
        if (patStatus.size() != 0) {
            return new ResponseEntity<>(patStatus, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No pets by status " + pet.getStatus(), HttpStatus.OK);
        }
    }

}
