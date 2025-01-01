package com.ust.feedback.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ENROLLMENTSERVICEAPPLICATION")
public interface EnrollFeign {

    @GetMapping("/api/enroll/check/{userId}/{courseId}")
    Boolean isUserEnrolledInCourse(@PathVariable("userId") Long userId, @PathVariable("courseId") Long courseId);
}
