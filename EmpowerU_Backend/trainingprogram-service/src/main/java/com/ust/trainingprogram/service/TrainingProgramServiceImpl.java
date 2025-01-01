package com.ust.trainingprogram.service;

import com.ust.trainingprogram.dto.TrainingProgramDto;
import com.ust.trainingprogram.dto.User;
import com.ust.trainingprogram.exception.NotFoundException;
import com.ust.trainingprogram.fiegnClient.UserFiegnClient;
import com.ust.trainingprogram.model.Course;
import com.ust.trainingprogram.model.TrainingProgram;
import com.ust.trainingprogram.repository.CourseRepository;
import com.ust.trainingprogram.repository.TrainingProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingProgramServiceImpl implements TrainingProgramService {

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserFiegnClient userFiegnClient;

    @Override
    public TrainingProgram saveTrainingProgram(TrainingProgram trainingProgram) {
//        if(trainingProgramRepository.existsById(trainingProgram.getId())){
//            throw  new RuntimeException("trainingProgram with "+trainingProgram.getId()+" already exist.");
//        }
        return trainingProgramRepository.save(trainingProgram);

    }

    @Override
    public TrainingProgram updateTrainingProgram(Long id, TrainingProgram trainingProgram) {
        return null;
    }

    @Override
    public void deleteTrainingProgram(Long id) {
        if(!trainingProgramRepository.existsById(id)){
            throw new RuntimeException("trainingProgram with "+id+" not exist");
        }
        trainingProgramRepository.deleteById(id);

    }

    @Override
    public Optional<TrainingProgram> getTrainingProgramById(Long id) {

        return trainingProgramRepository.findById(id);
    }

    @Override
    public List<TrainingProgram> getAllTrainingPrograms() {
        return trainingProgramRepository.findAll();
    }

    @Override
    public TrainingProgram addUserToTrainingProgram(Long trainingProgramId, Long userId) {
        TrainingProgram trainingProgram=getTrainingProgramById(trainingProgramId).get();
        trainingProgram.getTrainees().add(userId);
        trainingProgramRepository.save(trainingProgram);
        return trainingProgram;
    }

    @Override
    public void removeUserFromTrainingProgram(Long trainingProgramId, Long userId) {
        TrainingProgram trainingProgram=getTrainingProgramById(trainingProgramId).get();
        trainingProgram.getTrainees().remove(userId);
        trainingProgramRepository.save(trainingProgram);

    }

    public List<TrainingProgram> getAllTrainingProgramsByUserId(Long userId) {
        // Fetch all training programs
        List<TrainingProgram> allTrainingPrograms = trainingProgramRepository.findAll();

        // Filter the training programs by the userId (check if the userId exists in the trainees set)
        List<TrainingProgram> userTrainingPrograms = allTrainingPrograms.stream()
                .filter(trainingProgram -> trainingProgram.getTrainees().contains(userId))
                .toList();

        return userTrainingPrograms;
    }

    @Override
    public List<User> getUserByTrainingProgram(Long trainingProgramId) {
        TrainingProgram trainingProgram=getTrainingProgramById(trainingProgramId).get();
        List<User>students=new ArrayList<>();
        for(Long userId: trainingProgram.getTrainees()){
            students.add(userFiegnClient.getUserById(userId));
        }
        return students;
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
