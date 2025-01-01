package com.ust.empoweru.service;

import com.ust.empoweru.model.Notification;

import java.util.List;
import java.util.Optional;

public interface NotificationService {

    Notification saveNotification(Notification notification, Long employeeId);

    Notification getNotificationById(Long id);

    List<Notification> getAllNotifications();

    List<Notification> getNotificationsByEmployeeId(Long employeeId);

    void deleteNotification(Long id);

    Notification updateNotification(Long id, Notification updatedNotification);

//    List<Notification> getUnreadNotificationsByUserId(Long userId);
}
