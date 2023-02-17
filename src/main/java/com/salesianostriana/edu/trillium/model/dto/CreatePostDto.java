package com.salesianostriana.edu.trillium.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CreatePostDto {

    private String title;
    private String username;
    private String image;



}
