package com.salesianostriana.edu.trillium.post.model;

import com.salesianostriana.edu.trillium.user.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String image;

    @ManyToOne()
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_POST_USER"))
    private User author;


}
