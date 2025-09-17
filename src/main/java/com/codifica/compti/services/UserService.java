package com.codifica.compti.services;

import com.codifica.compti.models.user.User;
import com.codifica.compti.repositories.UserRepository;
import com.codifica.compti.specifications.UserSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 🔍 Buscar usuários com filtros dinâmicos
    public Page<User> findUsers(String name, String email, Boolean type, Pageable pageable) {
        Specification<User> spec = (root, query, cb) -> cb.conjunction();

        if (name != null && !name.isBlank()) {
            spec = spec.and(UserSpecifications.hasName(name));
        }
        if (email != null && !email.isBlank()) {
            spec = spec.and(UserSpecifications.hasEmail(email));
        }
        if (type != null) {
            spec = spec.and(UserSpecifications.hasType(type));
        }

        return userRepository.findAll(spec, pageable);
    }

    // ➕ Criar usuário
    public User save(User user) {
        return userRepository.save(user);
    }

    // 🔍 Buscar por ID
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado com ID: " + id));
    }

    // ✏️ Atualizar usuário
    public User update(Long id, User updatedUser) {
        User user = findById(id);
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setWhatsapp(updatedUser.getWhatsapp());
        user.setSocialMediaLink(updatedUser.getSocialMediaLink());
        user.setZipCode(updatedUser.getZipCode());
        user.setAddressComplement(updatedUser.getAddressComplement());
        user.setType(updatedUser.getType());
        user.setDocument(updatedUser.getDocument());
        user.setPhoto(updatedUser.getPhoto());
        return userRepository.save(user);
    }

    // 🗑️ Deletar usuário
    public void delete(Long id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}