package com.notes.service;

import com.notes.model.Category;

public interface CategoryService {
    Category findByName(String name);
}
