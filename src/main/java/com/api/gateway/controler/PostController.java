package com.api.gateway.controler;

import com.api.gateway.dto.post.post.SinglePagePostDto;
import com.api.gateway.dto.post.post.request.EditPostRequest;
import com.api.gateway.exception.NotFoundException;
import com.api.gateway.feign.comment.CommentClient;
import com.api.gateway.feign.post.PostClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/post")
@Slf4j
public class PostController {

    private final PostClient postClient;

    private final CommentClient commentClient;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<?> savePost(@RequestPart("requestDto") String requestDto,
                                      @RequestPart(value = "file", required = false) MultipartFile multipartFile,
                                      HttpServletRequest request) {

        var savePost = postClient.savePost(requestDto, multipartFile, request.getHeader("Authorization"));
        if (savePost.isAllowComment())
            savePost.setNumberOfComments(0L);

        return new ResponseEntity<>(savePost, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllPosts() {
        var fetchposts = postClient.getAllPosts();

        fetchposts.forEach(e -> {
            e.setNumberOfComments(commentClient.numberOfComments(e.getId()));
        });

        return new ResponseEntity<>(fetchposts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        var post = postClient.getPostById(id);
        var comments = commentClient.latestCommentsFromPost(id);

        if (post == null || comments == null)
            throw new NotFoundException("Post or comments are null");

        SinglePagePostDto singlePagePostDto = new SinglePagePostDto(post.getId(), post.getTitle(), post.getDescription(), post.getImageUrl(),
                post.isAllowComment(), post.getPostedBy(), post.getCategoryDtos(), post.getPostLikedDislike(), comments);

        return new ResponseEntity<>(singlePagePostDto, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAllUserPosts(@PathVariable Long id) {
        var fetchposts = postClient.getAllUsersPost(id);

        fetchposts.forEach(e -> {
            e.setNumberOfComments(commentClient.numberOfComments(e.getId()));
        });

        return new ResponseEntity<>(fetchposts, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<?> editPostById(@PathVariable Long id, @RequestBody EditPostRequest request, HttpServletRequest servletRequest) {
        return new ResponseEntity<>(postClient.editPostById(id, request, servletRequest.getHeader("Authorization")), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping("/like-dislike/{id}")
    public ResponseEntity<?> likeDislikePost(@PathVariable(name = "id") Long postId, @RequestParam boolean isLike, HttpServletRequest request) {
        return new ResponseEntity<>(postClient.likeDislikePost(postId, isLike, request.getHeader("Authorization")), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable Long id, HttpServletRequest request) {
        postClient.deletePostById(id, request.getHeader("Authorization"));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<?> getAllPostsByCategoryName(@PathVariable String name) {
        return new ResponseEntity<>(postClient.getAllPostsByCategoryName(name), HttpStatus.OK);
    }

}
