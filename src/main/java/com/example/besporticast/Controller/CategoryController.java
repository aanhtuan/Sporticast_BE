package com.example.besporticast.Controller;

import com.example.besporticast.Entity.Category;
import com.example.besporticast.Service.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/categories")
    public List<Category> getCategories() {
        return service.getAllCategories();
    }
}
