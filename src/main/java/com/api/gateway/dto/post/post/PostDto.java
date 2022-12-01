package com.api.gateway.dto.post.post;

import com.api.gateway.dto.post.category.CategoryDto;
import com.api.gateway.dto.user.response.PostedBy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Long id;

    private String title;

    private String description;

    private String imageUrl;

/*    private LocalDateTime createdAt;

    private LocalDateTime editedAt;*/

    private boolean allowComment;

    private PostedBy postedBy;

    private Set<CategoryDto> categoryDtos;

    public PostDto(Long id, String title, String description, String imageUrl, PostedBy postedBy) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.postedBy = postedBy;
    }
}
