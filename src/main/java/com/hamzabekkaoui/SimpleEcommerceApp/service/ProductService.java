package com.hamzabekkaoui.SimpleEcommerceApp.service;

import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.ProductRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.ProductResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.entities.Product;
import com.hamzabekkaoui.SimpleEcommerceApp.exeption.ResourceAlreadyExist;
import com.hamzabekkaoui.SimpleEcommerceApp.exeption.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    ProductResponse getProductByName(String name) throws ResourceNotFoundException;

    List<ProductResponse> getListOfSelectedProduct();
    Page<ProductResponse> getPageOfSelectedProduct(int pageNumber , int pageSize);

    List<ProductResponse> getListOfNotSelectedProduct();
    Page<ProductResponse> getPageOfNotSelectedProduct(int pageNumber , int pageSize);

    List<ProductResponse> getListOfAvailableProducts();
    Page<ProductResponse> getPageOfAvailableProducts(int pageNumber , int pageSize);

    List<ProductResponse> getListOfProductsByNameContain(String name);
    Page<ProductResponse> getPageOfProductsByNameContain(String name , int pageNumber , int pageSize);

    List<ProductResponse> getListOfProducts();
    Page<ProductResponse> getPageOfProducts(int pageNumber , int pageSize);
    ProductResponse getProductById(Long id) throws ResourceNotFoundException;

    ProductResponse updateProduct(Long id , ProductRequest productRequest) throws ResourceNotFoundException;
    boolean removeProduct(Long id) throws ResourceNotFoundException;
    ProductResponse createProduct(ProductRequest productRequest) throws ResourceAlreadyExist;



}
