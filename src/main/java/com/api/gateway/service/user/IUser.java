package com.api.gateway.service.user;

import com.api.gateway.dto.user.request.EditUserRequest;
import com.api.gateway.dto.user.request.UserLoginRequest;
import com.api.gateway.dto.user.request.UserSignupRequest;
import com.api.gateway.dto.user.response.PostedBy;
import com.api.gateway.dto.user.response.UserLoginResponse;
import com.api.gateway.dto.user.response.UserProfile;
import com.api.gateway.dto.user.response.follow.Follow;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public interface IUser {

    UserProfile getUserProfileById(Long id);

    UserLoginResponse login(UserLoginRequest request);

    void createAccount(UserSignupRequest request);

    PostedBy editUser(EditUserRequest request);

    void deleteUserById(Long id);

    Set<Follow> followUnfollowUserById(Long followingId, HttpServletRequest request);

}
