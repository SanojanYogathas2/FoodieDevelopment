package com.project.foodie.services.notifications;

import java.util.List;
import com.project.foodie.dto.notification.NotificationDTO;

public interface NotificationService {
    List<NotificationDTO> getAllNotificationByUserId(Long userId);
}
