package com.salesianostriana.edu.trillium.comment.dto;

import com.salesianostriana.edu.trillium.comment.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {

    private Long id;

    private String postTitle;

    private String username, avatar, fullname;

    private String message;

    public static CommentResponse toComment(Comment comment){
        return CommentResponse.builder()
                .id(comment.getId())
                .avatar(comment.getUser().getAvatar())
                .fullname(comment.getUser().getFullName())
                .postTitle(comment.getPost().getTitle())
                .username(comment.getUser().getUsername())
                .message(comment.getMessage())
                .build();


    }



}
