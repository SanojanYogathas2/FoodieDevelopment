package com.project.foodie.controller.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.foodie.dto.post.PostDTO;
import com.project.foodie.model.post.Post;
import com.project.foodie.services.post.PostService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/posts")
public class PostController {
    
    @Autowired
    private PostService postService;

    @ApiOperation(value = "Upsert post", response = PostDTO.class)
    @PostMapping
    public ResponseEntity<PostDTO> upsertLeadStage(@RequestBody PostDTO postDTO) {
        PostDTO savedPost= postService.upsertPost(postDTO);
        return new ResponseEntity<>(savedPost, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all posts", response = Post.class)
    @GetMapping
    public ResponseEntity<List<Post>> getUser() {
        List<Post> post = postService.getAllPosts();
        return new ResponseEntity<List<Post>>(post, HttpStatus.OK);
    }

    @ApiOperation(value = "Get post by Id", response = Post.class)
    @GetMapping("{id}")
    public ResponseEntity<Post> getLeadStageById(@PathVariable("id") Long id) {
        Post result = postService.getPostById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete post")
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
