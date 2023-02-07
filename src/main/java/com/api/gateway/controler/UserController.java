package com.api.gateway.controler;

import com.api.gateway.dto.user.request.EditUserRequest;
import com.api.gateway.dto.user.request.UserLoginRequest;
import com.api.gateway.dto.user.request.UserSignupRequest;
import com.api.gateway.security.AuthTokenFilter;
import com.api.gateway.service.user.IUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final IUser iUser;



    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest request) {
        return new ResponseEntity<>(iUser.login(request), HttpStatus.OK);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> createAccount(@RequestBody UserSignupRequest request) {
        iUser.createAccount(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> editUser(@RequestBody EditUserRequest request) {
        return new ResponseEntity<>(iUser.editUser(request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        iUser.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/follow/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<?> followUnfollowUserById(@PathVariable Long id, HttpServletRequest req) {
        return new ResponseEntity<>(iUser.followUnfollowUserById(id, req), HttpStatus.OK);
    }


    @GetMapping("/profile/{id}")
    public ResponseEntity<?> getUserProfileById(@PathVariable Long id) {
        return new ResponseEntity<>(iUser.getUserProfileById(id), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentlyLoggedUserProfile(HttpServletRequest request) {
        return new ResponseEntity<>(iUser.getUserProfileByJwt(AuthTokenFilter.parseJwt(request)) , HttpStatus.OK);
    }

    @GetMapping("/jwt/valid")
    public ResponseEntity<?> validateJWT(@RequestParam String jwt) {
        return new ResponseEntity<>(iUser.validateJwt(jwt), HttpStatus.OK);
    }

}
