package com.notes.repositories;

import com.notes.model.Category;
import com.notes.model.Note;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CategoryRepositoryUnitTests {

    @Autowired
    CategoryRepository repository;

    @BeforeEach
    public void setup() {
        Note note = new Note();
        List<Note> notes = new ArrayList<>();
        notes.add(note);
        repository.save(new Category(
                "Test", notes
        ));
    }

    @AfterEach
    public void teardown() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Test findByName method. Should return Category instance")
    public void testFindByName() {
        Assertions.assertTrue(
                repository.findByName("Test").isPresent()
        );
    }

}
