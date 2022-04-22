package com.microservices.demo.twitter.to.kafka.service.exceptions;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TwitterServiceException extends RuntimeException{

    TwitterServiceException(){
        super();
    }

    TwitterServiceException(String message){
        super(message);
    }
}
