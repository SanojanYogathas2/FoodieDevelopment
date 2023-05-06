package com.project.foodie.seeder;

import com.project.foodie.model.post.Post;
import com.project.foodie.model.user.User;
import com.project.foodie.repository.post.PostRepository;
import com.project.foodie.repository.user.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserSeeder implements CommandLineRunner {

    private UserRepository userRepository;
  

    public UserSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setEmail("johndoe@example.com");
        user1.setPassword("password");

        User user2 = new User();
        user2.setFirstName("Jane");
        user2.setLastName("Doe");
        user2.setEmail("janedoe@example.com");
        user2.setPassword("password");

        User user3 = new User();
        user3.setFirstName("Bob");
        user3.setLastName("Smith");
        user3.setEmail("bobsmith@example.com");
        user3.setPassword("password");

        userRepository.saveAll(Arrays.asList(user1, user2, user3));
    }
}
