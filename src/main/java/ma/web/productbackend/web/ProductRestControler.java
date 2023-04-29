package ma.web.productbackend.web;



import ma.web.productbackend.entities.Product;
import ma.web.productbackend.serviceP.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController

public class ProductRestControler {
@Autowired
private service service;

    @GetMapping(path = "/products")
    public List<Product> getAllProduct(){

        return service.getAllProducts();

    }


    @GetMapping(path = "/product/{id}")
    public Product getOneProduct(@PathVariable Long id){
        return service.getProductById(id);
    }




    @PutMapping(path = "/product/{id}")
    public Product update(@PathVariable Long id,@RequestBody Product product){
        return service.updateProduc(id,product);
    }



    @DeleteMapping(path = "/product/{id}")
    public void delete( @PathVariable Long id){
        service.removeProduct(id);
    }

@GetMapping(path = "/product")
    public List<Product> sershProduct(@RequestParam(name = "name") String name){
        return service.getproductbyname(name);
}



    @GetMapping(path = "/product-slc")
    public List<Product> getSelectedP(@RequestParam(name = "selected") boolean selected){
        return service.getSelectedP(selected);
    }
    @GetMapping(path = "/product-avl")
    public List<Product> getAvailbleP(@RequestParam(name = "availble") boolean availble){
        return service.getAvailble(availble);
    }

@PostMapping(path = "/save")
    public Product saveProduct(@RequestBody Product product){
        return service.saveProduct(product);
}



}
