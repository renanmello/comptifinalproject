package com.codifica.compti.models.user;

import lombok.Getter;

/**
 * Enumeration representing the different user roles in the system.
 * <p>
 * Each role defines a specific level of access and available functionalities for the user.
 * </p>
 *
 * @version 1.0
 * @since 2025
 */
@Getter
public enum UserRole {

    /**
     * Admin role, with full permissions on the system.
     */
    ADMIN("admin"),
    /**
     * individual role, com permissões completas no sistema.
     */
    INDIVIDUAL("individual"),
    /**
     * Company role, with permissions to manage company resources.
     */
    BUSINESS("business");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

}
