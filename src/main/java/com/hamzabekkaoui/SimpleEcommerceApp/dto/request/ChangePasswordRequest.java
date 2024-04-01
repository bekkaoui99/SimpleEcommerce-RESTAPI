package com.hamzabekkaoui.SimpleEcommerceApp.dto.request;

import lombok.Builder;

@Builder
public record ChangePasswordRequest(
        String mail,
        String password,
        String confirmationPassword
) {
}
