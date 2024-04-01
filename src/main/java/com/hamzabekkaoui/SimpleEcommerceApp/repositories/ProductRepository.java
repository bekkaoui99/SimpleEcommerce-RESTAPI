package com.hamzabekkaoui.SimpleEcommerceApp.repositories;

import com.hamzabekkaoui.SimpleEcommerceApp.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product,Long> {

    Optional<Product> findByName(String name);

    Page<Product> findByNameContains(Pageable pageable , String name);
    List<Product> findByNameContains(String name);

    Page<Product> findBySelectedIsTrue(Pageable pageable);
    List<Product> findBySelectedIsTrue();

    Page<Product> findBySelectedIsFalse(Pageable pageable);
    List<Product> findBySelectedIsFalse();

    Page<Product> findByAvailableIsTrue(Pageable pageable);
    List<Product> findByAvailableIsTrue();


}
