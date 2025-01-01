package com.ust.trainingprogram.service;


import com.ust.trainingprogram.model.Course;
import com.ust.trainingprogram.model.TrainingProgram;
import com.ust.trainingprogram.repository.CourseRepository;
import com.ust.trainingprogram.repository.TrainingProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;


    public Course saveCourse(Course course,Long programId){
        if (programId != null) {
            TrainingProgram trainingProgram = trainingProgramRepository.findById(programId)
                    .orElseThrow(() -> new RuntimeException("Training Program with Id "+programId+" not found"));
            course.setTrainingProgram(trainingProgram);
        }
        return courseRepository.save(course);
    }

    public List<Course> findAllCourses(){
        return courseRepository.findAll();
    }

    public Optional<Course> findCourseById(Long id){
        if(!courseRepository.existsById(id)){
            throw new RuntimeException("course id"+ id+" not found");
        }
        return courseRepository.findById(id);

    }


    public Course updateCourse(Long id, Course updatedCourse,Long trainingProgramId){
        if(!courseRepository.existsById(id)) {
            throw new RuntimeException("course with Id "+id+" not found.");
        }
        Course existingCourse = findCourseById(id).get();
        existingCourse.setTitle(updatedCourse.getTitle());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setContentUrl(updatedCourse.getContentUrl());

        if (trainingProgramId != null) {
            TrainingProgram trainingProgram = trainingProgramRepository.findById(trainingProgramId)
                    .orElseThrow(() -> new RuntimeException("Training Program with id "+trainingProgramId+" not found"));
            existingCourse.setTrainingProgram(trainingProgram);
        } else {
            existingCourse.setTrainingProgram(null);
        }

        return courseRepository.save(existingCourse);


    }



    public void  deleteCourse(Long courseId){
        Course course = findCourseById(courseId).get();
        if(course==null){
            throw new RuntimeException("course with id "+courseId+" doesn't exists");
        }
        course.setTrainingProgram(null);
        courseRepository.delete(course);
    }

}
