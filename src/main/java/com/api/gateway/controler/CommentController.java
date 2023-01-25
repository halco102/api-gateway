package com.api.gateway.controler;

import com.api.gateway.dto.comment.request.CommentRequest;
import com.api.gateway.feign.comment.CommentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentClient commentClient;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public ResponseEntity<?> saveComment(@RequestBody CommentRequest commentRequest, HttpServletRequest request) {
        return new ResponseEntity<>(commentClient.saveComment(commentRequest, request.getHeader("Authorization")), HttpStatus.OK);
    }

}
