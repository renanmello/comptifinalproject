package com.codifica.compti.controllers;

import com.codifica.compti.models.userproduct.UserProduct;
import com.codifica.compti.services.UserProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-products")
@RequiredArgsConstructor
public class UserProductController {

    private final UserProductService userProductService;

    @GetMapping
    public Page<UserProduct> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Boolean type,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long userId,
            Pageable pageable
    ) {
        return userProductService.findUserProducts(name, type, categoryId, userId, pageable);
    }

    @GetMapping("/{id}")
    public UserProduct getById(@PathVariable Long id) {
        return userProductService.findById(id);
    }

    @PostMapping
    public UserProduct create(@RequestBody UserProduct product) {
        return userProductService.save(product);
    }

    @PutMapping("/{id}")
    public UserProduct update(@PathVariable Long id, @RequestBody UserProduct product) {
        return userProductService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userProductService.delete(id);
    }
}