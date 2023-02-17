package com.salesianostriana.edu.trillium.repos;

import com.salesianostriana.edu.trillium.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUsername(String username);

}
