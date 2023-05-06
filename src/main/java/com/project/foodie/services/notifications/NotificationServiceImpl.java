package com.project.foodie.services.notifications;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.foodie.dto.notification.NotificationDTO;
import com.project.foodie.model.notification.Notification;
import com.project.foodie.repository.notification.NotificationRepository;

@Service
public class NotificationServiceImpl implements NotificationService {

    Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<NotificationDTO> getAllNotificationByUserId(Long userId) {
        List<Notification> notifications = notificationRepository.findAllByUserId(userId);
        List<NotificationDTO> resultDTOs = notifications.stream().map(NotificationDTO::new)
                .collect(Collectors.toList());
        return resultDTOs;
    }

}
