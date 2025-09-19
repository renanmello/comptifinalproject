package com.codifica.compti.services;

import com.codifica.compti.models.user.User;
import com.codifica.compti.repositories.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl  implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User create(User user) {
        // Encrypt the user's password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User view(Long id) {
        Optional<User> opuser = userRepository.findById(id);
        User user = opuser.get();
        // Check if the user exists and if the password is correct
        if (user.getId() != null) {
            return user;
        }
        return null;
    }

    @Override
    public User update(User user, Long id) {
        User edit_user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
        edit_user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(edit_user);
    }
}
