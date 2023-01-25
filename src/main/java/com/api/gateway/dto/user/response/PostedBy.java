package com.api.gateway.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostedBy extends UserDto {

    private String email;

    public PostedBy(Long id, String username, String imageUrl, String email) {
        super(id, username, imageUrl);
        this.email = email;
    }


}
