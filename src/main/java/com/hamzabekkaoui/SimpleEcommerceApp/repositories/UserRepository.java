package com.hamzabekkaoui.SimpleEcommerceApp.repositories;

import com.hamzabekkaoui.SimpleEcommerceApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {
    Optional<User> findByMail(String mail);

    boolean existsByUserName(String userName);
    boolean existsByMail(String userName);
    Optional<User> findByUserName(String userName);
}
