package com.api.gateway.dto.comment;

import com.api.gateway.dto.post.post.PostDto;
import com.api.gateway.dto.user.response.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ReplyDto extends BaseComment{
    private String parentId;

    public ReplyDto(String id, CommentPostDto postDto, UserDto userDto, String comment, List<LikeDislikeComment> likeDislikeComments, LocalDateTime createdAt, String parentId, String mention) {
        super(id, postDto, userDto, comment, likeDislikeComments, createdAt, mention);
        this.parentId = parentId;
    }

}
