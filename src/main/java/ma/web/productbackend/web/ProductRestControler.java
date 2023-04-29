package ma.web.productbackend.web;



import ma.web.productbackend.entities.Product;
import ma.web.productbackend.payloads.ApiResponse;
import ma.web.productbackend.serviceP.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Product> getOneProduct(@PathVariable Long id){
        Product productById = this.service.getProductById(id);
        return ResponseEntity.ok(productById);
    }




    @PutMapping(path = "/product/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id,@RequestBody Product product){
        Product productUpdated = service.updateProduc(id, product);
        return new  ResponseEntity<>(productUpdated, HttpStatus.OK);
    }



    @DeleteMapping(path = "/product/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id){
        service.removeProduct(id);
        return new ResponseEntity<>(new ApiResponse("user deleted seccusefully",true),HttpStatus.OK);
    }

    @GetMapping(path = "/product")
        public ResponseEntity<Product> sershProduct(@RequestParam(name = "name") String name){
        Product getproductbyname = service.getproductbyname(name);
        return ResponseEntity.ok(getproductbyname);
    }



    @GetMapping(path = "/product-slc")
    public ResponseEntity<List<Product>> getSelectedP(@RequestParam(name = "selected") boolean selected){
        List<Product> selectedProducts = service.getSelectedP(selected);
        return ResponseEntity.ok(selectedProducts);
    }

    @GetMapping(path = "/product-avl")
    public ResponseEntity<List<Product>> getAvailbleP(@RequestParam(name = "availble") boolean availble){

        List<Product> availbleProducts = service.getAvailble(availble);
        return ResponseEntity.ok(availbleProducts);
    }

    @PostMapping(path = "/save")
        public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        Product createdProduct = service.saveProduct(product);
        return new ResponseEntity<>(createdProduct , HttpStatus.OK);
    }



    }
