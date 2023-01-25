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
public class PostDto extends BasePost {

    private Long numberOfComments;

    public PostDto(Long id, String title, String description, String imageUrl, boolean allowComment, PostedBy postedBy, Set<CategoryDto> categoryDtos, Set<LikeDislikeDto> postLikedDislike, Long numberOfComments) {
        super(id, title, description, imageUrl, allowComment, postedBy, categoryDtos, postLikedDislike);
        this.numberOfComments = numberOfComments;
    }

}
