package com.codifica.compti.models.userproduct;

import com.codifica.compti.models.productcategory.ProductCategory;
import com.codifica.compti.models.productcategory.ProductCategoryRepository;
import com.codifica.compti.models.user.User;
import com.codifica.compti.models.user.UserRepository;
import com.codifica.compti.models.user.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserProductServiceImpl implements UserProductService {

    private final UserRepository userRepository;
    private final UserProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public UserProduct create(UserProduct userProduct, Long user_id, Long category_id) {
        // Busca o usuário
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Validação: Apenas BUSINESS pode criar produtos
        if (user.getRole() != UserRole.BUSINESS) {
            throw new IllegalArgumentException(
                    "Apenas usuários do tipo BUSINESS (CNPJ) podem criar produtos. " +
                            "Usuários INDIVIDUAL (CPF) podem apenas favoritar produtos."
            );
        }

        // Busca a categoria
        ProductCategory category = productCategoryRepository.findById(category_id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        // Configura o produto
        userProduct.setUser(user);
        userProduct.setCategory(category);

        // Salva e retorna o produto
        return productRepository.save(userProduct);
    }

    @Override
    public UserProduct update(UserProduct userProduct) {
        // TODO: Implementar atualização
        return null;
    }

    @Override
    public UserProduct delete(UserProduct userProduct) {
        // TODO: Implementar exclusão
        return null;
    }

    @Override
    public UserProduct findById(int id) {
        // TODO: Implementar busca por ID
        return null;
    }

    @Override
    public List<UserProduct> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<UserProductDTO> findByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Valida se é BUSINESS
        if (user.getRole() != UserRole.BUSINESS) {
            throw new IllegalArgumentException(
                    "Usuários INDIVIDUAL (CPF) não possuem produtos."
            );
        }

        return productRepository.findByUserId(userId).stream()
                .map(UserProductDTO::new)
                .collect(Collectors.toList());
    }
}
