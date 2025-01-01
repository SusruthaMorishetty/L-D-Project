package com.ust.progress.controller;

import com.ust.progress.model.Progress;
import com.ust.progress.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    @PostMapping
    public ResponseEntity<Progress> createProgress(@RequestBody Progress progress) {
        try {
            Progress createdProgress = progressService.createProgress(progress);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProgress);
        } catch (Exception e) {
            // Handle other potential exceptions, e.g., validation errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public List<Map<String, Object>> getAllProgress() {
        List<Progress> progresses = progressService.getAllProgress();
        // Map each Progress object to a simpler structure

        return progresses.stream().map(progress -> {
            Map<String, Object> response = new HashMap<>();
            response.put("progressId", progress.getProgressId());
            response.put("userId", progress.getUserId());
            response.put("courseId", progress.getCourseId());
            response.put("status", progress.getStatus());
            return response;
        }).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public Progress getById(@PathVariable long id){
        return progressService.getProgressById(id);
    }

//    @GetMapping("/userprogress/{id}")
//    public List<Progress> getProgressByUserId(@PathVariable long id){
//        return progressService.findProgressByUserId(id);
//    }

    // Update an existing progress record
    @PutMapping("/{progressId}")
    public ResponseEntity<Progress> updateProgress(@PathVariable Long progressId, @RequestBody Progress progress) {
        try {
            Progress updatedProgress = progressService.updateProgress(progressId, progress);
            if (updatedProgress != null) {
                return ResponseEntity.ok(updatedProgress);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProgress(@PathVariable long id){
        progressService.deleteProgress(id);
    }

    @GetMapping("/user/{userId}/course/{courseId}")
    public Map<String, Object> getProgressByUserIdAndCourseId(@PathVariable Long userId, @PathVariable Long courseId) {
        Progress progress = progressService.getProgressByUserIdAndCourseId(userId, courseId);
        // Constructing a custom response map
        Map<String, Object> response = new HashMap<>();
        response.put("progressId", progress.getProgressId());
        response.put("userId", progress.getUserId());
        response.put("courseId", progress.getCourseId());
        response.put("status", progress.getStatus());
        return response;
    }



    @GetMapping("/course/{courseId}")
    public List<Progress> getProgressByCourseId(@PathVariable Long courseId){
        return progressService.getProgressForCourse(courseId);
    }

    @GetMapping("/user/{userId}")
    public Progress getProgressForUser(@PathVariable Long userId) {
        return progressService. getUserProgress(userId);
    }

    // Custom exception class for handling resource not found scenarios
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }
}
