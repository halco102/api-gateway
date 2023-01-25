package com.api.gateway.dto.post.post;

import com.api.gateway.dto.post.category.CategoryDto;
import com.api.gateway.dto.post.likedislike.LikeDislikeDto;
import com.api.gateway.dto.user.response.PostedBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BasePost {

    private Long id;

    private String title;

    private String description;

    private String imageUrl;

    private boolean allowComment;

    private PostedBy postedBy;

    private Set<CategoryDto> categoryDtos;

    private Set<LikeDislikeDto> postLikedDislike;

}
