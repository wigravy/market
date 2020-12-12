package com.wigravy.market.services;

import com.wigravy.market.entities.Category;
import com.wigravy.market.repositories.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {
    private CategoriesRepository categoriesRepository;

    @Autowired
    public void setCategoriesRepository(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public List<Category> findAll() {
        return categoriesRepository.findAll();
    }

    public List<Category> findAllById(List<Long> ids) {
        return categoriesRepository.findAllById(ids);
    }
}
