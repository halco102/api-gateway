package com.api.gateway.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSecurityDto {


    private Long id;

    private String username;

    private String password;

    private String email;

    private String imageUrl;

    private Set<RoleDto> userRoles;


}
