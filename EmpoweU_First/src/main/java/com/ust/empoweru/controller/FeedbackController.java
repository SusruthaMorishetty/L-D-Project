package com.ust.empoweru.controller;

import com.ust.empoweru.dto.FeedbackRequest;
import com.ust.empoweru.model.Feedback;
import com.ust.empoweru.service.FeedbackService;
import com.ust.empoweru.service.FeedbackServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;


    @PostMapping("/{employeeId}/{courseId}")
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback, @PathVariable Long employeeId, @PathVariable Long courseId) {
        Feedback savedFeedback = feedbackService.saveFeedback(feedback, employeeId, courseId);
        return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        Feedback updatedFeedback = feedbackService.getFeedbackById(id);
        updatedFeedback.setRating(feedback.getRating());
        updatedFeedback.setComments(feedback.getComments());
        feedbackService.saveFeedback(updatedFeedback, updatedFeedback.getEmployee().getEmployeeId(), updatedFeedback.getCourse().getId());
        return new ResponseEntity<>(updatedFeedback, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Feedback>> getFeedbacksByEmployeeId(@PathVariable Long employeeId) {
        List<Feedback> feedbacks = feedbackService.findByEmployeeId(employeeId);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }


    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Feedback>> getFeedbacksByCourseId(@PathVariable Long courseId) {
        List<Feedback> feedbacks = feedbackService.findByCourseId(courseId);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }
}
