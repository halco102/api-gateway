package com.api.gateway.dto.comment;

import com.api.gateway.dto.user.response.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeDislikeComment {

    private String commentId;

    private UserDto userDto;

    private boolean isLike;

}
