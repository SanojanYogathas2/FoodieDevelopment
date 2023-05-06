package com.project.foodie.services.user;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.foodie.dto.user.UserDTO;
import com.project.foodie.exception.BadRequestException;
import com.project.foodie.exception.InternalServerErrorException;
import com.project.foodie.model.user.User;
import com.project.foodie.repository.user.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO UpsertUser(UserDTO userDTO) {
        User user = userDTO.getModel();
        try {
            User result = userRepository.save(user);
            UserDTO resultDTO = new UserDTO(result);
            return resultDTO;
        } catch (Exception e) {
            logger.error(String.format("faild to create user for: %s, error: %s", userDTO), e);
            throw new InternalServerErrorException("Faild to create user");
        }
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            logger.warn("invalid user id provide!");
            throw new BadRequestException("Invalid user id provied!");
        }
        return userOptional.get();
    }

    @Override
    public List<User> getAllUser() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            String errorMessage = "Failed to retrieve users. Error: " + e.getMessage();
            logger.error(errorMessage, e);
            throw new InternalServerErrorException("Failed to retrieve users", e);
        }
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> leadStageOptional = userRepository.findById(id);

        if (!leadStageOptional.isPresent()) {
            logger.warn(String.format("invalid user id (%s) provided!", id));
            throw new BadRequestException("Invalid user id provided!");
        }

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            logger.error(String.format("failed to delete user for id: %s, error: %s", id, e));
            throw new InternalServerErrorException("Failed to delete user!");
        }
    }
}
