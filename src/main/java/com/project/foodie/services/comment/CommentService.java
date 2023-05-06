package com.project.foodie.services.comment;

import com.project.foodie.dto.comment.CommentDTO;

public interface CommentService {
    CommentDTO upsertComment(CommentDTO commentDTO);

    void deletCommentById(Long id);
}
