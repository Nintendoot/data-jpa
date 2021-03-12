package by.nintendo.datajpa.service;

import by.nintendo.datajpa.model.*;
import by.nintendo.datajpa.storage.PetRepository;
import by.nintendo.datajpa.storage.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PetServiceTest {

    @Autowired
    PetRepository petRepositoryMock = Mockito.mock(PetRepository.class);

    @Autowired
    TagRepository tagRepository = Mockito.mock(TagRepository.class);

    @BeforeEach
    public void initBd() {
        List<Tag> list1 = new ArrayList<>(tagRepository.findAll());
        tagRepository.save(new Tag(2, "Tag2"));
        tagRepository.save(new Tag(5, "Tag5"));
        petRepositoryMock.save(new Pet(10, new Category(0, "Category1"), "Pet1", list1, PetStatus.AVAILABLE));
        petRepositoryMock.save(new Pet(12, new Category(1, "Category2"), "Pet2", list1, PetStatus.AVAILABLE));
        petRepositoryMock.save(new Pet(22, new Category(2, "Category1"), "Pet3", list1, PetStatus.AVAILABLE));
    }

    @Test
    void getById() {
    }

    @Test
    void deleatePet() {
        assertNotNull(petRepositoryMock.findAll());
        petRepositoryMock.deleteAll();
        assertEquals(petRepositoryMock.findAll().size(), 0);

    }

    @Test
    void createPet() {
        List<Tag> list = new ArrayList<>(tagRepository.findAll());
        Tag tf = tagRepository.save(new Tag(23, "Tag22343"));
        list.add(tf);
        petRepositoryMock.save(new Pet(7, new Category(0, "Category1"), "Pet1", list, PetStatus.AVAILABLE));
        assertEquals(petRepositoryMock.findAll().size(),4);
    }

    @Test
    void allPet() {
        assertNotNull(petRepositoryMock.findAll());
    }

    @Test
    void updateByForm() {
    }

    @Test
    void updatePet() {
    }

}