package com.wigravy.market.services;


import com.wigravy.market.exceptions.ProductNotFoundException;
import com.wigravy.market.entities.Product;
import com.wigravy.market.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Product getById(Long id) {
        return productsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Can't find product with id = " + id));
    }

    public Product findByTitle(String title) {
        return productsRepository.findOneByTitle(title);
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public Page<Product> findAll(Specification<Product> specification, Integer page) {
        if (page < 1) {
            page = 1;
        }
        return productsRepository.findAll(specification, PageRequest.of(page - 1, 5));
    }
}
