package com.api.gateway.dto.post.likedislike;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeDislikeDto {

    private Long postId;

    private Long userId;

    private boolean likeOrDislike;

}
