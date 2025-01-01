package com.ust.empoweru.service;


import com.ust.empoweru.model.Employee;
import com.ust.empoweru.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApiUserService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee dbUser = employeeRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        return User.builder()
                .username(dbUser.getEmployeeName())
                .password(dbUser.getPassword())
                .build();
    }


    public void saveUser(Employee appUser) {
        if (employeeRepository.findByEmail(appUser.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists: " + appUser.getEmail());
        }
        employeeRepository.save(appUser);
    }

    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
