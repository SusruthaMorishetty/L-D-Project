package com.ust.empoweru.service;

import com.ust.empoweru.exception.NotFoundException;
import com.ust.empoweru.model.Course;
import com.ust.empoweru.model.Employee;
import com.ust.empoweru.model.ProgressTracker;
import com.ust.empoweru.repository.CourseRepository;
import com.ust.empoweru.repository.EmployeeRepository;
import com.ust.empoweru.repository.ProgressTrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressTrackerServiceImpl implements ProgressTrackerService {

    @Autowired
    private ProgressTrackerRepository progressTrackerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public ProgressTracker saveProgressTracker(ProgressTracker progressTracker, Long employeeId, Long courseId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Optional<Course> course = courseRepository.findById(courseId);

        if (employee.isPresent() && course.isPresent()) {
            progressTracker.setEmployee(employee.get());
            progressTracker.setCourse(course.get());
            return progressTrackerRepository.save(progressTracker);
        } else {
            throw new NotFoundException("Employee or Course not found");
        }
    }

    @Override
    public ProgressTracker getProgressTrackerById(Long id) {
        return progressTrackerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ProgressTracker with Id"+id+" not found"));
    }

    @Override
    public List<ProgressTracker> getAllProgressTrackers() {
        return progressTrackerRepository.findAll();
    }

    @Override
    public void deleteProgressTracker(Long id) {
        if (progressTrackerRepository.existsById(id)) {
            progressTrackerRepository.deleteById(id);
        } else {
            throw new NotFoundException("ProgressTracker with Id"+ id+" not found");
        }
    }

    @Override
    public ProgressTracker updateProgressTracker(Long progressTrackerId, ProgressTracker updatedProgressTracker, Long employeeId, Long courseId) {

        ProgressTracker existingProgressTracker = progressTrackerRepository.findById(progressTrackerId)
                .orElseThrow(() -> new NotFoundException("Progress Tracker not found"));

        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Optional<Course> course = courseRepository.findById(courseId);

        if (employee.isPresent() && course.isPresent()) {

            existingProgressTracker.setEmployee(employee.get());
            existingProgressTracker.setCourse(course.get());


            existingProgressTracker.setCompletionDate(updatedProgressTracker.getCompletionDate());
            existingProgressTracker.setStartDate(updatedProgressTracker.getStartDate());
            existingProgressTracker.setStatus(updatedProgressTracker.getStatus());


            return progressTrackerRepository.save(existingProgressTracker);
        } else {
            throw new NotFoundException("Employee or Course not found");
        }
    }


    @Override
    public List<ProgressTracker> findByEmployeeId(Long employeeId) {

        return progressTrackerRepository.findAll().stream()
                .filter(progressTracker -> progressTracker.getEmployee().getEmployeeId().equals(employeeId))
                .toList();
    }

    @Override
    public List<ProgressTracker> findByCourseId(Long courseId) {

        return progressTrackerRepository.findAll().stream()
                .filter(progressTracker -> progressTracker.getCourse().getId().equals(courseId))
                .toList();
    }
}
