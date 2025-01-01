package com.ust.empoweru.controller;

import com.ust.empoweru.model.Notification;
import com.ust.empoweru.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification, @RequestParam Long employeeId) {
        Notification createdNotification = notificationService.saveNotification(notification, employeeId);
        return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        Notification notification = notificationService.getNotificationById(id);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }


    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Notification>> getNotificationsByEmployeeId(@PathVariable Long employeeId) {
        List<Notification> notifications = notificationService.getNotificationsByEmployeeId(employeeId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(
            @PathVariable Long id,
            @RequestBody Notification updatedNotification) {
        Notification notification = notificationService.updateNotification(id, updatedNotification);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
