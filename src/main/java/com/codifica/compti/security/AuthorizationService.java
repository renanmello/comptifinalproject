package com.codifica.compti.security;

import com.codifica.compti.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    /**
     * Carrega os detalhes do usuário com base no nome de usuário fornecido.
     *
     * @param username o nome de usuário do usuário a ser autenticado
     * @return um objeto {@link UserDetails} contendo as informações do usuário
     * @throws UsernameNotFoundException se o usuário com o nome fornecido não for encontrado
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
}
