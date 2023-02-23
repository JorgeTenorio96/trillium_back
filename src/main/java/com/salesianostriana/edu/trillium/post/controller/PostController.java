package com.salesianostriana.edu.trillium.post.controller;

import com.salesianostriana.edu.trillium.post.dto.CreatePostDto;
import com.salesianostriana.edu.trillium.post.model.Post;
import com.salesianostriana.edu.trillium.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @GetMapping("/")
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<Post> create(
            @RequestPart("file") MultipartFile file,
            @RequestPart("post") CreatePostDto newPost
    ) {
        Post post = service.save(newPost,file);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(post);
    }

}
