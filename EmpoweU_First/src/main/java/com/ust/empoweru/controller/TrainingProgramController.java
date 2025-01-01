package com.ust.empoweru.controller;

import com.ust.empoweru.model.Course;
import com.ust.empoweru.model.Employee;
import com.ust.empoweru.model.TrainingProgram;
import com.ust.empoweru.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/trainingProgram")
public class TrainingProgramController {

    @Autowired
    private TrainingProgramService trainingProgramService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<TrainingProgram>>getAllTrainingPrograms(){
        return ResponseEntity.ok(trainingProgramService.getAllTrainingPrograms());
    }


    @PostMapping
    public ResponseEntity<TrainingProgram>addNewEnrollment(@RequestBody TrainingProgram trainingProgram){
        return ResponseEntity.ok(trainingProgramService.saveTrainingProgram(trainingProgram));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingProgram>findTrainingProgramById(@PathVariable Long id){
        TrainingProgram trainingProgram = trainingProgramService.getTrainingProgramById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return ResponseEntity.ok(trainingProgram);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingProgram> updateTrainingProgram(@PathVariable Long id, @RequestBody TrainingProgram trainingProgramDetails) {

        return ResponseEntity.ok(trainingProgramService.updateTrainingProgram(id, trainingProgramDetails));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingProgram(@PathVariable Long id) {
        TrainingProgram trainingProgram = trainingProgramService.getTrainingProgramById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

       trainingProgramService.deleteTrainingProgram(trainingProgram.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{trainingProgramId}/courses")
    public ResponseEntity<List<Course>>getAllCoursesInTrainingProgram(@PathVariable Long trainingProgramId){
        return  ResponseEntity.ok(trainingProgramService.getCoursesByTrainingProgram(trainingProgramId));
    }

    @PostMapping("/{trainingProgramId}/employees/{employeeId}")
    public ResponseEntity<TrainingProgram> addEmployeeToTrainingProgram(@PathVariable Long trainingProgramId, @PathVariable Long employeeId) {
        TrainingProgram updatedProgram = trainingProgramService.addEmployeeToTrainingProgram(trainingProgramId, employeeId);
        return ResponseEntity.ok(updatedProgram);
    }

    @GetMapping("/{trainingProgramId}/employees")
    public ResponseEntity<List<Employee>> getEmployeesByTrainingProgram(@PathVariable Long trainingProgramId) {
        List<Employee> employees = trainingProgramService.getEmployeesByTrainingProgram(trainingProgramId);
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("/{trainingProgramId}/employees/{employeeId}")
    public ResponseEntity<TrainingProgram> removeEmployeeFromTrainingProgram(@PathVariable Long trainingProgramId, @PathVariable Long employeeId) {
        TrainingProgram updatedProgram = trainingProgramService.removeEmployeeFromTrainingProgram(trainingProgramId, employeeId);
        return ResponseEntity.ok(updatedProgram);
    }

    @PostMapping("/{trainingProgramId}/courses/{courseId}")
    public ResponseEntity<TrainingProgram> addCourseToTrainingProgram(@PathVariable Long trainingProgramId, @PathVariable Long courseId) {
        TrainingProgram updatedProgram = trainingProgramService.addCourseToTrainingProgram(trainingProgramId, courseId);
        return ResponseEntity.ok(updatedProgram);
    }

    @DeleteMapping("/{trainingProgramId}/courses/{courseId}")
    public ResponseEntity<TrainingProgram> removeCourseFromTrainingProgram(@PathVariable Long trainingProgramId, @PathVariable Long courseId) {
        TrainingProgram updatedProgram = trainingProgramService.removeCourseFromTrainingProgram(trainingProgramId, courseId);
        return ResponseEntity.ok(updatedProgram);
    }




}
