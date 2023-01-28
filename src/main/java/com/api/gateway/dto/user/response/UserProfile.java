package com.api.gateway.dto.user.response;

import com.api.gateway.dto.comment.CommentDto;
import com.api.gateway.dto.comment.LikeDislikeComment;
import com.api.gateway.dto.post.likedislike.LikeDislikeDto;
import com.api.gateway.dto.post.post.PostDto;
import com.api.gateway.dto.user.response.follow.Follow;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserProfile extends UserDto {

    private String email;

    private LocalDate createdAt;

    private List<PostDto> posts;

    private List<CommentDto> commentsPosts;

    private List<LikeDislikeComment> likedOrDislikedComments;

    private List<LikeDislikeDto> postLikeOrDislikeDtos;

    private List<Follow> following;

    private List<Follow> followers;

    public UserProfile(Long id, String username, String imageUrl, String email, LocalDate createdAt, List<PostDto> posts, List<CommentDto> commentsPosts, List<LikeDislikeComment> likedOrDislikedComments, List<LikeDislikeDto> postLikeOrDislikeDtos, List<Follow> followersDtos, List<Follow> followingDtos) {
        super(id, username, imageUrl);
        this.email = email;
        this.createdAt = createdAt;
        this.posts = posts;
        this.commentsPosts = commentsPosts;
        this.likedOrDislikedComments = likedOrDislikedComments;
        this.postLikeOrDislikeDtos = postLikeOrDislikeDtos;
        this.followers = followersDtos;
        this.following = followingDtos;
    }

    public UserProfile(String email, LocalDate createdAt, List<PostDto> posts, List<CommentDto> commentsPosts, List<LikeDislikeComment> likedOrDislikedComments, List<LikeDislikeDto> postLikeOrDislikeDtos, List<Follow> followersDtos, List<Follow> followingDtos) {
        this.email = email;
        this.createdAt = createdAt;
        this.posts = posts;
        this.commentsPosts = commentsPosts;
        this.likedOrDislikedComments = likedOrDislikedComments;
        this.postLikeOrDislikeDtos = postLikeOrDislikeDtos;
        this.followers = followersDtos;
        this.following = followingDtos;
    }
}
