package com.codifica.compti.models.userproduct;

import java.util.List;

public interface UserProductService {
    UserProduct create(UserProduct userProduct, Long user_id, Long category_id);
    UserProduct update(UserProduct userProduct);
    UserProduct delete(UserProduct userProduct);
    UserProduct findById(int id);
    List<UserProduct> findAll();
    List<UserProductDTO> findByUserId(Long userId);
}
