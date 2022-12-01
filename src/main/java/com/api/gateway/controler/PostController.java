package com.api.gateway.controler;

import com.api.gateway.dto.post.post.request.PostRequestDto;
import com.api.gateway.feign.post.PostClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostClient postClient;

    @PostMapping
    public ResponseEntity<?> savePost(@RequestBody PostRequestDto requestDto) {
        return new ResponseEntity<>(postClient.savePost(requestDto), HttpStatus.OK);
    }


}
