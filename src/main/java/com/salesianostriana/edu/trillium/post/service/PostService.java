package com.salesianostriana.edu.trillium.post.service;

import com.salesianostriana.edu.trillium.files.service.StorageService;
import com.salesianostriana.edu.trillium.post.dto.CreatePostDto;
import com.salesianostriana.edu.trillium.post.model.Post;
import com.salesianostriana.edu.trillium.post.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;
    private final StorageService storageService;

    @Transactional
    public Post save(CreatePostDto createPostDto, MultipartFile file) {
        String filename = storageService.store(file);

        Post post = repository.save(
                Post.builder()
                        .title(createPostDto.getTitle())
                        .image(filename)
                        .build()
        );
        return post;
    }

    public List<Post> findAll() { return repository.findAll(); }


}
