package com.wigravy.market.repositories.specification;


import com.wigravy.market.entities.Category;
import com.wigravy.market.entities.Product;
import org.springframework.data.jpa.domain.Specification;


public class ProductSpecification {
    public static Specification<Product> priceLesserOrEqualThan(Double maxPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    public static Specification<Product> priceGreaterOrEqualThan(Double minPrice) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> titleLike(String title) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", title));
    }

    public static Specification<Product> categoryIs(Category category) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isMember(category, root.get("categories"));
    }
}
