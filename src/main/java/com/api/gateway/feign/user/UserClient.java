package com.api.gateway.feign.user;

import com.api.gateway.dto.user.request.EditUserRequest;
import com.api.gateway.dto.user.request.UserLoginRequest;
import com.api.gateway.dto.user.request.UserSignupRequest;
import com.api.gateway.dto.user.response.PostedBy;
import com.api.gateway.dto.user.response.UserProfile;
import com.api.gateway.dto.user.response.UserSecurityDto;
import com.api.gateway.dto.user.response.follow.Follow;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
    Set<Follow> followUnfollowUser(@PathVariable Long followingId, @RequestHeader("Authorization") String jwt);

    @GetMapping("/{id}/followers")
    List<Follow> getUserFollowersByUserId(@PathVariable Long id);

    @GetMapping("/{id}/following")
    List<Follow> getUserFollowingByUserId(@PathVariable Long id);

    @GetMapping("/{id}")
    UserProfile getUserDtoById(@PathVariable Long id);

    @GetMapping("/profile/{id}")
    UserProfile getUserProfileById(@PathVariable Long id);

}
