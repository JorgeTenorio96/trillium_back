package com.salesianostriana.edu.trillium.post.dto;

import com.salesianostriana.edu.trillium.post.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {

    private Long id;

    private String title;

    private String image;



    public static PostResponse toPostResponse(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .image(post.getImage())
                .build();
    }

}
