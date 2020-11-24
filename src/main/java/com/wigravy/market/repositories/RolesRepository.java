package com.wigravy.market.repositories;

import com.wigravy.market.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {
    Role findOneByName(String name);
}
