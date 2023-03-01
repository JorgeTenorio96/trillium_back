package com.salesianostriana.edu.trillium.post.service;

import com.salesianostriana.edu.trillium.comment.dto.CreateComment;
import com.salesianostriana.edu.trillium.comment.dto.PageResponse;
import com.salesianostriana.edu.trillium.comment.model.Comment;
import com.salesianostriana.edu.trillium.comment.repo.CommentRepository;
import com.salesianostriana.edu.trillium.files.service.StorageService;
import com.salesianostriana.edu.trillium.post.dto.CreatePostDto;
import com.salesianostriana.edu.trillium.post.dto.PostResponse;
import com.salesianostriana.edu.trillium.post.model.Post;
import com.salesianostriana.edu.trillium.post.repo.PostRepository;
import com.salesianostriana.edu.trillium.search.spec.PostSpecificationBuilder;
import com.salesianostriana.edu.trillium.search.util.SearchCriteria;
import com.salesianostriana.edu.trillium.search.util.SearchCriteriaExtractor;
import com.salesianostriana.edu.trillium.user.model.User;
import com.salesianostriana.edu.trillium.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;
    private final StorageService storageService;

    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    @Transactional
    public PostResponse save(CreatePostDto createPostDto, MultipartFile file) {
        String filename = storageService.store(file);

        return PostResponse.toPostResponse(
                repository.save(
                        Post.builder()
                                .title(createPostDto.getTitle())
                                .image(filename)
                                .likes(new HashSet<>())
                                .comment(new ArrayList<>())
                                .build()
                )
        );
    }
    public PageResponse<PostResponse> search(List<SearchCriteria> params, Pageable pageable){
        PostSpecificationBuilder genericSpecificationBuilder = new PostSpecificationBuilder(params);
        Specification<Post> spec = genericSpecificationBuilder.build();
        Page<PostResponse> postResponsePage = repository.findAll(spec, pageable).map(PostResponse::toPostResponse);
        return new PageResponse<>(postResponsePage);
    }
    public Post comment(Long id, CreateComment commentary, User user) {
        if (repository.existsById(id)) {
            repository.findById(id).get().getComment().add(
                    commentRepository.save(
                            Comment.builder()
                                    .post(repository.findById(id).get())
                                    .message(commentary.getMessage())
                                    .user(user)
                                    .build()));
            repository.save(repository.findById(id).get());
            return repository.findById(id).get();
        }
        return null;

    }
    public List<Comment> findAllComments() {
        List<Comment> result = commentRepository.findAll();
        if (result.isEmpty()) {
            throw new EntityNotFoundException("Comments not found");
        }
        return result;
    }
    public void deleteCommentById(Long idPost, Long idComment){
        if (repository.findById(idPost).get().getComment().contains(commentRepository.findById(idComment))){
            commentRepository.deleteById(idComment);
        }

    }


    public PageResponse<PostResponse> findAll(String s, Pageable pageable) {
        List<SearchCriteria> params = SearchCriteriaExtractor.extractSearchCriteriaList(s);
        PageResponse<PostResponse> res = search(params, pageable);
        if (res.getContent().isEmpty()) {
            throw new EntityNotFoundException("Posts not found");
        }
        return res;
    }

    public Post liked(Long id, User user){
        if(repository.existsById(id)){
            if(repository.findById(id).get().getLikes().contains(user)){
                repository.findById(id).get().getLikes().remove(user);
                repository.save(repository.findById(id).get());
            }
            else {
                repository.findById(id).get().getLikes().add(user);
                repository.save(repository.findById(id).get());
                userRepository.findById(user.getId()).get().getLikes().add(repository.findById(id).get());
                userRepository.save(user);

            }
            return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        }
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }


}
