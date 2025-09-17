package com.codifica.compti.repositories;

import com.codifica.compti.models.userproduct.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserProductRepository extends JpaRepository<UserProduct, Long>, JpaSpecificationExecutor<UserProduct> {
}