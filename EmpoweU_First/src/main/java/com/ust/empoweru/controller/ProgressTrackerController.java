package com.ust.empoweru.controller;

import com.ust.empoweru.model.ProgressTracker;
import com.ust.empoweru.service.ProgressTrackerService;
import com.ust.empoweru.service.ProgressTrackerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/progress")
public class ProgressTrackerController {

    @Autowired
    private ProgressTrackerService progressTrackerService;


    @PostMapping
    public ResponseEntity<ProgressTracker> createProgressTracker(
            @RequestBody ProgressTracker progressTracker,
            @RequestParam Long employeeId,
            @RequestParam Long courseId) {
        ProgressTracker savedProgressTracker = progressTrackerService.saveProgressTracker(progressTracker, employeeId, courseId);
        return new ResponseEntity<>(savedProgressTracker, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProgressTracker> getProgressTrackerById(@PathVariable Long id) {
        ProgressTracker progressTracker = progressTrackerService.getProgressTrackerById(id);
        return new ResponseEntity<>(progressTracker, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<ProgressTracker>> getAllProgressTrackers() {
        List<ProgressTracker> progressTrackers = progressTrackerService.getAllProgressTrackers();
        return new ResponseEntity<>(progressTrackers, HttpStatus.OK);
    }


    @PutMapping("/{progressTrackerId}")
    public ResponseEntity<ProgressTracker> updateProgressTracker(
            @PathVariable Long progressTrackerId,
            @RequestBody ProgressTracker updatedProgressTracker,
            @RequestParam Long employeeId,
            @RequestParam Long courseId) {

        ProgressTracker updatedProgress = progressTrackerService.updateProgressTracker(progressTrackerId, updatedProgressTracker, employeeId, courseId);
        return ResponseEntity.ok(updatedProgress);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgressTracker(@PathVariable Long id) {
        progressTrackerService.deleteProgressTracker(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<ProgressTracker>> getProgressTrackersByEmployeeId(@PathVariable Long employeeId) {
        List<ProgressTracker> progressTrackers = progressTrackerService.findByEmployeeId(employeeId);
        return new ResponseEntity<>(progressTrackers, HttpStatus.OK);
    }


    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ProgressTracker>> getProgressTrackersByCourseId(@PathVariable Long courseId) {
        List<ProgressTracker> progressTrackers = progressTrackerService.findByCourseId(courseId);
        return new ResponseEntity<>(progressTrackers, HttpStatus.OK);
    }
}
