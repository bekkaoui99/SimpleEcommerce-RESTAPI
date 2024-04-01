package com.hamzabekkaoui.SimpleEcommerceApp.dto.response;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String userName,
        String accessToken,
        String refreshToken
) {
}
