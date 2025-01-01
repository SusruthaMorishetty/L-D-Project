package com.ust.feedback.service;


import com.ust.feedback.dto.*;
import com.ust.feedback.model.Feedback;
import com.ust.feedback.repository.CourseFeign;
import com.ust.feedback.repository.EnrollFeign;
import com.ust.feedback.repository.FeedbackRepository;
import com.ust.feedback.repository.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private CourseFeign courseFeign;

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private EnrollFeign enrollFeign;

    public FeedbackRes createFeedback(Long userId, Long courseId, FeedbackDto feedbackDto){
//        boolean isEnrolled=enrollFeign.isUserEnrolledInCourse(userId,courseId);
//        if(!isEnrolled){
//            throw new RuntimeException(userId+" User is not enrolled to the course "+courseId+", cannot give feedback");
//        }

        Feedback feedback = new Feedback();
        feedback.setCourse(fetchCourse(courseId));
        feedback.setUser(fetchUser(userId));
        feedback.setRating(feedbackDto.rating());
        feedback.setDescription(feedbackDto.description());
        Feedback feedback1=feedbackRepository.save(feedback);

        return new FeedbackRes(feedback1.getFeedbackId(),feedback1.getCourse().getTitle(),feedback1.getRating(),feedback1.getDescription());
    }

    public List<FeedbackRes> getAllFeedbacks(){

        List<Feedback>feedbacks =feedbackRepository.findAll();
        List<FeedbackRes>feedbackRes=new ArrayList<>();
        for(var f: feedbacks){
            feedbackRes.add(new FeedbackRes(f.getFeedbackId(),f.getCourse().getTitle(),f.getRating(),f.getDescription()));
        }
        return feedbackRes;
    }

    public Feedback getFeedbackById(Long id){
        if(feedbackRepository.existsById(id)){
            return feedbackRepository.findById(id).get();
        }
        throw new RuntimeException("Feedback with Id "+id+" Not Found");
    }

    public void  deleteFeedback(Long id){
        if(feedbackRepository.existsById(id)){
            feedbackRepository.deleteById(id);
            return;
        }
        throw new RuntimeException("Cannot Find");
    }

    public Feedback updateFeedback(Long id,Feedback feedback){
        Feedback existing;
        if(feedbackRepository.existsById(id)){
            existing=feedbackRepository.findById(id).get();
            existing.setFeedbackId(feedback.getFeedbackId());
            existing.setUser(feedback.getUser());
            existing.setCourse(feedback.getCourse());
            existing.setDescription(feedback.getDescription());
            existing.setRating(feedback.getRating());
            return existing;
        }
        throw new RuntimeException("Feedback not found");
    }

    //get feedback by userId
    public List<Feedback> getFeedbackByUserId(Long userId){
        return feedbackRepository.findByUser_id(userId);
    }

    public Course fetchCourse(long courseId){
       return courseFeign.getCourseById(courseId);
    }

    public User fetchUser(long userId){
        return userFeign.getUserById(userId);
    }


}
