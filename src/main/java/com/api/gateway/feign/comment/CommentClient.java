package com.api.gateway.feign.comment;

import com.api.gateway.dto.comment.CommentDto;
import com.api.gateway.dto.comment.request.CommentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "commentClient", url = "${COMMENT_CLIENT_URL}")
public interface CommentClient {

    @GetMapping("/latest/{postId}")
    List<CommentDto> latestCommentsFromPost(@PathVariable("postId") Long id);

    @GetMapping("/post/{postId}/count")
    Long numberOfComments(@PathVariable("postId") Long postId);

    @PostMapping
    CommentDto saveComment(@RequestBody CommentRequest request, @RequestHeader("Authorization") String jwt);

}
