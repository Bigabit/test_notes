package com.notes.controller;

import com.notes.model.Category;
import com.notes.model.Note;
import com.notes.service.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerUnitTests {

    @LocalServerPort
    private int port;

    @Mock
    CategoryService service;
    Category category;
    Note note;
    List<Note> notes;

    @InjectMocks
    CategoryController controller;

    @Autowired
    MockMvc mvc;

    @BeforeEach
    public void setup() {
        notes = new ArrayList<>();
        note = new Note();
        notes.add(note);
        category = new Category("Test", notes);

        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @AfterEach
    public void teardown() {
        note = null;
        notes = null;
        category = null;
    }

    @Test
    @DisplayName("Test find by name endpoint. Should return 200 for available query")
    public void testFindByNameEndpointAvailableName() throws Exception {
        Mockito.when(service.findByName("Test")).thenReturn(category);
        mvc.perform(MockMvcRequestBuilders.get("/category/Test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Test find by name endpoint. Should return 404 for unavailabe query")
    public void testFindByNameEndpointUnavailableName() throws Exception {
        Mockito.when(service.findByName("Tester")).thenReturn(new Category());
        mvc.perform(MockMvcRequestBuilders.get("/category/Tester"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

}
