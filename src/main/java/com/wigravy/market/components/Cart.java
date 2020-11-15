package com.wigravy.market.components;

import com.wigravy.market.entities.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {
    private Map<Product, Integer> productsList;


    @PostConstruct
    public void init() {
        productsList = new HashMap<>();
    }

    public void addProduct(Product product) {
        productsList.merge(product, 1, Integer::sum);
    }

    public void deleteProduct(Product product) {
        if (productsList.get(product) > 1) {
            productsList.merge(product, 1, (a,b) -> a - 1);
        } else {
            productsList.remove(product);
        }
    }

    public Map<Product, Integer> getProductsList() {
        return productsList;
    }

    public void clear() {
        productsList.clear();
    }
}
