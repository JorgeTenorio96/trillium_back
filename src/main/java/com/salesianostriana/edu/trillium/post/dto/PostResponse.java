package com.salesianostriana.edu.trillium.post.dto;

import com.salesianostriana.edu.trillium.comment.dto.CommentResponse;
import com.salesianostriana.edu.trillium.comment.model.Comment;
import com.salesianostriana.edu.trillium.post.model.Post;
import com.salesianostriana.edu.trillium.user.dto.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {

    private Long id;

    @NotEmpty
    private String title;

    private String image;


    private Set<UserResponse> likes;

    private List<CommentResponse> comments;



    public static PostResponse toPostResponse(Post post){
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .image(post.getImage())
                .likes(post.getLikes().stream().map(UserResponse::fromUser).collect(Collectors.toSet()))
                .comments(post.getComment().stream().map(CommentResponse::toComment).toList())
                .build();
    }

}
