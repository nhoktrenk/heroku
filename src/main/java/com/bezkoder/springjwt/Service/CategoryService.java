package com.bezkoder.springjwt.Service;

import com.bezkoder.springjwt.models.Category;
import com.bezkoder.springjwt.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category save(Category account) {
        return categoryRepository.save(account);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

}
