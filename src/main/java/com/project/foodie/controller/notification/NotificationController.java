package com.project.foodie.controller.notification;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.foodie.dto.notification.NotificationDTO;
import com.project.foodie.services.notifications.NotificationService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/notifications")
@CrossOrigin
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    
    @ApiOperation(value = "Get all notifications by user Id", response = NotificationDTO.class)
    @GetMapping("user/{id}")
    public List<NotificationDTO> getAllNotificationByUserId(@PathVariable("id") Long userId) {
        List<NotificationDTO> notificationList = notificationService.getAllNotificationByUserId(userId);
        return notificationList;
    }
}
