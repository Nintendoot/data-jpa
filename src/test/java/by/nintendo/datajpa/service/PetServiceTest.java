package by.nintendo.datajpa.service;

import by.nintendo.datajpa.model.*;
import by.nintendo.datajpa.storage.PetRepository;
import by.nintendo.datajpa.storage.TagRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PetServiceTest {

    @Autowired
    PetRepository petRepositoryMock = Mockito.mock(PetRepository.class);

    @Test
    void getById() {
        petRepositoryMock.save(new Pet(1, new Category(0, "Category1"), "Pet1", new ArrayList<Tag>(Arrays.asList(new Tag(2, "Tag2"))), PetStatus.AVAILABLE));
        Pet pet=petRepositoryMock.getOne(1L);
        assertNotNull(pet);
    }

    @Test
    void deleatePet() {
        assertNotNull(petRepositoryMock.findAll());
        petRepositoryMock.deleteAll();
        assertEquals(petRepositoryMock.findAll().size(), 0);
    }

    @Test
    void createPet() {
    Pet pet=  petRepositoryMock.save(new Pet(2, new Category(0, "Category1"), "Pet1", new ArrayList<Tag>(Arrays.asList(new Tag(2, "Tag2"))), PetStatus.AVAILABLE));
        assertEquals(petRepositoryMock.findAll().size(), 1);
        assertEquals(petRepositoryMock.findAll().get(0),pet);
    }

    @Test
    void allPet() {
        Pet pet=  petRepositoryMock.save(new Pet(1, new Category(0, "Category1"), "Pet1", new ArrayList<Tag>(Arrays.asList(new Tag(2, "Tag2"))), PetStatus.AVAILABLE));
        assertNotNull(petRepositoryMock.findAll());
    }

    @Test
    void updateByForm() {
        Pet pet = petRepositoryMock.save(new Pet(1, new Category(5, "Category1dfdf"), "Pet1fddd", null, PetStatus.AVAILABLE));
        assertEquals(petRepositoryMock.findAll().get(0),pet);
    }

    @Test
    void updatePet() {
        petRepositoryMock.save(new Pet(1, new Category(0, "Category1"), "Pet1", new ArrayList<Tag>(Arrays.asList(new Tag(2, "Tag2"))), PetStatus.AVAILABLE));
        Pet pet = petRepositoryMock.save(new Pet(1, new Category(0, "C344"), "34", null, PetStatus.AVAILABLE));
        assertEquals(petRepositoryMock.getOne(1L), pet);
    }

}