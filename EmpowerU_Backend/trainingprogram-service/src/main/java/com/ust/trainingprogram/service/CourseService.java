package com.ust.trainingprogram.service;

import com.ust.trainingprogram.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course saveCourse(Course course, Long programId);

    List<Course> findAllCourses();

    Optional<Course> findCourseById(Long id);

    Course updateCourse(Long id, Course course, Long programId);

//    Course fetchCourseByCourseName(String courseName);

    void deleteCourse(Long courseId);
}
