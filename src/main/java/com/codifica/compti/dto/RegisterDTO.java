package com.codifica.compti.dto;

import com.codifica.compti.models.user.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (DTO) para representar os dados de registro de um novo usuário.
 * <p>
 * Esta classe encapsula as informações necessárias para registrar um usuário, incluindo
 * o login, a senha e o papel (role) do usuário.
 * </p>
 *
 * @param login    o nome de login do usuário
 * @param password a senha do usuário
 * @param role     o papel do usuário, representado por {@link UserRole}
 * @version 2.0
 * @since 2024
 */
public record RegisterDTO(
    @NotBlank(message = "login field is required") 
    String login, 
    @NotBlank(message = "password field is required")
    String password, 
    @NotNull
    UserRole role, 
    @NotBlank(message = "name field is required")
    String name, 
    @NotBlank(message = "whatsapp field is required")
    String whatsapp,
    @NotBlank(message = "social_media_link is required")
    String social_media_link,
    @NotBlank(message = "zip_code is required")
    String zip_code,
    @NotBlank(message = "address_complement is required") 
    String address_complement, 
    @NotBlank(message = "document is required")
    String document,
    @NotBlank(message = "photo is required")
    String photo) {
}
