package com.hamzabekkaoui.SimpleEcommerceApp.exeption;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
