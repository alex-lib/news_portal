package com.example.springbootnewsportal.aop.comment;

import com.example.springbootnewsportal.exceptions.CommentAccessException;
import com.example.springbootnewsportal.security.AppUserPrincipal;
import com.example.springbootnewsportal.security.AuthenticationService;
import com.example.springbootnewsportal.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckCommentAccessAspect {

    private final CommentService commentService;

    private final AuthenticationService authenticationService;

    @Before("@annotation(CheckDeleteCommentAccess) && args(id, ..)")
    public void checkDeleteAccess(Long id) {
        AppUserPrincipal appUserPrincipal = authenticationService.getCurrentUser();
        List<String> roles = appUserPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (roles.stream().noneMatch(role -> role.equals("ROLE_ADMIN") ||
                role.equals("ROLE_MODERATOR") ||
                (role.equals("ROLE_USER") && appUserPrincipal.getId().equals(commentService.findById(id).getUserCreatorComment().getId())))) {
            throw new CommentAccessException("Impossible to delete comment because this comment was created by other user or role is not admin/moderator");
        }
    }

    @Before("@annotation(CheckUpdateCommentAccess) && args(id, ..)")
    public void checkUpdateAccess(Long id) {
        AppUserPrincipal appUserPrincipal = authenticationService.getCurrentUser();
        List<String> roles = appUserPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (roles.stream().noneMatch(role -> role.equals("ROLE_USER") &&
                appUserPrincipal.getId().equals(commentService.findById(id).getUserCreatorComment().getId()))) {
            throw new CommentAccessException("Impossible to update comment because this comment was created by other user");
        }
    }
}