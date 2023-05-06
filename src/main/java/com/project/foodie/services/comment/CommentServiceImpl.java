package com.project.foodie.services.comment;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.foodie.dto.comment.CommentDTO;
import com.project.foodie.exception.BadRequestException;
import com.project.foodie.exception.InternalServerErrorException;
import com.project.foodie.model.comment.Comment;
import com.project.foodie.repository.comment.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

    Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public CommentDTO upsertComment(CommentDTO commentDTO) {
        Comment user = commentDTO.getModel();
        try {
            Comment result = commentRepository.save(user);
            CommentDTO resultDTO = new CommentDTO(result);
            return resultDTO;
        } catch (Exception e) {
            logger.error(String.format("faild to create comment for: %s, error: %s", commentDTO), e);
            throw new InternalServerErrorException("Faild to create user");
        }
    }

    @Override
    public void deletCommentById(Long id) {
        Optional<Comment> leadStageOptional = commentRepository.findById(id);

        if (!leadStageOptional.isPresent()) {
            logger.warn(String.format("invalid comment id (%s) provided!", id));
            throw new BadRequestException("Invalid comment id provided!");
        }

        try {
            commentRepository.deleteById(id);
        } catch (Exception e) {
            logger.error(String.format("failed to delete comment for id: %s, error: %s", id, e));
            throw new InternalServerErrorException("Failed to delete comment!");
        }
    }
}
