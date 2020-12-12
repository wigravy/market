package com.wigravy.market.repositories;

import com.wigravy.market.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByPhone(String phone);
    boolean existsByPhone(String phone);
}
