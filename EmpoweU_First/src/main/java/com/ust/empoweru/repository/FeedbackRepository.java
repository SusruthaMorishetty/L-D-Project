package com.ust.empoweru.repository;

import com.ust.empoweru.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {

    @Query("From Feedback f where f.course.id=:courseId")
    List<Feedback> findByCourseId(Long courseId);
}
