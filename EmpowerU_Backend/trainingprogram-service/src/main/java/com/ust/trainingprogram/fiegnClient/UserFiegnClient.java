package com.ust.trainingprogram.fiegnClient;

import com.ust.trainingprogram.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-SERVICE")
public interface UserFiegnClient {

    @GetMapping("/api/user/{id}")
    public User getUserById(@PathVariable Long id);
}
