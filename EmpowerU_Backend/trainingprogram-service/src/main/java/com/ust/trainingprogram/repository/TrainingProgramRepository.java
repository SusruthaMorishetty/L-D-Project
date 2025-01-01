package com.ust.trainingprogram.repository;

import com.ust.trainingprogram.model.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingProgramRepository extends JpaRepository<TrainingProgram,Long> {
}
