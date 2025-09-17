package com.codifica.compti.specifications;

import com.codifica.compti.models.sellerrating.SellerRating;
import org.springframework.data.jpa.domain.Specification;

public class SellerRatingSpecifications {

    public static Specification<SellerRating> hasUserId(Long userId) {
        return (root, query, cb) -> cb.equal(root.get("user").get("id"), userId);
    }

    public static Specification<SellerRating> hasMinRating(Double minRating) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("totalRating"), minRating);
    }

    public static Specification<SellerRating> hasMaxRating(Double maxRating) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("totalRating"), maxRating);
    }
}