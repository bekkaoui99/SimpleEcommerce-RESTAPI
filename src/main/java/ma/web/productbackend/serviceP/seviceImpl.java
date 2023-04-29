package ma.web.productbackend.serviceP;

import lombok.AllArgsConstructor;
import ma.web.productbackend.entities.Product;
import ma.web.productbackend.repositories.productRepositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class seviceImpl implements  service{



    private productRepositories productRepositories;

    @Override
    public List<Product> getproductbyname(String name) {

        return productRepositories.findByNameContains(name);
    }

    @Override
    public List<Product> getSelectedP(Boolean selected) {
        return productRepositories.findBySelected(selected);
    }

    @Override
    public List<Product> getAvailble(Boolean availble) {
        return productRepositories.findByAvailble(availble);
    }

    @Override
    public Page<Product> getproductbynameByPage(String name, Pageable pageable) {
        return productRepositories.findByNameContains(name,pageable);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositories.findAll();
    }

    @Override
    public Page<Product> getAllProductsByPage(Pageable pageable) {
        return productRepositories.findAll(pageable);
    }

    @Override
    public Product getProductById(Long id) {
      Product product =  productRepositories.findById(id).orElse(null);
      if(product == null){
          try {
              throw new Exception("not found any product");
          } catch (Exception e) {
              throw new RuntimeException(e);
          }
      }
        return product;
    }

    @Override
    public Product updateProduc(Long id, Product product) {
        product.setId(id);

        return productRepositories.save(product);
    }

    @Override
    public void removeProduct(Long id) {
        productRepositories.deleteById(id);

    }

    @Override
    public Product saveProduct(Product product) {
        return productRepositories.save(product);
    }
}
