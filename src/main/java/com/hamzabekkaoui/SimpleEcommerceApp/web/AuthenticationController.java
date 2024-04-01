package com.hamzabekkaoui.SimpleEcommerceApp.web;

import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.AuthenticationRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.RegisterRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.AuthenticationResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.RegisterResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserServiceImpl userService;


    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }

    @PostMapping("/login")
    public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return userService.authenticate(authenticationRequest);
    }
}
