package com.ust.progress.repository;


import com.ust.progress.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserFeign {

    @GetMapping("/api/user/{id}")
    public User getUserById(@PathVariable long id);
}
