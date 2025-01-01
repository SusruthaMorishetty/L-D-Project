package com.ust.empoweru.repository;

import com.ust.empoweru.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {

    @Query("From Course where title=:courseName")
    Optional<Course> findByCourseName(String courseName);
}
