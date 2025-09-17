package com.codifica.compti.specifications;

import com.codifica.compti.models.productcategory.ProductCategory;
import org.springframework.data.jpa.domain.Specification;

public class ProductCategorySpecifications {

    public static Specification<ProductCategory> hasName(String name) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }
}