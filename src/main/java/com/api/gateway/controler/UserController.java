package com.api.gateway.controler;

import com.api.gateway.dto.user.request.EditUserRequest;
import com.api.gateway.dto.user.request.UserLoginRequest;
import com.api.gateway.dto.user.request.UserSignupRequest;
import com.api.gateway.dto.user.response.UserLoginResponse;
import com.api.gateway.feign.user.UserClient;
import com.api.gateway.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserClient userClient;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;



    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest request) {
        var user = userClient.loginUser(request);
        UserLoginResponse response = new UserLoginResponse();

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        var jwt = jwtTokenUtil.generateJwtToken(auth);
        response.setJwt(jwt);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody UserSignupRequest request) {
        userClient.createUser(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> editUser(@RequestBody EditUserRequest request) {
        return new ResponseEntity<>(userClient.updateUser(request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userClient.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/follow/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<?> followUnfollowUserById(@PathVariable Long id, HttpServletRequest req) {
        return new ResponseEntity<>(userClient.followUnfollowUser(id, req.getHeader("Authorization")), HttpStatus.OK);
    }

}
