package com.project.foodie.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.foodie.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{ 
}
 