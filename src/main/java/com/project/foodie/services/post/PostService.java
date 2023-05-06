package com.project.foodie.services.post;

import java.util.List;
import com.project.foodie.dto.comment.CommentDTO;
import com.project.foodie.dto.post.PostDTO;
import com.project.foodie.model.comment.Comment;

public interface PostService {
    PostDTO upsertPost(PostDTO postDTO);

    PostDTO getPostById(Long id);

    List<PostDTO> getAllPosts();

    void deletePostById (Long id);

    Comment upsertPostComment( Comment comment, Long postId);

    List<CommentDTO> getPostCommentsByPostId( Long leadId);
}
