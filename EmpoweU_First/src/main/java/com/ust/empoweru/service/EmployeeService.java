package com.ust.empoweru.service;

import com.ust.empoweru.model.Employee;
import com.ust.empoweru.model.TrainingProgram;


import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Long employeeId, Employee employee);

    void deleteEmployee(Long employeeId);

    Employee getEmployeeById(Long employeeId);

    List<Employee> getAllEmployees();

    Employee addTrainingProgramToEmployee(Long employeeId, Long trainingProgramId);

    List<TrainingProgram> getTrainingProgramsForEmployee(Long employeeId);

    Employee removeTrainingProgramFromEmployee(Long employeeId, Long trainingProgramId);







}
