package com.ust.empoweru.repository;

import com.ust.empoweru.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    @Query("From Notification N where N.employee.employeeId=:employeeId")
    List<Notification> findByEmployeeId(Long employeeId);

//    List<Notification> findByUserIdAndIsReadFalse(Long userId);
}
