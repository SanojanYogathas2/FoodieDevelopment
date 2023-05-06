package com.project.foodie.seeder;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.foodie.model.post.Post;
import com.project.foodie.repository.post.PostRepository;
@Component
public class PostSeeder  implements CommandLineRunner{
    private PostRepository postRepository;

    public PostSeeder(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) {
        Post post1 = new Post();
        post1.setId(1L);
        post1.setTitle("TestTitle");
        post1.setDiscription("TestDiscription");
        post1.setImage("ImageTest");
        post1.setUser(null);
        post1.setUser(null);

        Post post2 = new Post();
        post2.setId(1L);
        post2.setTitle("TestTitle");
        post2.setDiscription("TestDiscription");
        post2.setImage("ImageTest");
        post2.setUser(null);

        postRepository.saveAll(Arrays.asList(post1, post2));
    }
}
