package com.api.gateway.security;

import com.api.gateway.dto.user.response.UserSecurityDto;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@EqualsAndHashCode
public class CustomUserDetailsImp implements UserDetails {
    private Long id;

    private String username;

    private String email;

    private String password;

    private String imageUrl;


    private Set<GrantedAuthority> roles;

    public CustomUserDetailsImp() {
    }

    public CustomUserDetailsImp(String username, String email, String password, Set<GrantedAuthority> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public CustomUserDetailsImp(Long id, String username, String email, String password, String imageUrl, Set<GrantedAuthority> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.imageUrl = imageUrl;
        this.roles = roles;
    }

    public static CustomUserDetailsImp build(UserSecurityDto userSecurityDto) {
        Set<GrantedAuthority> r = new HashSet<>();
        r.add(new SimpleGrantedAuthority(userSecurityDto.getUserRole().name()));
        return new CustomUserDetailsImp(
            userSecurityDto.getId(), userSecurityDto.getUsername(), userSecurityDto.getEmail(), userSecurityDto.getPassword(), userSecurityDto.getImageUrl(), r
        );
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
