package com.ust.feedback.controller;

import com.ust.feedback.dto.FeedbackDto;
import com.ust.feedback.dto.FeedbackRes;
import com.ust.feedback.model.Feedback;
import com.ust.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping
    public List<FeedbackRes> getAll(){
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("/{id}")
    public Feedback getFeedbackById(@PathVariable long id){
        return feedbackService.getFeedbackById(id);
    }

    @PostMapping("/user/{userId}/course/{courseId}")
    public FeedbackRes addFeedback(@PathVariable Long userId, @PathVariable Long courseId, @RequestBody FeedbackDto feedback){
        return feedbackService.createFeedback(userId,courseId,feedback);
    }

    @PutMapping("/{id}")
    public Feedback updateFeedback(@PathVariable long id,@RequestBody Feedback feedback){
        return feedbackService.updateFeedback(id,feedback);
    }

    @DeleteMapping("/{id}")
    public void deleteFeedback(@PathVariable long id){
        feedbackService.deleteFeedback(id);
    }

    @GetMapping("/user/{userId}")
    public List<Feedback> getFeedbacksByUserId(@PathVariable long userid){
        return feedbackService.getFeedbackByUserId(userid);
    }
}
