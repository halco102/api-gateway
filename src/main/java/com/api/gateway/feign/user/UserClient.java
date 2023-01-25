package com.api.gateway.feign.user;

import com.api.gateway.dto.user.request.EditUserRequest;
import com.api.gateway.dto.user.request.UserLoginRequest;
import com.api.gateway.dto.user.request.UserSignupRequest;
import com.api.gateway.dto.user.response.PostedBy;
import com.api.gateway.dto.user.response.UserSecurityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@FeignClient(name = "userClient", url = "${USER_MICROSERVICE}")
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

    @GetMapping("/{id}/following")
    Set<PostedBy> getFollowingFromUserById(@PathVariable Long userId);

    @GetMapping("/{id}/followers")
    Set<PostedBy> getFollowersFromUserById(@PathVariable Long userId);

    @PostMapping("/follow-user/{id}")
    Set<PostedBy> followUnfollowUser(@PathVariable Long followingId, @RequestHeader("Authorization") String jwt);
}
