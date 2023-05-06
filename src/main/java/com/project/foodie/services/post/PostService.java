package com.project.foodie.services.post;

import java.util.List;
import com.project.foodie.dto.post.PostDTO;
import com.project.foodie.model.post.Post;

public interface PostService {
    PostDTO upsertPost(PostDTO postDTO);

    Post getPostById(Long id);

    List<Post> getAllPosts();

    void deletePostById (Long id);
}
