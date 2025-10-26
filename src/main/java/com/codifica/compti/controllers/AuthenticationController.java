package com.codifica.compti.controllers;


import com.codifica.compti.dto.LoginRequestDTO;
import com.codifica.compti.dto.LoginResponseDTO;
import com.codifica.compti.dto.RegisterDTO;
import com.codifica.compti.dto.RegisterResponseDTO;
import com.codifica.compti.models.user.User;
import com.codifica.compti.models.user.UserRepository;
import com.codifica.compti.models.user.UserServiceImpl;
import com.codifica.compti.services.TokenService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


/**
 * Controlador REST para autenticação e registro de usuários.
 * <p>
 * Este controlador fornece endpoints para login e registro de novos usuários,
 * incluindo a geração de tokens JWT para autenticação.
 * </p>
 *
 * @version 2.0
 * @since 2025
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;
    
    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Realiza a autenticação de um usuário e retorna um token JWT se as credenciais forem válidas.
     *
     * @param data objeto {@link AuthenticationDTO} contendo as credenciais do usuário
     * @return uma resposta HTTP com o token JWT, as autoridades do usuário e seu ID se a autenticação for bem-sucedida
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        User user = (User) userRepository.findByEmail(data.login());

        // Gera um token JWT para o usuário autenticado
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token, auth.getAuthorities(), user.getId()));
    }

    /**
     * Registra um novo usuário no sistema com as informações fornecidas.
     *
     * @param data objeto {@link RegisterDTO} contendo as informações de login, senha e papel do usuário
     * @return uma resposta HTTP com o ID e o login do novo usuário, ou erro 400 se o login já existir
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {
        User newUser = userServiceImpl.register(data);
        return ResponseEntity.ok(new RegisterResponseDTO(newUser.getId(), newUser.getEmail()));
    }

    @PutMapping("/update/{user_id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable("user_id") Long user_id) {
       User updatedUser = userServiceImpl.update(user, user_id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<User> delete(@PathVariable("user_id") Long id) {
        userServiceImpl.delete(id);
        return ResponseEntity.ok().build();
    }
}


