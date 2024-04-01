package com.hamzabekkaoui.SimpleEcommerceApp.repositories;

import com.hamzabekkaoui.SimpleEcommerceApp.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category , Long> {

    Optional<Category> findByCategoryName(String CategoryName);
}
