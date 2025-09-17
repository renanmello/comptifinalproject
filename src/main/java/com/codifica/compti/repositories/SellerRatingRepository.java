package com.codifica.compti.repositories;

import com.codifica.compti.models.sellerrating.SellerRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SellerRatingRepository extends JpaRepository<SellerRating, Long>, JpaSpecificationExecutor<SellerRating> {
}