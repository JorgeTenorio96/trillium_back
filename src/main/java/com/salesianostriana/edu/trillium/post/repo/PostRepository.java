package com.salesianostriana.edu.trillium.post.repo;

import com.salesianostriana.edu.trillium.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
}
