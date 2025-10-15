package com.codifica.compti.models.productcategory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategory>> getAllCategories() {
        List<ProductCategory> categories = productCategoryService.viewAllProductCategories();
        return ResponseEntity.ok(categories);
    }

}
