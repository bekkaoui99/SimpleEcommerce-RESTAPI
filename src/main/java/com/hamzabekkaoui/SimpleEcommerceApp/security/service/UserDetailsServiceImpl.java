package com.hamzabekkaoui.SimpleEcommerceApp.security.service;

import com.hamzabekkaoui.SimpleEcommerceApp.repositories.UserRepository;
import com.hamzabekkaoui.SimpleEcommerceApp.security.model.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByMail(username)
                .map(UserSecurity::new)
                .orElseThrow(() -> new UsernameNotFoundException("user doesn't exist"));
    }
}
