package com.codifica.compti.repositories;


import com.codifica.compti.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Busca um usuário pelo login fornecido.
     *
     * @param login o login do usuário a ser encontrado
     * @return o objeto {@link UserDetails} do usuário correspondente
     */
    UserDetails findByEmail(String login);
    /**
     * Salva um usuário no banco de dados.
     *
     * @param user o objeto {@link User} a ser salvo
     * @return o objeto {@link User} recém-salvo
     */
    User save(User user);
}
