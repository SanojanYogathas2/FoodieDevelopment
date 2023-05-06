package com.project.foodie.controller.user;

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
import com.project.foodie.dto.user.UserDTO;
import com.project.foodie.model.user.User;
import com.project.foodie.services.user.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get all user", response = User.class)
    @GetMapping
    public ResponseEntity<List<User>> getUser() {
        List<User> user = userService.getAllUser();
        return new ResponseEntity<List<User>>(user, HttpStatus.OK);
    }

    @ApiOperation(value = "Get user by Id", response = User.class)
    @GetMapping("{id}")
    public ResponseEntity<User> getLeadStageById(@PathVariable("id") Long id) {
        User result = userService.getUserById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new user", response = UserDTO.class)
    @PostMapping
    public ResponseEntity<UserDTO> upsertUser(@RequestBody UserDTO userDTO) {
        UserDTO dto = userService.UpsertUser(userDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete user")
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
