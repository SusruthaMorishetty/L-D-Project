package com.ust.empoweru.repository;

import com.ust.empoweru.model.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainingProgramRepository extends JpaRepository<TrainingProgram,Long> {


}
