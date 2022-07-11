package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.Service.CategoryService;
import com.bezkoder.springjwt.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getList() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Category> createNewCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (!optionalCategory.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalCategory.get());
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (!optionalCategory.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Category existCategory = optionalCategory.get();
        existCategory.setName(category.getName());
        return ResponseEntity.ok(categoryService.save(existCategory));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!categoryService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
