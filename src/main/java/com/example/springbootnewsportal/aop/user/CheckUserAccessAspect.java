package com.example.springbootnewsportal.aop.user;

import com.example.springbootnewsportal.exceptions.UserAccessException;
import com.example.springbootnewsportal.security.AppUserPrincipal;
import com.example.springbootnewsportal.security.AuthenticationService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class CheckUserAccessAspect {

    @Autowired
    private AuthenticationService authenticationService;

    @Before("@annotation(CheckUserAccess) && args(id,..)")
    public void checkAccess(Long id) {
        AppUserPrincipal appUserPrincipal = authenticationService.getCurrentUser();
        List<String> roles = appUserPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (roles.stream().noneMatch(role -> role.equals("ROLE_ADMIN") ||
                role.equals("ROLE_MODERATOR") ||
                (role.equals("ROLE_USER") && appUserPrincipal.getId().equals(id)))) {
            throw new UserAccessException("Impossible to update/delete/get user because id belongs other user or role is not admin/moderator");
        }
    }
}