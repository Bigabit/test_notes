package com.notes.service;

import com.notes.model.Category;
import com.notes.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository repository;

    @Override
    public Category findByName(String name) {
        Optional<Category> optional = repository.findByName(name);
        return optional.orElseGet(Category::new);
    }
}
