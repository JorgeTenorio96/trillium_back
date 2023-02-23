package com.salesianostriana.edu.trillium.post.repo;

import com.salesianostriana.edu.trillium.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {
}
