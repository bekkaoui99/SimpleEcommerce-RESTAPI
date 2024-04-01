package com.hamzabekkaoui.SimpleEcommerceApp.dto.response;

import lombok.Builder;

@Builder
public record CategoryResponse(

        Long categoryId,
        String categoryName,

        String categoryDescription
) {
}
