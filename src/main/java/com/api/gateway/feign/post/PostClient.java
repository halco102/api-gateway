package com.api.gateway.feign.post;

import com.api.gateway.dto.post.likedislike.LikeDislikeDto;
import com.api.gateway.dto.post.post.PostDto;
import com.api.gateway.dto.post.post.request.EditPostRequest;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "postClient", url = "${POST_MICROSERVICE}/post", configuration = {PostClient.MultipartSupportConfig.class})
public interface PostClient {


    @PostMapping(value = "", consumes = {"multipart/form-data"},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    PostDto savePost(@RequestPart("requestDto") String requestDto,
                     @RequestPart(value = "file", required = false) MultipartFile multipartFile,
                     @RequestHeader("Authorization") String jwt);


    @GetMapping
    List<PostDto> getAllPosts();

    @GetMapping("/{id}")
    PostDto getPostById(@PathVariable Long id);

    @GetMapping("/user/{id}")
    List<PostDto> getAllUsersPost(@PathVariable Long id);

    @PutMapping("/{id}")
    PostDto editPostById(@PathVariable Long id, @RequestBody EditPostRequest request, @RequestHeader("Authorization") String jwt);


    @PostMapping("/like-dislike/{id}")
    PostDto likeDislikePost(@PathVariable(name = "id") Long postId, @RequestParam boolean isLike, @RequestHeader("Authorization") String jwt);

    @DeleteMapping("/{id}")
    void deletePostById(@PathVariable Long id, @RequestHeader("Authorization") String jwt);

    @GetMapping("/like-dislike/user/{id}")
    List<LikeDislikeDto> getAllPostLikeDislikeFromUser(@PathVariable("id") Long userId);

    @GetMapping("/category/{name}")
    List<PostDto> getAllPostsByCategoryName(@PathVariable String name);

    @Configuration
    class MultipartSupportConfig {
        private ObjectFactory<HttpMessageConverters> messageConverters;

        public MultipartSupportConfig(ObjectFactory<HttpMessageConverters> messageConverters) {
            this.messageConverters = messageConverters;
        }

        @Bean
        @Primary
        @Scope("prototype")
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }
    }

}
