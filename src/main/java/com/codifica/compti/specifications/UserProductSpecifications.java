package com.codifica.compti.specifications;

import com.codifica.compti.models.userproduct.UserProduct;
import org.springframework.data.jpa.domain.Specification;

public class UserProductSpecifications {

    public static Specification<UserProduct> hasName(String name) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<UserProduct> hasType(Boolean type) {
        return (root, query, cb) ->
                cb.equal(root.get("type"), type);
    }

    public static Specification<UserProduct> hasCategory(Long categoryId) {
        return (root, query, cb) ->
                cb.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<UserProduct> hasUser(Long userId) {
        return (root, query, cb) ->
                cb.equal(root.get("user").get("id"), userId);
    }
}