package com.ust.empoweru.service;

import com.ust.empoweru.exception.IdAlreadyExistsException;
import com.ust.empoweru.exception.NotFoundException;
import com.ust.empoweru.exception.NullException;
import com.ust.empoweru.model.Employee;

import com.ust.empoweru.model.TrainingProgram;
import com.ust.empoweru.repository.EmployeeRepository;

import com.ust.empoweru.repository.TrainingProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) {
            return employeeRepository.findById(employeeId)
                    .orElseThrow(()->new NotFoundException("employee with "+employeeId+" not found"));
    }

    public Employee saveEmployee(Employee employee) {
        if(employee==null){
            throw new NullException("employee cannot be null");
        }
        if(employeeRepository.existsById(employee.getEmployeeId())){
            throw new IdAlreadyExistsException("Id with "+employee.getEmployeeId()+" already exists");
        }
        return employeeRepository.save(employee);

    }

    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee with Id"+employeeId+" not found"));

        for (TrainingProgram program : employee.getTrainingPrograms()) {
            program.getEmployees().remove(employee);
        }

        trainingProgramRepository.saveAll(employee.getTrainingPrograms());
        employeeRepository.delete(employee);
    }

    public Employee updateEmployee(Long employeeId, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee with Id "+employeeId+" not found"));


        existingEmployee.setEmployeeName(updatedEmployee.getEmployeeName());
        existingEmployee.setEmail(updatedEmployee.getEmail());
        existingEmployee.setRole(updatedEmployee.getRole());
        return employeeRepository.save(existingEmployee);
    }


    public Employee addTrainingProgramToEmployee(Long employeeId, Long trainingProgramId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee with Id "+employeeId+" not found"));

        TrainingProgram trainingProgram = trainingProgramRepository.findById(trainingProgramId)
                .orElseThrow(() -> new NotFoundException("Training program with Id "+trainingProgramId+" not found"));

        employee.getTrainingPrograms().add(trainingProgram);
        trainingProgram.getEmployees().add(employee);

        return employeeRepository.save(employee);
    }

    public List<TrainingProgram> getTrainingProgramsForEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee with Id "+employeeId+" not found"));

        return List.copyOf(employee.getTrainingPrograms());
    }


    public Employee removeTrainingProgramFromEmployee(Long employeeId, Long trainingProgramId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee with Id "+employeeId+" not found"));

        TrainingProgram trainingProgram = trainingProgramRepository.findById(trainingProgramId)
                .orElseThrow(() -> new NotFoundException("Training program with Id "+trainingProgramId+" not found"));

        employee.getTrainingPrograms().remove(trainingProgram);
        trainingProgram.getEmployees().remove(employee);

        return employeeRepository.save(employee);
    }

}
