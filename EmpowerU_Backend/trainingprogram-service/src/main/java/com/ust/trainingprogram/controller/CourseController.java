package com.ust.trainingprogram.controller;


import com.ust.trainingprogram.model.Course;
import com.ust.trainingprogram.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainingProgram/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/{trainingProgramId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Course createCourse(@RequestBody Course course, @PathVariable Long trainingProgramId) {
        return courseService.saveCourse(course, trainingProgramId);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Course> getAllCourses() {
        return courseService.findAllCourses();

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Course getCourseById(@PathVariable Long id) {
        return courseService.findCourseById(id).get();

    }

    @PutMapping("/{trainingProgramId}/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Course updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse, @RequestParam Long trainingprogramId) {
        return courseService.updateCourse(id, updatedCourse, trainingprogramId);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }

//    @GetMapping("/search")
//    @ResponseStatus(HttpStatus.OK)
//    public Course getCourseByName(@RequestParam String name){
//        return courseService.fetchCourseByCourseName(name);
//
//    }
}
