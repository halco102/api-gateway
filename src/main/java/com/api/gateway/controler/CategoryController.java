package com.api.gateway.controler;

import com.api.gateway.dto.post.category.CategoryRequestDto;
import com.api.gateway.feign.category.CategoryClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryClient categoryClient;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryRequestDto requestDto) {
        return new ResponseEntity<>(categoryClient.saveCategory(requestDto), HttpStatus.OK);
    }

    @GetMapping("/name")
    public ResponseEntity<?> getCategoryByName(@RequestParam String name) {
        return new ResponseEntity<>(categoryClient.getCategoryByName(name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoryClient.getCategoryById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(categoryClient.getAllCategories(), HttpStatus.OK);
    }
}
