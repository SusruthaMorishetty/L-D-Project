package com.ust.empoweru.controller;

import com.ust.empoweru.model.Course;
import com.ust.empoweru.model.TrainingProgram;
import com.ust.empoweru.service.CourseService;
import com.ust.empoweru.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/trainingProgram/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/{trainingProgramId}")
    public ResponseEntity<Course> createCourse(@RequestBody Course course, @PathVariable Long trainingprogramId) {
        Course savedCourse = courseService.saveCourse(course, trainingprogramId);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.findAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.findCourseById(id);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PutMapping("/{trainingProgramId}/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse, @PathVariable Long trainingprogramId) {
        Course course = courseService.updateCourse(id, updatedCourse, trainingprogramId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<Course>getCourseByName(@RequestParam String name){
        Course course=courseService.fetchCourseByCourseName(name);
        return new ResponseEntity<>(course,HttpStatus.OK);
    }


}
