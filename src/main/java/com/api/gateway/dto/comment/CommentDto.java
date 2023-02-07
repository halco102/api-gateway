package com.api.gateway.dto.comment;

import com.api.gateway.dto.user.response.UserDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class CommentDto extends BaseComment{

    private Set<String> parentIds = new LinkedHashSet<>();
    private List<CommentDto> replies = new ArrayList<>();

    public CommentDto(String id, CommentPostDto postDto, UserDto userDto, String comment,
                      List<LikeDislikeComment> likeDislikeComments, LocalDateTime createdAt,
                      List<CommentDto> replies,
                      Set<String> parentIds, String mention) {
        super(id, postDto, userDto, comment, likeDislikeComments, createdAt, mention);
        this.replies = replies;
        this.parentIds = parentIds;
    }

}
