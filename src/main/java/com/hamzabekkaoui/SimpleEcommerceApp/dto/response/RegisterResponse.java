package com.hamzabekkaoui.SimpleEcommerceApp.dto.response;

import lombok.Builder;

@Builder
public record RegisterResponse(

        String firstName,
        String lastName,
        String userName,
        String mail

) {
}
