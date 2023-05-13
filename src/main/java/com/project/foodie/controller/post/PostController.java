package com.project.foodie.controller.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.foodie.dto.comment.CommentDTO;
import com.project.foodie.dto.post.PostDTO;
import com.project.foodie.dto.user.UserDTO;
import com.project.foodie.model.comment.Comment;
import com.project.foodie.services.post.PostService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostController {
    
    @Autowired
    private PostService postService;

    @ApiOperation(value = "Upsert post", response = PostDTO.class)
    @PostMapping
    public ResponseEntity<PostDTO> upsertLeadStage(@RequestBody PostDTO postDTO) {
        PostDTO savedPost= postService.upsertPost(postDTO);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all posts", response = PostDTO.class)
    @GetMapping
    public ResponseEntity<List<PostDTO>> getUser() {
        List<PostDTO> post = postService.getAllPosts();
        return new ResponseEntity<List<PostDTO>>(post, HttpStatus.OK);
    }

    @ApiOperation(value = "Get post by Id", response = PostDTO.class)
    @GetMapping("{id}")
    public ResponseEntity<PostDTO> getLeadStageById(@PathVariable("id") Long id) {
        PostDTO result = postService.getPostById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete post")
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Add post like")
    @PostMapping("{id}/likes")
    public ResponseEntity<UserDTO> addPostLike(@RequestBody UserDTO user, @PathVariable("id") Long postId) {
        UserDTO savedLike = postService.addPostLike(user, postId);
        return new ResponseEntity<>(savedLike, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Create post comment", response = Comment.class)
    @PostMapping("{id}/comments")
    public ResponseEntity<Comment> upsertPostComment(@RequestBody Comment comment, @PathVariable("id") Long postId) {
        Comment savedComment = postService.upsertPostComment(comment, postId);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get post commen by post id", response = CommentDTO.class)
    @GetMapping("{id}/comments")
    public ResponseEntity<List<CommentDTO>> getPostComments(@PathVariable("id") Long postId) {
        List<CommentDTO> postCommentDTOs = postService.getPostCommentsByPostId(postId);
        return new ResponseEntity<>(postCommentDTOs, HttpStatus.OK) ;
    }
}
