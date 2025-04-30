package com.example.besporticast.Controller;

import com.example.besporticast.Entity.Category;
import com.example.besporticast.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private  CategoryService categoryService;


    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }
}
