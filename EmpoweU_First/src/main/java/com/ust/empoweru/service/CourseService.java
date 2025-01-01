package com.ust.empoweru.service;

import com.ust.empoweru.model.Course;
import com.ust.empoweru.model.TrainingProgram;

import java.util.List;

public interface CourseService {

    Course saveCourse(Course course, Long programId);

    List<Course> findAllCourses();

    Course findCourseById(Long id);

    Course updateCourse(Long id, Course course, Long programId);

    Course fetchCourseByCourseName(String courseName);

    void deleteCourse(Long courseId);

}
