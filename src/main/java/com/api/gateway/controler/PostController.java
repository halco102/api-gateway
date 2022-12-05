package com.api.gateway.controler;

import com.api.gateway.dto.post.post.request.EditPostRequest;
import com.api.gateway.dto.post.post.request.PostRequestDto;
import com.api.gateway.feign.post.PostClient;
import com.api.gateway.security.AuthTokenFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/post")
@Slf4j
public class PostController {

    private final PostClient postClient;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<?> savePost(@RequestBody PostRequestDto requestDto, HttpServletRequest request) {
        return new ResponseEntity<>(postClient.savePost(requestDto, AuthTokenFilter.parseJwt(request)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        return new ResponseEntity<>(postClient.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        return new ResponseEntity<>(postClient.getPostById(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAllUserPosts(@PathVariable Long id) {
        return new ResponseEntity<>(postClient.getAllUsersPost(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<?> editPostById(@PathVariable Long id, @RequestBody EditPostRequest request, HttpServletRequest servletRequest) {
        return new ResponseEntity<>(postClient.editPostById(id, request, AuthTokenFilter.parseJwt(servletRequest)), HttpStatus.OK);
    }


}
