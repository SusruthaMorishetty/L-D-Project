package com.ust.trainingprogram.controller;

import com.ust.trainingprogram.dto.User;
import com.ust.trainingprogram.model.Course;
import com.ust.trainingprogram.model.TrainingProgram;
import com.ust.trainingprogram.service.TrainingProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/trainingProgram")
public class TrainingProgramController {

    @Autowired
    private TrainingProgramService trainingProgramService;


    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TrainingProgram saveTrainingProgram(@RequestBody TrainingProgram trainingProgram){
        return trainingProgramService.saveTrainingProgram(trainingProgram);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingProgram>getAllTrainingPrograms(){
        return trainingProgramService.getAllTrainingPrograms();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<TrainingProgram> getTrainingProgramById(@PathVariable Long id){
        return trainingProgramService.getTrainingProgramById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTrainingProgram(@PathVariable Long id){
       trainingProgramService.deleteTrainingProgram(id);
    }

    @PostMapping("/{trainingProgramId}/{userId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TrainingProgram addTraineeToTrainingProgram(@PathVariable Long trainingProgramId, @PathVariable Long userId){
        return trainingProgramService.addUserToTrainingProgram(trainingProgramId,userId);
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainingProgram> getAllTrainingProgramsByUserId(@PathVariable Long userId) {
        return trainingProgramService.getAllTrainingProgramsByUserId(userId);
    }

    @GetMapping("/{trainingProgramId}/trainee")
    @ResponseStatus(HttpStatus.OK)
    public List<User>getAllTraineeOfTrainingProgram(Long trainingProgramId){
        return trainingProgramService.getUserByTrainingProgram(trainingProgramId);
    }

    @DeleteMapping("/{trainingProgramId}/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTraineeFromTrainingProgram(@PathVariable Long trainingProgramId, @PathVariable Long userId){
        trainingProgramService.removeUserFromTrainingProgram(trainingProgramId,userId);
    }

    @PostMapping("/{trainingProgramId}/courses/{courseId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TrainingProgram addCourseToTrainingProgram(@PathVariable Long trainingProgramId, @PathVariable Long courseId) {
        return trainingProgramService.addCourseToTrainingProgram(trainingProgramId, courseId);

    }

    @DeleteMapping("/{trainingProgramId}/courses/{courseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TrainingProgram removeCourseFromTrainingProgram(@PathVariable Long trainingProgramId, @PathVariable Long courseId) {
        return trainingProgramService.removeCourseFromTrainingProgram(trainingProgramId, courseId);

    }

    @GetMapping("/{trainingProgramId}/courses")
    @ResponseStatus(HttpStatus.OK)
    public List<Course>getAllCoursesInTrainingProgram(@PathVariable Long trainingProgramId){
        return  trainingProgramService.getCoursesByTrainingProgram(trainingProgramId);
    }

}
