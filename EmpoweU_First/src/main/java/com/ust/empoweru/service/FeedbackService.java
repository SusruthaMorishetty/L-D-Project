package com.ust.empoweru.service;

import com.ust.empoweru.dto.FeedbackRequest;
import com.ust.empoweru.model.Feedback;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    Feedback saveFeedback(Feedback feedback, Long employeeId, Long courseId);

    Feedback getFeedbackById(Long id);

    List<Feedback> getAllFeedbacks();

    void deleteFeedback(Long id);

    List<Feedback> findByEmployeeId(Long employeeId);

    List<Feedback> findByCourseId(Long courseId);
}
