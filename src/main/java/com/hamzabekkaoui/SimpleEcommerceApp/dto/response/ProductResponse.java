package com.hamzabekkaoui.SimpleEcommerceApp.dto.response;


import lombok.Builder;

@Builder
public record ProductResponse(
         Long productId,
         String name,
         String ProductImageLink,
         Double price,
         double quantity,
         boolean selected,
         boolean available,
         String categoryName
) {
}
