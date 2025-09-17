package com.codifica.compti.specifications;

import com.codifica.compti.models.negotiation.Negotiation;
import org.springframework.data.jpa.domain.Specification;

public class NegotiationSpecifications {

    public static Specification<Negotiation> hasBuyerId(Long buyerId) {
        return (root, query, cb) -> cb.equal(root.get("buyer").get("id"), buyerId);
    }

    public static Specification<Negotiation> hasProductId(Long productId) {
        return (root, query, cb) -> cb.equal(root.get("product").get("id"), productId);
    }

    public static Specification<Negotiation> isCompleted(Boolean completed) {
        return (root, query, cb) -> cb.equal(root.get("completed"), completed);
    }

    public static Specification<Negotiation> hasRating(Integer rating) {
        return (root, query, cb) -> cb.equal(root.get("rating"), rating);
    }
}