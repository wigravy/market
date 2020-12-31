package com.wigravy.market.repositoryTests;


import com.wigravy.market.entities.Product;
import com.wigravy.market.exceptions.ResourceNotFoundException;
import com.wigravy.market.repositories.ProductsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class ProductTest {
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void addNewProductTest() {
        Product product = new Product();
        product.setTitle("Test");
        product.setPrice(new BigDecimal("12.99"));

        entityManager.persist(product);
        entityManager.flush();

        List<Product> products = productsRepository.findAll();
        Assertions.assertEquals(4, products.size());
    }

    @Test
    public void deleteProductFromDbTest() {
        productsRepository.deleteById(1L);
        List<Product> products = productsRepository.findAll();
        Assertions.assertEquals(2, products.size());
    }

    @Test
    public void findOneByIdTest() {
        Product product = productsRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Product with id 1 didn't exist."));
        Assertions.assertEquals(product.getTitle(), "Pear");

    }

}
