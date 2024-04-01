package com.hamzabekkaoui.SimpleEcommerceApp.web;


import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.ChangePasswordRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.RegisterRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.RegisterResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.service.ProductService;
import com.hamzabekkaoui.SimpleEcommerceApp.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserServiceImpl userService;

    @PostMapping("/list")
    public List<RegisterResponse> allUsers(){
        return userService.allUsers();
    }

    @PostMapping("/page")
    public Page<RegisterResponse> allUsers(
            @RequestParam(name = "pageNumber" , defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize" , defaultValue = "0") int pageSize
    ){
        return userService.allUsers(pageNumber , pageSize);
    }

    @PutMapping("/byName")
    public RegisterResponse updateUser(
            @RequestParam(name = "userName") String userName
    ){
        return userService.userByName(userName);
    }

    @DeleteMapping("/{id}")
    public boolean allUsers(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @PutMapping()
    public RegisterResponse updateUser(@RequestBody RegisterRequest registerRequest){
        return userService.updateUser(registerRequest);
    }


    @PutMapping("/changePassword")
    public RegisterResponse changePassword(@RequestBody ChangePasswordRequest changePasswordRequest){
        return userService.changePassword(changePasswordRequest);
    }


}
