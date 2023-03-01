package com.salesianostriana.edu.trillium.comment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salesianostriana.edu.trillium.post.model.Post;
import com.salesianostriana.edu.trillium.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String message;

}
