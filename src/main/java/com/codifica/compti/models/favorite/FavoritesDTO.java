package com.codifica.compti.models.favorite;

import com.codifica.compti.models.userproduct.UserProductDTO;

public class FavoritesDTO {
    private Long id;
    private Long userId;
    private UserProductDTO product;

    public FavoritesDTO(Favorites favorite) {
        this.id = favorite.getId();
        this.userId = favorite.getUser().getId();
        this.product = new UserProductDTO(favorite.getProduct());

    }
}
