package com.hamzabekkaoui.SimpleEcommerceApp.web;



import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.CategoryRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.ProductRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.CategoryResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.ProductResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.service.ProductService;


import com.hamzabekkaoui.SimpleEcommerceApp.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductServiceImpl productService;


    @GetMapping("/list")
    public List<ProductResponse> getAllProducts(){
        return productService.getListOfProducts();
    }

    @GetMapping("/page")
    public Page<ProductResponse> getPageOfProducts(
            @RequestParam(name = "pageNumber" , defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize" , defaultValue = "0") int pageSize
    ){
        return productService.getPageOfProducts(pageNumber , pageSize);
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable(name = "id") Long id){
        return productService.getProductById(id);
    }

    @GetMapping("/name")
    public ProductResponse getProductByName(@PathVariable(name = "name") String name){
        return productService.getProductByName(name);
    }

    @PostMapping
    public ProductResponse createProducts(@RequestBody ProductRequest productRequest){
        return productService.createProduct(productRequest);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProducts(@PathVariable Long id , @RequestBody ProductRequest productRequest){
        return productService.updateProduct(id , productRequest);
    }

    @DeleteMapping("/{id}")
    public boolean deleteProducts(@PathVariable Long id){
        return productService.removeProduct(id);
    }


}
