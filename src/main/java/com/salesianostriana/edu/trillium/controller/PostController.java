package com.salesianostriana.edu.trillium.controller;

import com.salesianostriana.edu.trillium.model.dto.CreatePostDto;
import com.salesianostriana.edu.trillium.model.dto.GetPostDto;
import com.salesianostriana.edu.trillium.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    /*
    @PostMapping("/")
    public ResponseEntity<GetPostDto> createPost(@RequestParam("title")String title, @RequestParam("image")String image, @RequestPart("file") MultipartFile file,
                                                 @AuthenticationPrincipal User user) throws IOException {

       CreatePostDto createPostDto = CreatePostDto.builder()
                .title(title)
                .username(username)
                .build();
        Post post = postService.save(postDtoConverter.createPostDtoToPost(createPostDto, file),file,user);
        GetPostDto getPostDto = postDtoConverter.postToGetPostDtoWithUser(post,user);
        return ResponseEntity.created(URI.create(post.getContenidoMultimedia())).body(getPostDto);
    }*/
}
