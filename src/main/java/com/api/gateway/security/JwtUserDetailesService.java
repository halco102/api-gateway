package com.api.gateway.security;

import com.api.gateway.feign.user.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JwtUserDetailesService implements UserDetailsService {

    private final UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var getUserByUsername = userClient.findUserByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        if (getUserByUsername != null) {
            getUserByUsername.getUserRoles().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())));

            return CustomUserDetailsImp.build(getUserByUsername);
            //return new CustomUserDetailsImp(getUserByUsername.get().getUsername(), getUserByUsername.get().getEmail(), getUserByUsername.get().getPassword(), grantedAuthorities);
        }else {
            throw new UsernameNotFoundException("User with username " + username + " was not found!");
        }

    }
}
