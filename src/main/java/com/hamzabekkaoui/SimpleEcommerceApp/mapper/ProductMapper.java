package com.hamzabekkaoui.SimpleEcommerceApp.mapper;

import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.ProductResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.entities.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public ProductResponse productToProductResponse(Product product){
        return ProductResponse.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .ProductImageLink(product.getProductImageLink())
                .available(product.isAvailable())
                .selected(product.isSelected())
                .categoryName(product.getCategory().getCategoryName())
                .build();
    }

}
