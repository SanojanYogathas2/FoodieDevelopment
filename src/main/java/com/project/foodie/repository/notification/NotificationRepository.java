package com.project.foodie.repository.notification;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.foodie.model.notification.Notification;

public interface NotificationRepository extends JpaRepository<Notification,Long>{
    
}
