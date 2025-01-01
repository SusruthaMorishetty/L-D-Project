package com.ust.empoweru.repository;

import com.ust.empoweru.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

//    @Query("From Employee where email=:email ")
//    Optional<Employee> findByEmail(String email);
//
//    @Query("From Employee where role=:role")
//    List<Employee> findByRole(String role);
//
//    @Query("From Employee where department=:department")
//    List<Employee> findByDepartment(String department);

    @Query("From Employee where employeeName=:userName")
    Optional<Employee> findByUserName(String userName);

    @Query("From Employee where email=:email")
    Optional<Employee> findByEmail(String email);
}
