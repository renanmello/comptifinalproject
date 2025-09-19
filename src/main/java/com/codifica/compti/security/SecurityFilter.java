package com.codifica.compti.security;

import com.codifica.compti.repositories.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Filtro de segurança que intercepta requisições HTTP e aplica autenticação
 * baseada em token JWT, configurando o contexto de segurança do Spring Security.
 * <p>
 * Este filtro ignora endpoints de documentação Swagger para evitar autenticação nesses caminhos.
 * </p>
 *
 * @version 2.0
 * @since 2024
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

    /**
     * Extrai o token JWT do cabeçalho Authorization da requisição HTTP.
     *
     * @param request a requisição HTTP
     * @return o token JWT, ou null se não estiver presente ou mal formatado
     */



    private String recoverToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring(7); // Remove "Bearer " para obter o token puro
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Ignora requisições para os endpoints do Swagger UI e favicon
        String requestURI = request.getRequestURI();
        logger.info("Processando requisição URI: {}", requestURI);
        if (requestURI.startsWith("/swagger-ui") || requestURI.startsWith("/v3/api-docs") || requestURI.equals("/favicon.ico")) {
            logger.info("Ignorando autenticação para URI: {}", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        var token = this.recoverToken(request);


        if (token != null) {
            logger.info("Token encontrado, validando: {}", token);

            var email = tokenService.validateToken(token); //essa linha nao acha o metodo

            UserDetails user = userRepository.findByEmail(email);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            logger.info("Autenticação bem-sucedida para o usuário: {}", email);
        } else {
            logger.warn("Token inválido ou expirado.");
        }
        filterChain.doFilter(request, response);
    }

}
