package com.hamzabekkaoui.SimpleEcommerceApp.exeption;

public class ResourceAlreadyExist extends RuntimeException{

    public ResourceAlreadyExist(String message) {
        super(message);
    }
}
