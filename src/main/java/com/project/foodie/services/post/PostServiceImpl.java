package com.project.foodie.services.post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.foodie.dto.comment.CommentDTO;
import com.project.foodie.dto.post.PostDTO;
import com.project.foodie.dto.user.UserDTO;
import com.project.foodie.exception.BadRequestException;
import com.project.foodie.exception.InternalServerErrorException;
import com.project.foodie.model.comment.Comment;
import com.project.foodie.model.notification.Notification;
import com.project.foodie.model.post.Post;
import com.project.foodie.model.user.User;
import com.project.foodie.repository.notification.NotificationRepository;
import com.project.foodie.repository.post.PostRepository;
import com.project.foodie.repository.user.UserRepository;

@Service
public class PostServiceImpl implements PostService {

    Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public PostDTO upsertPost(PostDTO postDTO) {

        Post post = postDTO.getModel();
        Optional<User> optionalUser = userRepository.findById(postDTO.getUser().getId());
        post.setUser(optionalUser.get());

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
    public PostDTO getPostById(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        PostDTO result = new PostDTO(postOptional.get());
        if (!postOptional.isPresent()) {
            logger.warn("invalid post id provide!");
            throw new BadRequestException("Invalid post id provied!");
        }
        return result;
    }

    @Override
    public List<PostDTO> getAllPosts() {
        try {
            List<Post> posts = postRepository.findAll();
            List<PostDTO> postDTOs = posts.stream().map(PostDTO::new).collect(Collectors.toList());
            return postDTOs;
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

    @Override
    public Comment upsertPostComment(Comment comment, Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        Post post = postOptional.get();
        List<Comment> postCommentList = new ArrayList<Comment>();

        String notificationComment = " ";
        if (comment.getId() == null) {
            postCommentList.addAll(post.getComments());
            postCommentList.add(comment);
            notificationComment = "added";
        } else { // updating an existing comment
            for (Comment postComment : post.getComments()) {
                if (postComment.getId().equals(comment.getId())) {
                    postCommentList.add(comment);
                    notificationComment = "updated";
                } else {
                    postCommentList.add(postComment);
                    notificationComment = "added";
                }
            }
        }
        post.setComments(postCommentList);

        // create notification
        Notification notification = new Notification();
        notification.setMessage(String.format("Comment %s for post for id: %s, by: %s", notificationComment, postId,
                comment.getUser().getFirstName()));
        notification.setUser(post.getUser());

        try {
            postRepository.save(post);
            notificationRepository.save(notification);
        } catch (Exception e) {
            logger.error(String.format("failed to create post for: %s, error: %s", comment, e));
            throw new InternalServerErrorException("Failed to create post!");
        }
        return comment;
    }

    @Override
    public List<CommentDTO> getPostCommentsByPostId(Long postId) {
        List<Comment> postCommentList = new ArrayList<Comment>();
        Optional<Post> postOptional = postRepository.findById(postId);
        Post post = postOptional.get();
        postCommentList = post.getComments();
        List<CommentDTO> resultDTOs = postCommentList.stream().map(CommentDTO::new).collect(Collectors.toList());
        return resultDTOs;

    }

    @Override
    public UserDTO addPostLike(UserDTO userDTO, Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        Post post = postOptional.get();
        List<User> postLikeList = new ArrayList<User>();

        Optional<User> likedUser = userRepository.findById(userDTO.getId());
        postLikeList.addAll(post.getLikes());
        postLikeList.add(likedUser.get());

        post.setLikes(postLikeList);

        // create notification
        Notification notification = new Notification();
        notification.setMessage(String.format("Post %s liked by %s ", postId, userDTO.getFirstName()));
        notification.setUser(post.getUser());

        try {
            postRepository.save(post);
            notificationRepository.save(notification);
        } catch (Exception e) {
            logger.error(String.format("failed to add like for: %s, error: %s", userDTO, e));
            throw new InternalServerErrorException("Failed to add like!");
        }

        return userDTO;
    }

}
