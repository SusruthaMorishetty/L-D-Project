package com.ust.feedback.repository;

import com.ust.feedback.dto.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TRAININGPROGRAM-SERVICE")
public interface CourseFeign {

    @GetMapping("/api/trainingProgram/course/{id}")
    Course getCourseById(@PathVariable long id);
}
