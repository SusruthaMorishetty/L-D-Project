package com.ust.feedback.repository;

import com.ust.feedback.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "USER-SERVICE")
public interface UserFeign {

    @GetMapping("/api/user/{id}")
    User getUserById(@PathVariable long id);

}
