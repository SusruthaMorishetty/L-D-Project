package com.ust.empoweru.controller;


import com.ust.empoweru.model.Employee;
import com.ust.empoweru.model.Schedule;
import com.ust.empoweru.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;


    @PostMapping
    public ResponseEntity<Schedule> createSchedule(
            @RequestBody Schedule schedule,
            @RequestParam Long courseId,
            @RequestParam List<Long> attendeeIds) {
        Schedule savedSchedule = scheduleService.saveSchedule(schedule, courseId, attendeeIds);
        return new ResponseEntity<>(savedSchedule, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable Long id) {
        Schedule schedule = scheduleService.getScheduleById(id);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Schedule>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getAllSchedules();
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity<Schedule> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody Schedule updatedSchedule,
            @RequestParam(required = false) Long courseId,
            @RequestParam(required = false) List<Long> attendeeIds) {
        Schedule updated = scheduleService.updateSchedule(scheduleId, updatedSchedule, courseId, attendeeIds);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Schedule>> getSchedulesByCourseId(@PathVariable Long courseId) {
        List<Schedule> schedules = scheduleService.findByCourseId(courseId);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }


    @GetMapping("/attendee/{userId}")
    public ResponseEntity<List<Schedule>> getSchedulesByAttendeeId(@PathVariable Long userId) {
        List<Schedule> schedules = scheduleService.getSchedulesByAttendeeId(userId);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }

    @GetMapping("/{scheduleId}/attendees")
    public ResponseEntity<List<Employee>> getAttendeesByScheduleId(@PathVariable Long scheduleId) {
        List<Employee> attendees = scheduleService.getAttendeesByScheduleId(scheduleId);
        return new ResponseEntity<>(attendees, HttpStatus.OK);
    }
}
