package com.project.foodie.dto.comment;

import com.project.foodie.dto.ModelMapper;
import com.project.foodie.dto.user.UserDTO;
import com.project.foodie.model.comment.Comment;
import com.project.foodie.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO implements ModelMapper<Comment> {

    private Long id;

    private UserDTO user;

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
        comment.setUser(this.getUser().getModel());
        comment.setContent(this.getContent());
        return comment;
    }

    @Override
    public void mapToSelf(Comment comment) {
        this.setId(comment.getId());
        this.setUser(new UserDTO(comment.getUser()));
        this.setContent(comment.getContent());
    }
}
