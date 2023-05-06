package com.project.foodie.controller.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.foodie.dto.comment.CommentDTO;
import com.project.foodie.services.comment.CommentService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/comments")
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    @ApiOperation(value = "Create a new comment", response = CommentDTO.class)
    @PostMapping
    public ResponseEntity<CommentDTO> upsertUser(@RequestBody CommentDTO commentDTO) {
        CommentDTO dto = commentService.upsertComment(commentDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete comment")
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        commentService.deletCommentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
