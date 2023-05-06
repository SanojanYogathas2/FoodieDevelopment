package com.project.foodie.dto.comment;

import org.springframework.beans.factory.annotation.Autowired;
import com.project.foodie.dto.ModelMapper;
import com.project.foodie.model.comment.Comment;
import com.project.foodie.model.post.Post;
import com.project.foodie.model.user.User;
import com.project.foodie.services.comment.CommentService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO implements ModelMapper<Comment> {

    private Long id;

    private Post post;

    private User user;

    private String content;

    public CommentDTO(Comment comment) {
        this.mapToSelf(comment);
    }

    @Override
    public Comment getModel() {
        Comment comment = new Comment();
        mapToModel(comment);
        return comment;
    }

    @Override
    public Comment mapToModel(Comment comment) {
        comment.setId(this.getId());
        comment.setPost(this.getPost());
        comment.setUser(this.getUser());
        comment.setContent(this.getContent());
        return comment;
    }

    @Override
    public void mapToSelf(Comment comment) {
        this.setId(comment.getId());
        this.setPost(comment.getPost());
        this.setUser(comment.getUser());
        this.setContent(comment.getContent());
    }
}
