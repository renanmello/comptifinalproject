package com.codifica.compti.services;

import com.codifica.compti.models.profile.Profile;
import com.codifica.compti.repositories.ProfileRepository;
import com.codifica.compti.specifications.ProfileSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    // 🔍 Buscar perfis com filtros dinâmicos
    public Page<Profile> findProfiles(String theme, Boolean notificationsEnabled, Long userId, Pageable pageable) {
        Specification<Profile> spec = (root, query, cb) -> cb.conjunction();

        if (theme != null && !theme.isBlank()) {
            spec = spec.and(ProfileSpecifications.hasTheme(theme));
        }
        if (notificationsEnabled != null) {
            spec = spec.and(ProfileSpecifications.hasNotificationsEnabled(notificationsEnabled));
        }
        if (userId != null) {
            spec = spec.and(ProfileSpecifications.hasUserId(userId));
        }

        return profileRepository.findAll(spec, pageable);
    }

    // ➕ Criar perfil
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    // 🔍 Buscar por ID
    public Profile findById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Perfil não encontrado com ID: " + id));
    }

    // ✏️ Atualizar
    public Profile update(Long id, Profile updatedProfile) {
        Profile profile = findById(id);
        profile.setTheme(updatedProfile.getTheme());
        profile.setNotificationsEnabled(updatedProfile.getNotificationsEnabled());
        profile.setUser(updatedProfile.getUser());
        return profileRepository.save(profile);
    }

    // 🗑️ Deletar
    public void delete(Long id) {
        profileRepository.delete(findById(id));
    }
}