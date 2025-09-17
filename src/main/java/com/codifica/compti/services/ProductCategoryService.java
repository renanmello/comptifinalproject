package com.codifica.compti.services;

import com.codifica.compti.models.productcategory.ProductCategory;
import com.codifica.compti.repositories.ProductCategoryRepository;
import com.codifica.compti.specifications.ProductCategorySpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    // 🔍 Buscar com filtros
    public Page<ProductCategory> findCategories(String name, Pageable pageable) {
        Specification<ProductCategory> spec = (root, query, cb) -> cb.conjunction();

        if (name != null && !name.isBlank()) {
            spec = spec.and(ProductCategorySpecifications.hasName(name));
        }

        return productCategoryRepository.findAll(spec, pageable);
    }

    // ➕ Criar
    public ProductCategory save(ProductCategory category) {
        return productCategoryRepository.save(category);
    }

    // 🔍 Buscar por ID
    public ProductCategory findById(Long id) {
        return productCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoria não encontrada com ID: " + id));
    }

    // ✏️ Atualizar
    public ProductCategory update(Long id, ProductCategory updatedCategory) {
        ProductCategory category = findById(id);
        category.setName(updatedCategory.getName());
        return productCategoryRepository.save(category);
    }

    // 🗑️ Deletar
    public void delete(Long id) {
        ProductCategory category = findById(id);
        productCategoryRepository.delete(category);
    }
}