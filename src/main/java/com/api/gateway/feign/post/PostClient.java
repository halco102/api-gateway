package com.api.gateway.feign.post;

import com.api.gateway.dto.post.post.PostDto;
import com.api.gateway.dto.post.post.request.PostRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "postClient", url = "http://localhost:8086/api/v1/post")
public interface PostClient {

    @PostMapping(value = "", consumes = {  MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    PostDto savePost(@RequestBody PostRequestDto requestDto);

}
