package com.example.besporticast.Service;

import com.example.besporticast.Entity.Category;
import com.example.besporticast.Repository.CategoryRepository;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Data
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAllCategories() {
        return repository.findAll();
    }
}
