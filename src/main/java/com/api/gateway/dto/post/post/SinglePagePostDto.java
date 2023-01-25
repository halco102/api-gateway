package com.api.gateway.dto.post.post;

import com.api.gateway.dto.comment.CommentDto;
import com.api.gateway.dto.post.category.CategoryDto;
import com.api.gateway.dto.post.likedislike.LikeDislikeDto;
import com.api.gateway.dto.user.response.PostedBy;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class SinglePagePostDto extends BasePost {

    @JsonProperty("commentsDtos")
    private List<CommentDto> commentDtos;

    public SinglePagePostDto(Long id, String title, String description, String imageUrl, boolean allowComment, PostedBy postedBy, Set<CategoryDto> categoryDtos, Set<LikeDislikeDto> postLikedDislike, List<CommentDto> commentDtos) {
        super(id, title, description, imageUrl, allowComment, postedBy, categoryDtos, postLikedDislike);
        this.commentDtos = commentDtos;
    }

}
