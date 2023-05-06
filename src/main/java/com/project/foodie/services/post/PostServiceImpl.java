package com.project.foodie.services.post;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.foodie.dto.post.PostDTO;
import com.project.foodie.exception.BadRequestException;
import com.project.foodie.exception.InternalServerErrorException;
import com.project.foodie.model.post.Post;
import com.project.foodie.repository.post.PostRepository;

@Service
public class PostServiceImpl implements PostService {

    Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDTO upsertPost(PostDTO postDTO) {

        Post post = postDTO.getModel();

        Post result = new Post();

        try {
            result = postRepository.save(post);
        } catch (Exception e) {
            logger.error(String.format("failed to create post for: %s, error: %s", postDTO, e));
            throw new InternalServerErrorException("Failed to create post!");
        }

        PostDTO resultDTO = new PostDTO(result);
        return resultDTO;
    }

    @Override
    public Post getPostById(Long id) {
        Optional<Post> userOptional = postRepository.findById(id);
        if (!userOptional.isPresent()) {
            logger.warn("invalid post id provide!");
            throw new BadRequestException("Invalid post id provied!");
        }
        return userOptional.get();
    }

    @Override
    public List<Post> getAllPosts() {
        try {
            return postRepository.findAll();
        } catch (Exception e) {
            String errorMessage = "Failed to retrieve posts. Error: " + e.getMessage();
            logger.error(errorMessage, e);
            throw new InternalServerErrorException("Failed to retrieve posts", e);
        }
    }

    @Override
    public void deletePostById(Long id) {
        Optional<Post> leadStageOptional = postRepository.findById(id);

        if (!leadStageOptional.isPresent()) {
            logger.warn(String.format("invalid post id (%s) provided!", id));
            throw new BadRequestException("Invalid post id provided!");
        }

        try {
            postRepository.deleteById(id);
        } catch (Exception e) {
            logger.error(String.format("failed to delete post for id: %s, error: %s", id, e));
            throw new InternalServerErrorException("Failed to delete post!");
        }
    }
    
}
