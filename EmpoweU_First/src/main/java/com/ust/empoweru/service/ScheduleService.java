package com.ust.empoweru.service;

import com.ust.empoweru.model.Employee;
import com.ust.empoweru.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    Schedule saveSchedule(Schedule schedule, Long courseId, List<Long> employeeIds);

    Schedule getScheduleById(Long id);

    List<Schedule> getAllSchedules();

    void deleteSchedule(Long id);

    Schedule updateSchedule(Long scheduleId, Schedule updatedSchedule, Long courseId, List<Long> attendeeIds);

    List<Schedule> findByCourseId(Long courseId);

    List<Schedule> getSchedulesByAttendeeId(Long userId);

    List<Employee> getAttendeesByScheduleId(Long scheduleId);
}
