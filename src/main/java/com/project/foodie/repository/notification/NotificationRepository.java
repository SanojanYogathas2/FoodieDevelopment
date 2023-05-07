package com.project.foodie.repository.notification;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.foodie.model.notification.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Long>{

    List<Notification> findAllByUserId(Long userId);
    // List<NotificationDTO> findAllByUserId(Long userId);
}
