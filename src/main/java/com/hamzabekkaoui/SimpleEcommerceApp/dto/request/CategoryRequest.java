package com.hamzabekkaoui.SimpleEcommerceApp.dto.request;

import lombok.Builder;

@Builder
public record CategoryRequest(
        String categoryName,

        String categoryDescription

) {
}
