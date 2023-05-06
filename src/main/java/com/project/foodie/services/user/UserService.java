package com.project.foodie.services.user;

import java.util.List;
import com.project.foodie.dto.user.UserDTO;
import com.project.foodie.model.user.User;

public interface UserService {

    List<User> getAllUser();

    User getUserById(Long id);

    UserDTO UpsertUser(UserDTO userDTO);

    void deleteUserById(Long id);

}
