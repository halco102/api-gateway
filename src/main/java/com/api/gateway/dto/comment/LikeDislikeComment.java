package com.api.gateway.dto.comment;

import com.api.gateway.dto.user.response.UserDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeDislikeComment {

    private String commentId;

    private UserDto userDto;

    @JsonProperty("likeOrDislike")
    private boolean isLike;

}
