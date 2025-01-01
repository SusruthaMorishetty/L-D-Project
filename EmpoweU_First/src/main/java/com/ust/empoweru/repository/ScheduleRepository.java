package com.ust.empoweru.repository;

import com.ust.empoweru.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

//    @Query("From Schedule S where S.course.id=:courseId")
//    List<Schedule> findByCourseId(Long courseId);

    @Query("SELECT s FROM Schedule s JOIN s.attendees a WHERE a.id = :userId")
    List<Schedule> findSchedulesByAttendeeId(@Param("userId") Long userId);
}
