package com.notes.service;

import com.notes.model.Category;
import com.notes.model.Note;
import com.notes.repositories.CategoryRepository;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CategoryServiceUnitTests {

    @Mock
    CategoryRepository repository;

    @InjectMocks
    CategoryServiceImpl service;

    Category category;
    Note note;
    List<Note> notes;

    @BeforeEach
    public void setup() {
        notes = new ArrayList<>();
        note = new Note();
        notes.add(note);

        category = new Category("Test", notes);
    }

    @AfterEach
    public void teardown() {
        note = null;
        notes = null;
        category = null;
    }

    @Test
    @DisplayName("Test findByName service returns category model")
    public void testFindByNameCatAvailable(){
        Mockito.when(
                repository.findByName("Test")
        ).thenReturn(Optional.of(category));

        Assertions.assertEquals(
                service.findByName("Test").getName(),
                category.getName()
        );
    }

    @Test
    @DisplayName("Test findByName service returns category model")
    public void testFindByNameCatUnAvailable(){
        Mockito.when(
                repository.findByName("Tester")
        ).thenReturn(Optional.of(new Category()));

        Assertions.assertNull(service.findByName("Test").getId());
    }
}
