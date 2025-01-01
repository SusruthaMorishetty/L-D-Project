package com.ust.empoweru.service;

import com.ust.empoweru.model.ProgressTracker;

import java.util.List;
import java.util.Optional;

public interface ProgressTrackerService {

    ProgressTracker saveProgressTracker(ProgressTracker progressTracker, Long employeeId, Long courseId);

    ProgressTracker getProgressTrackerById(Long id);

    List<ProgressTracker> getAllProgressTrackers();

    void deleteProgressTracker(Long id);

    ProgressTracker updateProgressTracker(Long progressTrackerId, ProgressTracker updatedProgressTracker, Long employeeId, Long courseId);

    List<ProgressTracker> findByEmployeeId(Long employeeId);

    List<ProgressTracker> findByCourseId(Long courseId);
}
