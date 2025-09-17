package com.codifica.compti.controllers;

import com.codifica.compti.models.productcategory.ProductCategory;
import com.codifica.compti.services.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping
    public Page<ProductCategory> getAll(
            @RequestParam(required = false) String name,
            Pageable pageable
    ) {
        return productCategoryService.findCategories(name, pageable);
    }

    @GetMapping("/{id}")
    public ProductCategory getById(@PathVariable Long id) {
        return productCategoryService.findById(id);
    }

    @PostMapping
    public ProductCategory create(@RequestBody ProductCategory category) {
        return productCategoryService.save(category);
    }

    @PutMapping("/{id}")
    public ProductCategory update(@PathVariable Long id, @RequestBody ProductCategory category) {
        return productCategoryService.update(id, category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productCategoryService.delete(id);
    }
}