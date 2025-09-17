package com.codifica.compti.controllers;

import com.codifica.compti.models.profile.Profile;
import com.codifica.compti.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public Page<Profile> getProfiles(
            @RequestParam(required = false) String theme,
            @RequestParam(required = false) Boolean notificationsEnabled,
            @RequestParam(required = false) Long userId,
            Pageable pageable
    ) {
        return profileService.findProfiles(theme, notificationsEnabled, userId, pageable);
    }

    @GetMapping("/{id}")
    public Profile getProfile(@PathVariable Long id) {
        return profileService.findById(id);
    }

    @PostMapping
    public Profile createProfile(@RequestBody Profile profile) {
        return profileService.save(profile);
    }

    @PutMapping("/{id}")
    public Profile updateProfile(@PathVariable Long id, @RequestBody Profile profile) {
        return profileService.update(id, profile);
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable Long id) {
        profileService.delete(id);
    }
}