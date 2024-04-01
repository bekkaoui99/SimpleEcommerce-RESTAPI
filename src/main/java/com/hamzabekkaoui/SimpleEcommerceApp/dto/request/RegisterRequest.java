package com.hamzabekkaoui.SimpleEcommerceApp.dto.request;

import lombok.Builder;

@Builder
public record RegisterRequest(
        String firstName,
        String lastName,
        String userName,
        String mail,
        String password,
        String confirmationPassword
) {
}
