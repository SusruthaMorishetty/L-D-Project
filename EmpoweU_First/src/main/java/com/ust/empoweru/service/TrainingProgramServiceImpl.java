package com.ust.empoweru.service;

import com.ust.empoweru.exception.NotFoundException;
import com.ust.empoweru.model.Course;


import com.ust.empoweru.model.Employee;
import com.ust.empoweru.model.TrainingProgram;


import com.ust.empoweru.repository.CourseRepository;
import com.ust.empoweru.repository.EmployeeRepository;
import com.ust.empoweru.repository.TrainingProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingProgramServiceImpl implements TrainingProgramService {

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<TrainingProgram> getAllTrainingPrograms() {
        return trainingProgramRepository.findAll();
    }

    public Optional<TrainingProgram> getTrainingProgramById(Long trainingProgramId) {
        return trainingProgramRepository.findById(trainingProgramId);
    }

    public TrainingProgram saveTrainingProgram(TrainingProgram trainingProgram) {
        return trainingProgramRepository.save(trainingProgram);
    }

    public void deleteTrainingProgram(Long trainingProgramId) {
        if (!trainingProgramRepository.existsById(trainingProgramId)) {
            throw new NotFoundException("Training program with Id "+trainingProgramId+" not found");
        }
        trainingProgramRepository.deleteById(trainingProgramId);
    }

    public TrainingProgram updateTrainingProgram(Long trainingProgramId, TrainingProgram updatedTrainingProgram) {
        TrainingProgram existingTrainingProgram = trainingProgramRepository.findById(trainingProgramId)
                .orElseThrow(() -> new NotFoundException("Training program with Id "+trainingProgramId+" not found"));


        existingTrainingProgram.setTitle(updatedTrainingProgram.getTitle());
        existingTrainingProgram.setDescription(updatedTrainingProgram.getDescription());
        existingTrainingProgram.setTargetRole(updatedTrainingProgram.getTargetRole());


        return trainingProgramRepository.save(existingTrainingProgram);
    }



    public TrainingProgram addEmployeeToTrainingProgram(Long trainingProgramId, Long employeeId) {
        TrainingProgram trainingProgram = trainingProgramRepository.findById(trainingProgramId)
                .orElseThrow(() -> new NotFoundException("Training program with Id "+trainingProgramId+" not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee with Id"+ employeeId+" not found"));

        trainingProgram.getEmployees().add(employee);
        employee.getTrainingPrograms().add(trainingProgram);

        return trainingProgramRepository.save(trainingProgram);
    }

    public List<Employee> getEmployeesByTrainingProgram(Long trainingProgramId) {
        TrainingProgram trainingProgram = trainingProgramRepository.findById(trainingProgramId)
                .orElseThrow(() -> new NotFoundException("Training program with Id"+trainingProgramId+" not found"));

        return List.copyOf(trainingProgram.getEmployees());
    }


    public TrainingProgram removeEmployeeFromTrainingProgram(Long trainingProgramId, Long employeeId) {
        TrainingProgram trainingProgram = trainingProgramRepository.findById(trainingProgramId)
                .orElseThrow(() -> new NotFoundException("Training program with Id "+trainingProgramId +" not found"));

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee with Id "+employeeId+" not found"));

        trainingProgram.getEmployees().remove(employee);
        employee.getTrainingPrograms().remove(trainingProgram);

        return trainingProgramRepository.save(trainingProgram);
    }

    public TrainingProgram addCourseToTrainingProgram(Long trainingProgramId, Long courseId) {
        TrainingProgram trainingProgram = trainingProgramRepository.findById(trainingProgramId)
                .orElseThrow(() -> new NotFoundException("Training program with Id "+trainingProgramId+" not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course with Id "+courseId+" not found"));

        trainingProgram.getCourses().add(course);
        course.setTrainingProgram(trainingProgram);

        return trainingProgramRepository.save(trainingProgram);
    }

    public TrainingProgram removeCourseFromTrainingProgram(Long trainingProgramId, Long courseId) {
        TrainingProgram trainingProgram = trainingProgramRepository.findById(trainingProgramId)
                .orElseThrow(() -> new NotFoundException("Training program with Id "+trainingProgramId+" not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not with Id "+courseId+" found"));

        trainingProgram.getCourses().remove(course);
        course.setTrainingProgram(null); // Clear the training program in the course

        return trainingProgramRepository.save(trainingProgram);
    }

    public List<Course> getCoursesByTrainingProgram(Long trainingProgramId) {
        TrainingProgram trainingProgram = trainingProgramRepository.findById(trainingProgramId)
                .orElseThrow(() -> new NotFoundException("Training program with Id "+trainingProgramId+" not found"));

        return trainingProgram.getCourses();
    }
}

