package com.wigravy.market.utils;

import com.wigravy.market.entities.Category;
import com.wigravy.market.entities.Product;
import com.wigravy.market.repositories.specification.ProductSpecification;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Product> spec;
    private StringBuilder filterDefinition;

    public ProductFilter(Map<String, String> map, List<Category> categories) {
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();
        if (map.containsKey("min_price") && !map.get("min_price").isEmpty()) {
            double minPrice = Double.parseDouble(map.get("min_price"));
            spec = spec.and(ProductSpecification.priceGreaterOrEqualThan(minPrice));
            filterDefinition.append("&min_price=").append(minPrice);
        }
        if (map.containsKey("max_price") && !map.get("max_price").isEmpty()) {
            double maxPrice = Double.parseDouble(map.get("max_price"));
            spec = spec.and(ProductSpecification.priceLesserOrEqualThan(maxPrice));
            filterDefinition.append("&max_price=").append(maxPrice);
        }
        if (map.containsKey("title") && !map.get("title").isEmpty()) {
            String title = map.get("title");
            spec = spec.and((ProductSpecification.titleLike(title)));
            filterDefinition.append("&title=").append(title);
        }
        if (categories != null && !categories.isEmpty()) {
            Specification<Product> categorySpec = null;
            for (Category c : categories) {
                if (categorySpec == null) {
                    categorySpec = ProductSpecification.categoryIs(c);
                } else {
                    categorySpec = categorySpec.or(ProductSpecification.categoryIs(c));
                }
            }
            spec = spec.and(categorySpec);
        }

    }
}
