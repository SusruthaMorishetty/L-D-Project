package com.ust.trainingprogram.service;

import com.ust.trainingprogram.dto.TrainingProgramDto;
import com.ust.trainingprogram.dto.User;
import com.ust.trainingprogram.model.Course;
import com.ust.trainingprogram.model.TrainingProgram;

import java.util.List;
import java.util.Optional;

public interface TrainingProgramService {

    TrainingProgram saveTrainingProgram(TrainingProgram trainingProgram);

    TrainingProgram updateTrainingProgram(Long id, TrainingProgram trainingProgram);

    void deleteTrainingProgram(Long id);

    Optional<TrainingProgram> getTrainingProgramById(Long id);

    List<Course> getCoursesByTrainingProgram(Long programId);

    List<TrainingProgram> getAllTrainingPrograms();

    TrainingProgram addUserToTrainingProgram(Long trainingProgramId, Long userId);

    void removeUserFromTrainingProgram(Long trainingProgramId, Long userId);

    TrainingProgram addCourseToTrainingProgram(Long trainingProgramId, Long courseId);

    TrainingProgram removeCourseFromTrainingProgram(Long trainingProgramId, Long courseId);

    List<TrainingProgram> getAllTrainingProgramsByUserId(Long userId);

    List<User> getUserByTrainingProgram(Long trainingProgramId);
}
