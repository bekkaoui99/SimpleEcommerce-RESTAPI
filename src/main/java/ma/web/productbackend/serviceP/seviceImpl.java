package ma.web.productbackend.serviceP;

import lombok.AllArgsConstructor;
import ma.web.productbackend.entities.Product;
import ma.web.productbackend.exeption.ProductNotFoundExeption;
import ma.web.productbackend.repositories.productRepositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class seviceImpl implements  service{



    private productRepositories productRepositories;

    @Override
    public Product getproductbyname(String name) {

        return productRepositories.findAll()
                .stream().
                filter(product -> product.getName().equals(name)).
                findFirst().orElseThrow(() -> new ProductNotFoundExeption("product not Found exeption"));

    }

    @Override
    public Page<Product> getproductsbyname(String name, Pageable pageable) {
        return  this.productRepositories.findByNameContains(name, pageable);
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
    public Product getProductById(Long id)  {
      Product product =  productRepositories.findById(id)
              .orElseThrow(() -> new ProductNotFoundExeption("Product not found exeption"));

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
