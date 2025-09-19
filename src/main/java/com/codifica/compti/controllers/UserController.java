package com.codifica.compti.controllers;

import com.codifica.compti.models.user.User;
import com.codifica.compti.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")

public class UserController {

    private final UserService userService;

    // Construtor explícito
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Cria um novo usuário.
     *
     * @param user o objeto {@link User} contendo os dados do novo usuário
     * @return o objeto {@link User} recém-criado
     */
    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);

    }

    /**
     * Atualiza um usuário existente com base no ID fornecido.
     *
     * @param user   o objeto {@link User} contendo os dados atualizados do usuário
     * @param userId o ID do usuário a ser atualizado
     * @return um {@link ResponseEntity} contendo o objeto {@link User} atualizado
     */
    @PutMapping("update/{userId}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.update(user, userId));

    }
}
