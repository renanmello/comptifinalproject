package com.codifica.compti.security;

import java.util.Collection;

public record LoginResponseDTO(String token, Collection role, Long id) {
}
