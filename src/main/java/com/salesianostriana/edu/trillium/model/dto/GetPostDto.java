package com.salesianostriana.edu.trillium.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetPostDto {

    private Long id;
    private String titulo;
    private String image;
    private String user;
}

