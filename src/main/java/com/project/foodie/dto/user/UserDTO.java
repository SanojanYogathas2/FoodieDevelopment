package com.project.foodie.dto.user;

import com.project.foodie.dto.ModelMapper;
import com.project.foodie.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class UserDTO implements ModelMapper<User> {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    public UserDTO(User user) {
        this.mapToSelf(user);
    }

    @Override
    public User getModel() {
        User user = new User();
        mapToModel(user);
        return user;
    }

    @Override
    public User mapToModel(User user) {
        user.setId(this.getId());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        return user;
    }

    @Override
    public void mapToSelf(User user) {
        this.setId(user.getId());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
    }
}
