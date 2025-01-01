package com.ust.empoweru.service;

import com.ust.empoweru.model.Course;
import com.ust.empoweru.model.Employee;
import com.ust.empoweru.model.TrainingProgram;

import java.util.List;
import java.util.Optional;

public interface TrainingProgramService {

    TrainingProgram saveTrainingProgram(TrainingProgram trainingProgram);

    TrainingProgram updateTrainingProgram(Long id, TrainingProgram trainingProgram);

    void deleteTrainingProgram(Long id);

    Optional<TrainingProgram> getTrainingProgramById(Long id);

    List<Course> getCoursesByTrainingProgram(Long programId);

    List<TrainingProgram> getAllTrainingPrograms();

    TrainingProgram addEmployeeToTrainingProgram(Long trainingProgramId, Long employeeId);

    TrainingProgram removeEmployeeFromTrainingProgram(Long trainingProgramId, Long employeeId);

    TrainingProgram addCourseToTrainingProgram(Long trainingProgramId, Long courseId);

    TrainingProgram removeCourseFromTrainingProgram(Long trainingProgramId, Long courseId);

    List<Employee> getEmployeesByTrainingProgram(Long trainingProgramId);

}

