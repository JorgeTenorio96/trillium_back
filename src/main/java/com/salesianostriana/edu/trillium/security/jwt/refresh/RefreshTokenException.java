package com.salesianostriana.edu.trillium.security.jwt.refresh;

import com.salesianostriana.edu.trillium.security.errorhandling.JwtTokenException;

public class RefreshTokenException extends JwtTokenException {

    public RefreshTokenException(String msg) {
        super(msg);
    }

}
