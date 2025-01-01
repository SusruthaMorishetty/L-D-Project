package com.ust.empoweru.controller;


import com.ust.empoweru.dto.RegisterRequest;
import com.ust.empoweru.model.Employee;

import com.ust.empoweru.model.TrainingProgram;
import com.ust.empoweru.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<Employee> createUser(@RequestBody Employee user) {

        Employee createdUser = employeeService.saveEmployee(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Employee>> getAllUsers() {
        List<Employee> users = employeeService.getAllEmployees();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Employee> getUserById(@PathVariable Long userId) {
        Employee user = employeeService.getEmployeeById(userId);
        return  new ResponseEntity<>(user, HttpStatus.OK);

    }


    @PutMapping("/{userId}")
    public ResponseEntity<Employee> updateUser(@PathVariable Long userId, @RequestBody Employee updatedUser) {
            Employee user = employeeService.updateEmployee(userId, updatedUser);
            return new ResponseEntity<>(user, HttpStatus.OK);

    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
       employeeService.deleteEmployee(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{employeeId}/training-programs/{trainingProgramId}")
    public ResponseEntity<Employee> addTrainingProgramToEmployee(@PathVariable Long employeeId, @PathVariable Long trainingProgramId) {
        Employee updatedEmployee = employeeService.addTrainingProgramToEmployee(employeeId, trainingProgramId);
        return ResponseEntity.ok(updatedEmployee);
    }

    @GetMapping("/{employeeId}/training-programs")
    public ResponseEntity<List<TrainingProgram>> getTrainingProgramsForEmployee(@PathVariable Long employeeId) {
        List<TrainingProgram> trainingPrograms = employeeService.getTrainingProgramsForEmployee(employeeId);
        return ResponseEntity.ok(trainingPrograms);
    }

    @DeleteMapping("/{employeeId}/training-programs/{trainingProgramId}")
    public ResponseEntity<Employee> removeTrainingProgramFromEmployee(@PathVariable Long employeeId, @PathVariable Long trainingProgramId) {
        Employee updatedEmployee = employeeService.removeTrainingProgramFromEmployee(employeeId, trainingProgramId);
        return ResponseEntity.ok(updatedEmployee);
    }



}
