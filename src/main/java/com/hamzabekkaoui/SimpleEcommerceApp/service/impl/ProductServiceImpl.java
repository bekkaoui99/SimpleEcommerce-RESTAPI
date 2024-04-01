package com.hamzabekkaoui.SimpleEcommerceApp.service.impl;

import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.ProductRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.ProductResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.entities.Category;
import com.hamzabekkaoui.SimpleEcommerceApp.entities.Product;
import com.hamzabekkaoui.SimpleEcommerceApp.exeption.ResourceAlreadyExist;
import com.hamzabekkaoui.SimpleEcommerceApp.exeption.ResourceNotFoundException;
import com.hamzabekkaoui.SimpleEcommerceApp.mapper.ProductMapper;

import com.hamzabekkaoui.SimpleEcommerceApp.repositories.CategoryRepository;
import com.hamzabekkaoui.SimpleEcommerceApp.repositories.ProductRepository;
import com.hamzabekkaoui.SimpleEcommerceApp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse getProductByName(String name) throws ResourceNotFoundException {

        Product product = productRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("product doesn't exist"));
        return productMapper.productToProductResponse(product);
    }

    @Override
    public List<ProductResponse> getListOfSelectedProduct() {
        List<Product> productList = productRepository.findBySelectedIsTrue();
        return productList.stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductResponse> getPageOfSelectedProduct(int pageNumber, int pageSize) {

        Page<Product> selectedProducts = productRepository.findBySelectedIsTrue(PageRequest.of(pageNumber, pageSize));

        List<ProductResponse> productResponseList = selectedProducts.getContent()
                .stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(productResponseList , selectedProducts.getPageable() , selectedProducts.getTotalElements());
    }


    @Override
    public List<ProductResponse> getListOfNotSelectedProduct() {

        List<Product> notSelectedProductList = productRepository.findBySelectedIsFalse();

        return notSelectedProductList.stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductResponse> getPageOfNotSelectedProduct(int pageNumber, int pageSize) {
        Page<Product> notSelectedProducts = productRepository.findBySelectedIsFalse(PageRequest.of(pageNumber, pageSize));

        List<ProductResponse> productResponseList = notSelectedProducts.getContent()
                .stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(productResponseList , notSelectedProducts.getPageable() , notSelectedProducts.getTotalElements());
    }

    @Override
    public List<ProductResponse> getListOfAvailableProducts() {
        List<Product> availableProducts = productRepository.findByAvailableIsTrue();
        return availableProducts.stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductResponse> getPageOfAvailableProducts(int pageNumber, int pageSize) {

        Page<Product> availableProducts = productRepository.findByAvailableIsTrue(PageRequest.of(pageNumber, pageSize));

        List<ProductResponse> productResponseList = availableProducts.getContent()
                .stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(productResponseList , availableProducts.getPageable() , availableProducts.getTotalElements());
    }


    @Override
    public List<ProductResponse> getListOfProductsByNameContain(String name) {
        List<Product> productListByName = productRepository.findByNameContains(name);
        return productListByName.stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductResponse> getPageOfProductsByNameContain(String name, int pageNumber, int pageSize) {
        Page<Product> pageOfProductsByName = productRepository.findByNameContains(PageRequest.of(pageNumber, pageSize), name);

        List<ProductResponse> productResponseList = pageOfProductsByName.getContent()
                .stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(productResponseList , pageOfProductsByName.getPageable() , pageOfProductsByName.getTotalElements());
    }


    @Override
    public List<ProductResponse> getListOfProducts() {
        List<Product> productList = productRepository.findAll();

        return productList.stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductResponse> getPageOfProducts(int pageNumber, int pageSize) {
        Page<Product> pageOfProducts = productRepository.findAll(PageRequest.of(pageNumber, pageSize));
        List<ProductResponse> productResponseList = pageOfProducts.getContent()
                .stream()
                .map(productMapper::productToProductResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(productResponseList , pageOfProducts.getPageable() , pageOfProducts.getTotalElements());
    }


    @Override
    public ProductResponse getProductById(Long id) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product doesn't exist"));
        return productMapper.productToProductResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product doesn't exist"));
        if(productRequest.name() != null && !productRequest.name().equals(product.getName()))
            product.setName(productRequest.name());
        if(productRequest.price() != product.getPrice() )
            product.setPrice(productRequest.price());
        if(productRequest.quantity() != product.getQuantity() )
            product.setQuantity(productRequest.quantity());
        if(productRequest.selected() != null )
            product.setSelected(productRequest.selected());
        if(productRequest.available() != null )
            product.setAvailable(productRequest.available());
        if(productRequest.productImageLink() != null && !productRequest.productImageLink().equals(product.getProductImageLink()) )
            product.setProductImageLink(productRequest.productImageLink());
        if(productRequest.categoryName() != null && !productRequest.categoryName().equals(product.getCategory().getCategoryName()) ) {
            Category category = categoryRepository.findByCategoryName(productRequest.categoryName())
                    .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));
            product.setCategory(category);
        }
        Product updated = productRepository.save(product);
        return productMapper.productToProductResponse(updated);
    }

    @Override
    public boolean removeProduct(Long id) throws ResourceNotFoundException {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("product doesn't exist"));
         productRepository.delete(product);
         return true;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) throws ResourceAlreadyExist {
        productRepository.findByName(productRequest.name())
                .orElseThrow(() -> new ResourceAlreadyExist("product already exist with this information"));

        Category category = categoryRepository.findByCategoryName(productRequest.categoryName())
                .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));

        Product product = Product.builder()
                .name(productRequest.name())
                .price(productRequest.price())
                .quantity(productRequest.quantity())
                .productImageLink(productRequest.productImageLink())
                .category(category)
                .available(productRequest.available())
                .selected(productRequest.selected())
                .build();

        Product created = productRepository.save(product);

        return productMapper.productToProductResponse(created);

    }
}
