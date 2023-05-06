package com.project.foodie.dto.post;

import java.util.List;
import com.project.foodie.dto.ModelMapper;
import com.project.foodie.model.comment.Comment;
import com.project.foodie.model.post.Post;
import com.project.foodie.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO implements ModelMapper<Post>{
    private Long id;
    
    private User user;

    private List<Comment> comment;

    private String discription;

    private String title;

    private String image;

    public PostDTO(Post post){
        this.mapToSelf(post);
    }

    @Override 
    public Post getModel(){
        Post post = new Post();
        mapToModel(post);
        return post;
    }

    @Override 
    public Post mapToModel(Post post){
        post.setId(this.getId());
        post.setUser(this.getUser());
        post.setComment(this.getComment());
        post.setTitle(this.getTitle());
        post.setDiscription(this.getDiscription());
        post.setImage(this.getImage());
        return post;
    }

    @Override
    public void mapToSelf(Post post){
        this.setId(post.getId());
        this.setUser(post.getUser());
        this.setComment(post.getComment());
        this.setTitle(post.getTitle());
        this.setDiscription(post.getTitle());
        this.setImage(this.getImage());
    }

}
