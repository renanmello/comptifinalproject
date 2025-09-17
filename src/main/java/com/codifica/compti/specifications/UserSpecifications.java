package com.codifica.compti.specifications;

import com.codifica.compti.models.user.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecifications {

    public static Specification<User> hasName(String name) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<User> hasEmail(String email) {
        return (root, query, cb) ->
                cb.equal(cb.lower(root.get("email")), email.toLowerCase());
    }

    public static Specification<User> hasType(Boolean type) {
        return (root, query, cb) ->
                cb.equal(root.get("type"), type);
    }
}