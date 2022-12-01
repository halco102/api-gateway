package com.api.gateway.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSecurityDto {


    private Long id;

    private String username;

    private String password;

    private String email;

    private String imageUrl;

    private UserRole userRole;


}
