package com.api.gateway.service.user;

import com.api.gateway.dto.post.likedislike.LikeDislikeDto;
import com.api.gateway.dto.user.request.EditUserRequest;
import com.api.gateway.dto.user.request.UserLoginRequest;
import com.api.gateway.dto.user.request.UserSignupRequest;
import com.api.gateway.dto.user.response.PostedBy;
import com.api.gateway.dto.user.response.UserLoginResponse;
import com.api.gateway.dto.user.response.UserProfile;
import com.api.gateway.dto.user.response.follow.Follow;
import com.api.gateway.feign.comment.CommentClient;
import com.api.gateway.feign.post.PostClient;
import com.api.gateway.feign.user.UserClient;
import com.api.gateway.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUser{

    private final UserClient userClient;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final PostClient postClient;

    private final CommentClient commentClient;

    @Override
    public UserProfile getUserProfileById(Long id) {


        //fetch user info from user microservice
        var userProfile = userClient.getUserProfileById(id);

        //fetch post by user and like dislike of posts
        var fetchPostOfUser = postClient.getAllUsersPost(id);
        var fetchUsersLikeDislikeOnPost = postClient.getAllPostLikeDislikeFromUser(id);

        //fetch comments and like dislike of those comments
        var fetchUserCommentLikeDislike = commentClient.getAllCommentsWhereUserLikedOrDisliked(id);
        var fetchAllUserComments = commentClient.getAllUserComments(id);

        userProfile.setPosts(fetchPostOfUser);
        userProfile.setPostLikeOrDislikeDtos(fetchUsersLikeDislikeOnPost);
        userProfile.setLikedOrDislikedComments(fetchUserCommentLikeDislike);
        userProfile.setCommentsPosts(fetchAllUserComments);


        return userProfile;
    }

    @Override
    public UserProfile getUserProfileByJwt(String jwt) {
        var temp = jwtTokenUtil.getUserIdFromJwt(jwt);
        return getUserProfileById(temp);
    }

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        var user = userClient.loginUser(request);
        UserLoginResponse response = new UserLoginResponse();

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
        var jwt = jwtTokenUtil.generateJwtToken(auth);
        response.setJwt(jwt);

        return response;
    }

    @Override
    public void createAccount(UserSignupRequest request) {
        userClient.createUser(request);
    }

    @Override
    public PostedBy editUser(EditUserRequest request) {
        return userClient.updateUser(request);
    }

    @Override
    public void deleteUserById(Long id) {
        userClient.deleteUserById(id);
    }

    @Override
    public Set<Follow> followUnfollowUserById(Long followingId, HttpServletRequest request) {
        return userClient.followUnfollowUser(followingId, request.getHeader("Authorization"));
    }

    @Override
    public boolean validateJwt(String jwt) {
        return jwtTokenUtil.validateToken(jwt);
    }
}
