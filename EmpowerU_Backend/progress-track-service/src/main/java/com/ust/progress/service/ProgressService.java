package com.ust.progress.service;

import com.ust.progress.controller.ProgressController;
import com.ust.progress.dto.Course;
import com.ust.progress.dto.User;
import com.ust.progress.model.Progress;
import com.ust.progress.repository.CourseFeign;
import com.ust.progress.repository.ProgressRepository;
import com.ust.progress.repository.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    @Autowired
    private CourseFeign courseFeign;

    @Autowired
    private UserFeign userFeign;

    // Create a new progress record
    public Progress createProgress(Progress progress) {
        return progressRepository.save(progress);
    }

    public List<Progress> getAllProgress(){
        return progressRepository.findAll();
    }

    public Progress getProgressById(long id){
        if(progressRepository.existsById(id)) {
            return progressRepository.findById(id).get();
        }
        throw new RuntimeException("progress with id "+id+" not found");
    }

    // Retrieve progress by user ID, throwing an exception if not found
    public Progress getUserProgress(Long userId) {
        return progressRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Progress not found for user ID: " + userId));
    }

    // Update an existing progress record
    public Progress updateProgress(Long progressId, Progress progress) {
        return progressRepository.findById(progressId)
                .map(existingProgress -> {
                    existingProgress.setUserId(progress.getUserId());
                    existingProgress.setCourseId(progress.getCourseId());
                    existingProgress.setStatus(progress.getStatus());
                    return progressRepository.save(existingProgress);
                })
                .orElseThrow(() -> new ProgressController.ResourceNotFoundException("Progress not found for ID: " + progressId));
    }

    // Delete a progress record by ID
    public void deleteProgress(Long progressId) {
        if (progressRepository.existsById(progressId)) {
            progressRepository.deleteById(progressId);
        } else {
            throw new RuntimeException("Progress not found for ID: " + progressId);
        }
    }

    public Progress getProgressByUserIdAndCourseId(Long userId, Long courseId) {
        return progressRepository.findByUserIdAndCourseId(userId, courseId)
                .orElseThrow(() -> new RuntimeException("Progress not found for user and course"));
    }

    // Get all progress records for a specific course
    public List<Progress> getProgressForCourse(Long courseId) {
        return progressRepository.findByCourseId(courseId);
    }


//    public List<Progress> findProgressByUserId(long userid){
//
//        return progressRepository.findByUser_id(userid);
//    }


    public Course fetchCourseById(long id){
        return courseFeign.getCourseById(id);
    }

    public User fetchUserById(long id){
        return userFeign.getUserById(id);
    }
}
