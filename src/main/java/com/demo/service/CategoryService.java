package com.demo.service;

import com.demo.model.Category;
import com.demo.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

//TODO: Connect to database

@Service
public class CategoryService {
    @Autowired
    CategoryRepo repo;

    public List<Category> getAll(){
        return repo.findAll();
    }

    public Category getCategoryById(String categoryId){
        return repo.findById(categoryId).get();
    }
}
