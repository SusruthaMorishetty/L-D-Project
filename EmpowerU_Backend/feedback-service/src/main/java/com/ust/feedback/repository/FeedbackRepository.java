package com.ust.feedback.repository;

import com.ust.feedback.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

    List<Feedback> findByUser_id(Long userId);
}
