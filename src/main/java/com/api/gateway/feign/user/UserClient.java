package com.api.gateway.feign.user;

import com.api.gateway.dto.user.request.EditUserRequest;
import com.api.gateway.dto.user.request.UserLoginRequest;
import com.api.gateway.dto.user.request.UserSignupRequest;
import com.api.gateway.dto.user.response.PostedBy;
import com.api.gateway.dto.user.response.UserSecurityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "userClient", url = "http://localhost:8086/api/v1/user")
public interface UserClient {

    @GetMapping("/security/find-by-username")
    UserSecurityDto findUserByUsername(@RequestParam String username);

    @PostMapping("/login")
    UserSecurityDto loginUser(@RequestBody UserLoginRequest request);

    @PostMapping("/signup")
    void createUser(@RequestBody UserSignupRequest request);

    @PutMapping
    PostedBy updateUser(@RequestBody EditUserRequest request);

    @DeleteMapping("/{id}")
    void deleteUserById(@PathVariable Long id);


}
