package com.example.springbootnewsportal.aop.news;

import com.example.springbootnewsportal.exceptions.NewsAccessException;
import com.example.springbootnewsportal.security.AppUserPrincipal;
import com.example.springbootnewsportal.security.AuthenticationService;
import com.example.springbootnewsportal.services.NewsService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckNewsAccessAspect {

    private final AuthenticationService authenticationService;

    private final NewsService newsService;

    @Before("@annotation(CheckDeleteNewsAccess) && args(id, ..)")
    public void checkDeleteAccess(Long id) {
        AppUserPrincipal appUserPrincipal = authenticationService.getCurrentUser();
        List<String> roles = appUserPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (roles.stream().noneMatch(role -> role.equals("ROLE_ADMIN") ||
                role.equals("ROLE_MODERATOR") ||
                (role.equals("ROLE_USER") && appUserPrincipal.getId().equals(newsService.findById(id).getUserCreatorNews().getId())))) {
            throw new NewsAccessException("Impossible to delete news because this news was created by other user or role is not admin/moderator");
        }
    }

    @Before("@annotation(CheckUpdateNewsAccess) && args(id, ..)")
    public void checkUpdateAccess(Long id) {
        AppUserPrincipal appUserPrincipal = authenticationService.getCurrentUser();
        List<String> roles = appUserPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        if (roles.stream().noneMatch(role ->  role.equals("ROLE_USER") &&
                appUserPrincipal.getId().equals(newsService.findById(id).getUserCreatorNews().getId()))) {
            throw new NewsAccessException("Impossible to update news because this news was created by other user");
        }
    }
}