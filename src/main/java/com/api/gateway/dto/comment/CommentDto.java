package com.api.gateway.dto.comment;

import com.api.gateway.dto.post.post.PostDto;
import com.api.gateway.dto.user.response.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CommentDto extends BaseComment{
    private List<ReplyDto> replies = new ArrayList<>();

    public CommentDto(String id, CommentPostDto postDto, UserDto userDto, String comment, List<LikeDislikeComment> likeDislikeComments, LocalDateTime createdAt, List<ReplyDto> replies) {
        super(id, postDto, userDto, comment, likeDislikeComments, createdAt);
        this.replies = replies;
    }

}
