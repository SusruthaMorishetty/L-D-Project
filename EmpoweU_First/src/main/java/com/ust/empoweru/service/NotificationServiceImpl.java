package com.ust.empoweru.service;

import com.ust.empoweru.model.Employee;
import com.ust.empoweru.model.Notification;
import com.ust.empoweru.repository.EmployeeRepository;
import com.ust.empoweru.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Notification saveNotification(Notification notification, Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()) {
            notification.setEmployee(employee.get());
            notification.setSendDate(LocalDate.now());
            return notificationRepository.save(notification);
        } else {
            throw new RuntimeException("Employee not found");
        }
    }

    @Override
    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> getNotificationsByEmployeeId(Long employeeId) {
        return notificationRepository.findByEmployeeId(employeeId);
    }

    @Override
    public void deleteNotification(Long id) {
        if (notificationRepository.existsById(id)) {
            notificationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Notification not found");
        }
    }

    @Override
    public Notification updateNotification(Long id, Notification updatedNotification) {
        Notification existingNotification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));


        existingNotification.setMessage(updatedNotification.getMessage());
        existingNotification.setSendDate(updatedNotification.getSendDate());

        return notificationRepository.save(existingNotification);
    }

//    @Override
//    public List<Notification> getUnreadNotificationsByUserId(Long userId) {
//        return notificationRepository.findByUserIdAndIsReadFalse(userId);
//    }
}
