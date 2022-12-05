package com.api.gateway.feign.post;

import com.api.gateway.dto.post.post.PostDto;
import com.api.gateway.dto.post.post.request.EditPostRequest;
import com.api.gateway.dto.post.post.request.PostRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "postClient", url = "${POST_MICROSERVICE}")
public interface PostClient {

    @PostMapping(value = "", consumes = {  MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    PostDto savePost(@RequestBody PostRequestDto requestDto, @RequestHeader("Bearer") String token);


    @GetMapping
    List<PostDto> getAllPosts();

    @GetMapping("/{id}")
    PostDto getPostById(@PathVariable Long id);

    @GetMapping("/user/{id}")
    List<PostDto> getAllUsersPost(@PathVariable Long id);

    @PutMapping("/{id}")
    PostDto editPostById(@PathVariable Long id, @RequestBody EditPostRequest request, @RequestHeader("Bearer") String jwt);

}
