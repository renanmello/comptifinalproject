package com.codifica.compti.services;

import com.codifica.compti.models.sellerrating.SellerRating;
import com.codifica.compti.repositories.SellerRatingRepository;
import com.codifica.compti.specifications.SellerRatingSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SellerRatingService {

    private final SellerRatingRepository sellerRatingRepository;

    // 🔍 Buscar avaliações com filtros dinâmicos
    public Page<SellerRating> findSellerRatings(Long userId, Double minRating, Double maxRating, String review, Pageable pageable) {
        Specification<SellerRating> spec = (root, query, cb) -> cb.conjunction();

        if (userId != null) {
            spec = spec.and(SellerRatingSpecifications.hasUserId(userId));
        }
        if (minRating != null) {
            spec = spec.and(SellerRatingSpecifications.hasMinRating(minRating));
        }
        if (maxRating != null) {
            spec = spec.and(SellerRatingSpecifications.hasMaxRating(maxRating));
        }
        if (review != null && !review.isBlank()) {
            spec = spec.and(SellerRatingSpecifications.containsReview(review));
        }

        return sellerRatingRepository.findAll(spec, pageable);
    }

    // ➕ Criar
    public SellerRating save(SellerRating sellerRating) {
        return sellerRatingRepository.save(sellerRating);
    }

    // 🔍 Buscar por ID
    public SellerRating findById(Long id) {
        return sellerRatingRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("SellerRating não encontrado com ID: " + id));
    }

    // ✏️ Atualizar
    public SellerRating update(Long id, SellerRating updated) {
        SellerRating sellerRating = findById(id);
        sellerRating.setUser(updated.getUser());
        sellerRating.setTotalRating(updated.getTotalRating());
        sellerRating.setReviewCount(updated.getReviewCount());
        sellerRating.setReview(updated.getReview());
        return sellerRatingRepository.save(sellerRating);
    }

    // 🗑️ Deletar
    public void delete(Long id) {
        sellerRatingRepository.delete(findById(id));
    }
}