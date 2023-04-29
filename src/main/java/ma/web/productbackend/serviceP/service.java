package ma.web.productbackend.serviceP;

import ma.web.productbackend.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface service {
    List<Product> getproductbyname(String name );

    List<Product> getSelectedP(Boolean selected);

    List<Product> getAvailble(Boolean availble);
    Page<Product> getproductbynameByPage(String name , Pageable pageable);
    List<Product> getAllProducts();
    Page<Product> getAllProductsByPage(Pageable pageable);
    Product getProductById(Long id);
    Product updateProduc(Long id , Product product);
    void removeProduct(Long id);
    Product saveProduct( Product product);



}
