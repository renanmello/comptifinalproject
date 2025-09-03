package com.codifica.compti.userproduct;
import com.codifica.compti.productcategory.ProductCategory;
import com.codifica.compti.user.User;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

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

    private String name;
    private Boolean type; // true = Product, false = Service
    private Double price;
    private String description;
    private String image; // Optional or required
}
