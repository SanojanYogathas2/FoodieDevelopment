package com.project.foodie.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.foodie.model.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment,Long>{
    
}
