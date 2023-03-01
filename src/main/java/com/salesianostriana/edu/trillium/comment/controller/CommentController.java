package com.salesianostriana.edu.trillium.comment.controller;

import com.salesianostriana.edu.trillium.comment.dto.CommentResponse;
import com.salesianostriana.edu.trillium.comment.dto.CreateComment;
import com.salesianostriana.edu.trillium.post.dto.PostResponse;
import com.salesianostriana.edu.trillium.post.model.Post;
import com.salesianostriana.edu.trillium.post.service.PostService;
import com.salesianostriana.edu.trillium.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
public class CommentController {

    private final PostService postService;
    @Operation(summary = "Crea un nuevo comentario a un post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Se ha creado un nuevo comentario",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PostResponse.class),
                            examples = @ExampleObject(value = """
                                                    
                                    """))}),
            @ApiResponse(responseCode = "404",
                    description = "No se han introducido los datos correctamente",
                    content = @Content),
    })


    @PostMapping("/{id}/comment")
    public ResponseEntity<PostResponse> postComment(@PathVariable Long id, @RequestBody CreateComment createComment, @AuthenticationPrincipal User user){
        Post nuevo = postService.comment(id, createComment, user);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevo.getId()).toUri();


        return ResponseEntity
                .created(createdURI)
                .body(PostResponse.toPostResponse(nuevo));
    }
    @Operation(summary = "Este método muestra los comentarios de un post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Se ha encontrado el comentario",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PostResponse.class)),
                            examples = @ExampleObject(value = """
                                                {
}
                                    """
                            ))}),
            @ApiResponse(responseCode = "404",
                    description = "No se ha encontrado ningun comentario",
                    content = @Content),
    })
    @GetMapping("/{id}/comment")
    public List<CommentResponse> getAll(){
        List<CommentResponse> commentsResponse= new ArrayList<>();
        postService.findAllComments().forEach(comentario -> {
            commentsResponse.add(CommentResponse.toComment(comentario));
        });

        return commentsResponse;
    }

    @DeleteMapping("/{id}/comment/{id2}")
    public ResponseEntity<?> deleteById(@PathVariable Long idPost, @PathVariable Long idComment){
        postService.deleteCommentById(idPost, idComment);
        return ResponseEntity.noContent().build();
    }

}
