package com.codifica.compti.models.userproduct;


import com.codifica.compti.models.favorite.Favorites;
import com.codifica.compti.models.productcategory.ProductCategory;
import com.codifica.compti.models.user.User;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "UserProducts")
@Getter
@Setter
public class UserProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private ProductCategory category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorites> favorites;

    private String name;
    private Boolean type; // true = Product, false = Service
    private Double price;
    private String description;
    private String image; // Optional or required
}
