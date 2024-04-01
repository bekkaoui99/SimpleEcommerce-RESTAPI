package com.hamzabekkaoui.SimpleEcommerceApp.mapper;

import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.RegisterRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.RegisterResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.entities.Role;
import com.hamzabekkaoui.SimpleEcommerceApp.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    public RegisterResponse UserToRegisterResponse(User user){
        return RegisterResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .mail(user.getMail())
                .userName(user.getUserName())
                .build();
    }

    public User RegisterRequestToUser(RegisterRequest registerRequest){
        return User.builder()
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .mail(registerRequest.mail())
                .userName(registerRequest.userName())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(Role.USER)
                .build();
    }


}
