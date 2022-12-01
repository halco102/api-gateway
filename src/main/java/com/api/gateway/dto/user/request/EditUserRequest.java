package com.api.gateway.dto.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUserRequest {

    @NotNull
    private Long id;

    private String username;

    private String email;

    private String imageUrl;

}
