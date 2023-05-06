package com.project.foodie.model.post;

import java.util.List;
import com.project.foodie.model.comment.Comment;
import com.project.foodie.model.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Comment> comment;

    private String Discription;

    @NotBlank
    private String title;

    private String Image;
}
