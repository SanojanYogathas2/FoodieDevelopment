package com.project.foodie.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.foodie.model.post.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    
}
