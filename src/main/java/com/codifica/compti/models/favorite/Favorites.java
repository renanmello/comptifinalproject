package com.codifica.compti.models.favorite;
import com.codifica.compti.models.user.User;
import com.codifica.compti.models.userproduct.UserProduct;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Favorites")
public class Favorites {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relationship with the user who added the favorite
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference // Prevents infinite loop when serializing the user
    private User user;

    // Relationship with the product/service that was favorited
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @JsonManagedReference // Allows serialization of the product
    private UserProduct product;

    // Custom constructor for easier instance creation
    public Favorites(User user, UserProduct product) {
        this.user = user;
        this.product = product;
    }
}