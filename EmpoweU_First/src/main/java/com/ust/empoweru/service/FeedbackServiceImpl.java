package com.ust.empoweru.service;

import com.ust.empoweru.dto.FeedbackRequest;
import com.ust.empoweru.exception.NotFoundException;
import com.ust.empoweru.model.Course;
import com.ust.empoweru.model.Employee;
import com.ust.empoweru.model.Feedback;
import com.ust.empoweru.model.TrainingProgram;
import com.ust.empoweru.repository.CourseRepository;
import com.ust.empoweru.repository.EmployeeRepository;
import com.ust.empoweru.repository.FeedbackRepository;
import com.ust.empoweru.repository.TrainingProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Feedback saveFeedback(Feedback feedback, Long employeeId, Long courseId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        Optional<Course> course = courseRepository.findById(courseId);

        if (employee.isPresent() && course.isPresent()) {
            feedback.setEmployee(employee.get());
            feedback.setCourse(course.get());
            return feedbackRepository.save(feedback);
        } else {
            throw new NotFoundException("Employee or Course not found");
        }
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Feedback with Id"+id+" not found"));
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public void deleteFeedback(Long id) {
        if (feedbackRepository.existsById(id)) {
            feedbackRepository.deleteById(id);
        } else {
            throw new NotFoundException("Feedback with Id"+id+" not found");
        }
    }

    @Override
    public List<Feedback> findByEmployeeId(Long employeeId) {
        return feedbackRepository.findAll().stream()
                .filter(feedback -> feedback.getEmployee().getEmployeeId().equals(employeeId))
                .toList();
    }

    @Override
    public List<Feedback> findByCourseId(Long courseId) {
        return feedbackRepository.findAll().stream()
                .filter(feedback -> feedback.getCourse().getId().equals(courseId))
                .toList();
    }
}
