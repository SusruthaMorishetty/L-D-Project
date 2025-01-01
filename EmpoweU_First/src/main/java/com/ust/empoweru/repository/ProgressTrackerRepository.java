package com.ust.empoweru.repository;

import com.ust.empoweru.model.ProgressTracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProgressTrackerRepository extends JpaRepository<ProgressTracker,Long> {


}
