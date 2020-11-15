package com.wigravy.market.services;


import com.wigravy.market.entities.Product;
import com.wigravy.market.entities.dtos.ProductDto;
import com.wigravy.market.exceptions.ProductNotFoundException;
import com.wigravy.market.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Product saveOrUpdate(Product product) {
        return productsRepository.save(product);
    }

    public Product findById(Long id) {
        return productsRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Can't find product with id = " + id));
    }

    public List<Product> findAll() {
        return productsRepository.findAll();
    }

    public Page<Product> findAll(Specification<Product> specification, Integer page) {
        if (page < 1) {
            page = 1;
        }
        return productsRepository.findAll(specification, PageRequest.of(page - 1, 10));
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public void deleteAll() {
        productsRepository.deleteAll();
    }

    public boolean isExistsByID(Long id) {
        return productsRepository.existsById(id);
    }

    public List<ProductDto> findAllDto() {
        return productsRepository.findAllBy();
    }
}
