package com.wigravy.market.services;

import com.wigravy.market.entities.Role;
import com.wigravy.market.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService {
    private RolesRepository rolesRepository;

    @Autowired
    public void setRolesRepository(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Role findByName(String roleName) {
        return rolesRepository.findOneByName(roleName);
    }
}
