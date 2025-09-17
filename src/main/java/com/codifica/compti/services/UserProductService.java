package com.codifica.compti.services;

import com.codifica.compti.models.userproduct.UserProduct;
import com.codifica.compti.repositories.UserProductRepository;
import com.codifica.compti.specifications.UserProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserProductService {

    private final UserProductRepository userProductRepository;

    // 🔍 Buscar com filtros
    public Page<UserProduct> findUserProducts(String name, Boolean type, Long categoryId, Long userId, Pageable pageable) {
        Specification<UserProduct> spec = (root, query, cb) -> cb.conjunction();

        if (name != null && !name.isBlank()) {
            spec = spec.and(UserProductSpecifications.hasName(name));
        }
        if (type != null) {
            spec = spec.and(UserProductSpecifications.hasType(type));
        }
        if (categoryId != null) {
            spec = spec.and(UserProductSpecifications.hasCategory(categoryId));
        }
        if (userId != null) {
            spec = spec.and(UserProductSpecifications.hasUser(userId));
        }

        return userProductRepository.findAll(spec, pageable);
    }

    // ➕ Criar
    public UserProduct save(UserProduct product) {
        return userProductRepository.save(product);
    }

    // 🔍 Buscar por ID
    public UserProduct findById(Long id) {
        return userProductRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado com ID: " + id));
    }

    // ✏️ Atualizar
    public UserProduct update(Long id, UserProduct updatedProduct) {
        UserProduct product = findById(id);
        product.setName(updatedProduct.getName());
        product.setType(updatedProduct.getType());
        product.setPrice(updatedProduct.getPrice());
        product.setDescription(updatedProduct.getDescription());
        product.setImage(updatedProduct.getImage());
        product.setCategory(updatedProduct.getCategory());
        product.setUser(updatedProduct.getUser());
        return userProductRepository.save(product);
    }

    // 🗑️ Deletar
    public void delete(Long id) {
        UserProduct product = findById(id);
        userProductRepository.delete(product);
    }
}