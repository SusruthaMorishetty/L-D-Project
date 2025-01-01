package com.ust.progress.repository;

import com.ust.progress.model.Progress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress,Long> {
    Optional<Progress> findByUserIdAndCourseId(Long userId, Long courseId);
    List<Progress> findByCourseId(Long courseId);
    Optional<Progress> findByUserId(Long userId);
}
