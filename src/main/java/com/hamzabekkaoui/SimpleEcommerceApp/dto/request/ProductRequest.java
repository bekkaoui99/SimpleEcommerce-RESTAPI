package com.hamzabekkaoui.SimpleEcommerceApp.dto.request;

import lombok.Builder;

@Builder
public record ProductRequest(

        String name,
        String productImageLink,
        double price,
        double quantity,
        Boolean selected,
        Boolean available,
        String categoryName
) {
}
