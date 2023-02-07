package com.api.gateway.dto.comment;

import com.api.gateway.dto.post.post.PostDto;
import com.api.gateway.dto.user.response.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseComment {
    private String id;

    private CommentPostDto postDto;

    private UserDto userDto;

    private String comment;

    private List<LikeDislikeComment> likeDislikeComments;

    private LocalDateTime createdAt;

    private String mention;


}
