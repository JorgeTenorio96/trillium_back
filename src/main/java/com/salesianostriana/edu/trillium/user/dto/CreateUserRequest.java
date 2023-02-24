package com.salesianostriana.edu.trillium.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {

    private String username;
    private String password;
    private String email;
    private String verifyEmail;
    private String verifyPassword;
    private String avatar;
    private String fullName;

}
