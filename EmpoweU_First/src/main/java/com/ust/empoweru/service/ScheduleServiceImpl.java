package com.ust.empoweru.service;

import com.ust.empoweru.model.Course;
import com.ust.empoweru.model.Employee;
import com.ust.empoweru.model.Schedule;
import com.ust.empoweru.repository.CourseRepository;
import com.ust.empoweru.repository.EmployeeRepository;
import com.ust.empoweru.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ScheduleServiceImpl implements ScheduleService{

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Schedule saveSchedule(Schedule schedule, Long courseId, List<Long> employeeIds) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            schedule.setCourse(course.get());

            List<Employee> attendees = employeeRepository.findAllById(employeeIds);
            schedule.setAttendees(attendees);

            return scheduleRepository.save(schedule);
        } else {
            throw new RuntimeException("Course not found");
        }
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule updateSchedule(Long scheduleId, Schedule updatedSchedule, Long courseId, List<Long> attendeeIds) {
        Schedule existingSchedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));


        existingSchedule.setScheduledDate(updatedSchedule.getScheduledDate());
        existingSchedule.setMode(updatedSchedule.getMode());
        existingSchedule.setLocationOrLink(updatedSchedule.getLocationOrLink());


        if (courseId != null) {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            existingSchedule.setCourse(course);
        }


        if (attendeeIds != null && !attendeeIds.isEmpty()) {
            List<Employee> attendees = employeeRepository.findAllById(attendeeIds);
            existingSchedule.setAttendees(attendees);
        }

        return scheduleRepository.save(existingSchedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        if (scheduleRepository.existsById(id)) {
            scheduleRepository.deleteById(id);
        } else {
            throw new RuntimeException("Schedule not found");
        }
    }

    @Override
    public List<Schedule> findByCourseId(Long courseId) {
        return scheduleRepository.findAll().stream()
                .filter(schedule -> schedule.getCourse().getId().equals(courseId))
                .toList();
    }
    @Override
    public List<Schedule> getSchedulesByAttendeeId(Long userId) {
        return scheduleRepository.findSchedulesByAttendeeId(userId);
    }

    @Override
    public List<Employee> getAttendeesByScheduleId(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
        return schedule.getAttendees();
    }

}
