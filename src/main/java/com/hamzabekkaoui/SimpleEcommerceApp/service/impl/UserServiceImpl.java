package com.hamzabekkaoui.SimpleEcommerceApp.service.impl;

import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.AuthenticationRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.ChangePasswordRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.RegisterRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.AuthenticationResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.RegisterResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.entities.User;
import com.hamzabekkaoui.SimpleEcommerceApp.exeption.ResourceAlreadyExist;
import com.hamzabekkaoui.SimpleEcommerceApp.exeption.ResourceNotFoundException;
import com.hamzabekkaoui.SimpleEcommerceApp.mapper.UserMapper;
import com.hamzabekkaoui.SimpleEcommerceApp.repositories.UserRepository;
import com.hamzabekkaoui.SimpleEcommerceApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) throws ResourceAlreadyExist {
        boolean exists = userRepository.existsByMail(registerRequest.mail());
        if (exists) throw new ResourceAlreadyExist("user already exist with this mail : " + registerRequest.mail());

        User user = userMapper.RegisterRequestToUser(registerRequest);
        User createdUser = userRepository.save(user);

        return userMapper.UserToRegisterResponse(createdUser);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.mail(), authenticationRequest.password())
        );

        UserDetails user = (UserDetails) authenticate.getDetails();

        return AuthenticationResponse.builder()
                .userName(user.getUsername())
                .accessToken("access token")
                .refreshToken("refresh token")
                .build();
    }

    @Override
    public Page<RegisterResponse> allUsers(int page, int size) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size));
        List<RegisterResponse> registerResponseList = users.getContent()
                .stream()
                .map(userMapper::UserToRegisterResponse)
                .collect(Collectors.toList());


        return new PageImpl<>(registerResponseList , users.getPageable() , users.getTotalElements());
    }

    @Override
    public List<RegisterResponse> allUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(userMapper::UserToRegisterResponse)
                .collect(Collectors.toList());

    }

    @Override
    public RegisterResponse userByName(String userName) throws ResourceNotFoundException {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("user doesn't exist with this userName : " + userName));
        return userMapper.UserToRegisterResponse(user);
    }

    @Override
    public RegisterResponse changePassword(ChangePasswordRequest changePasswordRequest) {

        User user = userRepository.findByMail(changePasswordRequest.mail())
                .orElseThrow(() -> new ResourceNotFoundException("user doesn't exist"));
        if (changePasswordRequest.password() != null && !changePasswordRequest.password().equals(user.getPassword())){
            if(!changePasswordRequest.password().equals(changePasswordRequest.confirmationPassword()))
                throw new IllegalArgumentException("confirmation password doesn't match ");

            user.setPassword(passwordEncoder.encode(changePasswordRequest.password()));

        }
        User updatedUser = userRepository.save(user);
        return userMapper.UserToRegisterResponse(updatedUser);


    }

    @Override
    public RegisterResponse updateUser(RegisterRequest registerRequest) throws ResourceNotFoundException {

        User user = userRepository.findByMail(registerRequest.mail())
                .orElseThrow(() -> new ResourceNotFoundException("user doesn't exist"));
        if (registerRequest.userName() != null && !registerRequest.userName().equals(user.getUserName()))
            user.setUserName(registerRequest.userName());
        if (registerRequest.firstName() != null && !registerRequest.firstName().equals(user.getFirstName()))
            user.setFirstName(registerRequest.firstName());
        if (registerRequest.lastName() != null && !registerRequest.lastName().equals(user.getLastName()))
            user.setLastName(registerRequest.lastName());
        if (registerRequest.password() != null && !registerRequest.password().equals(user.getPassword())){
            if(!registerRequest.password().equals(registerRequest.confirmationPassword()))
                throw new IllegalArgumentException("confirmation password doesn't match ");

            user.setPassword(passwordEncoder.encode(registerRequest.password()));

        }
        User updatedUss = userRepository.save(user);


        return userMapper.UserToRegisterResponse(updatedUss);
    }

    @Override
    public boolean deleteUser(Long id) throws ResourceNotFoundException {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user doesn't exist"));
        userRepository.delete(user);
        return true;
    }
}
