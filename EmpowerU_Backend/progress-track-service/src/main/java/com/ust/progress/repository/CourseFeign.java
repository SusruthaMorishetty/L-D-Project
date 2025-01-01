package com.ust.progress.repository;


import com.ust.progress.dto.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TRAININGPROGRAM-SERVICE")
public interface CourseFeign {

    @GetMapping("/api/course/{id}")
    public Course getCourseById(@PathVariable long id);
}
