package com.hamzabekkaoui.SimpleEcommerceApp.exeption;

import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ExceptionResponse productNotFoundException(ProductNotFoundException ex){
        return ExceptionResponse.builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(ResourceAlreadyExist.class)
    public ExceptionResponse resourceAlreadyExist(ResourceAlreadyExist ex){
        return ExceptionResponse.builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

}
