package com.api.gateway.dto.user.response.follow;

import com.api.gateway.dto.user.response.PostedBy;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Follow extends PostedBy {


    public Follow(String email) {
        super(email);
    }

    public Follow(Long id, String username, String imageUrl, String email) {
        super(id, username, imageUrl, email);
    }

}
