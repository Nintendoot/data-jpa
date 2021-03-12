package by.nintendo.datajpa.storage;

import by.nintendo.datajpa.model.Pet;
import by.nintendo.datajpa.model.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
     List<Pet> findAllByStatus(PetStatus status);
}
