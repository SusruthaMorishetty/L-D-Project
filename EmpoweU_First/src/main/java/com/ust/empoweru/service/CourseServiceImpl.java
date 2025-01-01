package com.ust.empoweru.service;

import com.ust.empoweru.exception.NotFoundException;
import com.ust.empoweru.model.Course;
import com.ust.empoweru.model.TrainingProgram;
import com.ust.empoweru.repository.CourseRepository;
import com.ust.empoweru.repository.TrainingProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;


    public Course saveCourse(Course course,Long programId){
        if (programId != null) {
            TrainingProgram trainingProgram = trainingProgramRepository.findById(programId)
                    .orElseThrow(() -> new NotFoundException("Training Program with Id "+programId+" not found"));
            course.setTrainingProgram(trainingProgram);
        }
        return courseRepository.save(course);
    }

    public List<Course> findAllCourses(){
        return courseRepository.findAll();
    }

    public Course findCourseById(Long id){
        if(!courseRepository.existsById(id)){
            throw new NotFoundException("course id"+ id+" not found");
        }
        return courseRepository.findById(id).get();

    }


    public Course updateCourse(Long id, Course updatedCourse,Long trainingProgramId){
        if(!courseRepository.existsById(id)) {
            throw new NotFoundException("course with Id "+id+" not found.");
        }
        Course existingCourse = findCourseById(id);
        existingCourse.setTitle(updatedCourse.getTitle());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setContentUrl(updatedCourse.getContentUrl());

        if (trainingProgramId != null) {
            TrainingProgram trainingProgram = trainingProgramRepository.findById(trainingProgramId)
                    .orElseThrow(() -> new NotFoundException("Training Program with id "+trainingProgramId+" not found"));
            existingCourse.setTrainingProgram(trainingProgram);
        } else {
            existingCourse.setTrainingProgram(null);
        }

        return courseRepository.save(existingCourse);


    }

    public Course fetchCourseByCourseName(String courseName){
        return courseRepository.findByCourseName(courseName).orElseThrow(()->new NotFoundException("Course with "+courseName+ "does'nt exists"));
    }

    public void  deleteCourse(Long courseId){
        Course course = findCourseById(courseId);
        if(course==null){
            throw new NotFoundException("course with id "+courseId+" doesn't exists");
        }
        course.setTrainingProgram(null);
        courseRepository.delete(course);
    }

}
