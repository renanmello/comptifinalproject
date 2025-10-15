package com.codifica.compti.models.productcategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    ProductCategory findAllBy(ProductCategory productCategory);
}
