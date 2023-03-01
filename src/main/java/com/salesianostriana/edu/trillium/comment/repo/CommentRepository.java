package com.salesianostriana.edu.trillium.comment.repo;

import com.salesianostriana.edu.trillium.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
