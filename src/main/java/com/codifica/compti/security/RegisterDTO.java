package com.codifica.compti.security;

import com.codifica.compti.models.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
