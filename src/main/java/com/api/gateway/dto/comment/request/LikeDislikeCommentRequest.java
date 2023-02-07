package com.api.gateway.dto.comment.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeDislikeCommentRequest {
    private String id;

    private boolean isLike;

}
