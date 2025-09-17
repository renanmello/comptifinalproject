package com.codifica.compti.specifications;

import com.codifica.compti.models.profile.Profile;
import org.springframework.data.jpa.domain.Specification;

public class ProfileSpecifications {

    public static Specification<Profile> hasTheme(String theme) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("theme")), "%" + theme.toLowerCase() + "%");
    }

    public static Specification<Profile> hasNotificationsEnabled(Boolean notificationsEnabled) {
        return (root, query, cb) -> cb.equal(root.get("notificationsEnabled"), notificationsEnabled);
    }

    public static Specification<Profile> hasUserId(Long userId) {
        return (root, query, cb) -> cb.equal(root.get("user").get("id"), userId);
    }
}