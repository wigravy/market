package com.wigravy.market.controllers;

import com.wigravy.market.entities.Product;
import com.wigravy.market.entities.dtos.ProductDto;
import com.wigravy.market.exceptions.ProductNotFoundException;
import com.wigravy.market.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")

public class RestProductsController {
    private ProductsService productsService;


    @Autowired
    public RestProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }


    @GetMapping
    public List<Product> getAllProducts() {
        return productsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        if (productsService.isExistsByID(id)) {
            return new ResponseEntity<>(productsService.findById(id), HttpStatus.OK);
        } else {
            throw new ProductNotFoundException("Product not found, id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public String deleteProductById(@PathVariable Long id) {
        productsService.deleteById(id);
        return "OK";
    }

    @DeleteMapping("/deleteAll")
    public String deleteAllProducts() {
        productsService.deleteAll();
        return "All done";
    }

    @PostMapping
    public ResponseEntity<?> saveNewProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            product.setId(null);
        }
        if (product.getPrice() < 0) {
            return new ResponseEntity<>("Product price can not be negative", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productsService.saveOrUpdate(product), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> modifyProduct(@RequestBody Product product) {
        if (product.getId() == null || !productsService.isExistsByID(product.getId())) {
            throw new ProductNotFoundException("Product not found, id: " + product.getId());
        }
        if (product.getPrice() < 0) {
            return new ResponseEntity<>("Product price can not be negative", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(productsService.saveOrUpdate(product), HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<?> exceptionHandler(ProductNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/dto")
    public List<ProductDto> getAllProductsDto() {
        return productsService.findAllDto();
    }
}
