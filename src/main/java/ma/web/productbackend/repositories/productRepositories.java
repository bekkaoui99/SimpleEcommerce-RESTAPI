package ma.web.productbackend.repositories;

import ma.web.productbackend.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface productRepositories extends JpaRepository<Product,Long> {

    Page<Product> findByNameContains(String name , Pageable pageable);
    Product findByNameContains(String name);

    List<Product> findBySelected(boolean selected);

    List<Product> findByAvailble(boolean availble);

    @Override
    Page<Product> findAll(Pageable pageable);
}
