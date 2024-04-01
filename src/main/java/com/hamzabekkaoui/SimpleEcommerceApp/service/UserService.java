package com.hamzabekkaoui.SimpleEcommerceApp.service;

import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.AuthenticationRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.ChangePasswordRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.RegisterRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.AuthenticationResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.RegisterResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.exeption.ResourceAlreadyExist;
import com.hamzabekkaoui.SimpleEcommerceApp.exeption.ResourceNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    RegisterResponse register(RegisterRequest registerRequest) throws ResourceAlreadyExist;
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    Page<RegisterResponse> allUsers(int page , int size);
    List<RegisterResponse> allUsers();
    RegisterResponse userByName(String userName) throws ResourceNotFoundException;

    RegisterResponse changePassword(ChangePasswordRequest changePasswordRequest);
    RegisterResponse updateUser(RegisterRequest registerRequest) throws ResourceNotFoundException;
    boolean deleteUser(Long id) throws ResourceNotFoundException;
}
