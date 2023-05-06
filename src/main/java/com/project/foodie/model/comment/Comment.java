package com.project.foodie.model.comment;

import com.project.foodie.model.post.Post;
import com.project.foodie.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Post post;

    @ManyToOne
    private User user;

    private String content;

}