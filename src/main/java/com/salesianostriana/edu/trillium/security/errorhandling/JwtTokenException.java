package com.salesianostriana.edu.trillium.security.errorhandling;

public class JwtTokenException extends RuntimeException{

    public JwtTokenException(String msg) {
        super(msg);
    }

}
