package com.hamzabekkaoui.SimpleEcommerceApp.dto.request;

import lombok.Builder;

@Builder
public record AuthenticationRequest(
        String mail,
        String password
) {
}
