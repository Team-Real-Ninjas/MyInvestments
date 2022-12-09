package edu.famu.myinvestments.auth.services;

import edu.famu.myinvestments.auth.models.Credentials;
import edu.famu.myinvestments.auth.models.FirebaseUser;
import edu.famu.myinvestments.auth.models.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class SecurityService {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    CookieUtils cookieUtils;

    @Autowired
    SecurityProperties securityProps;

    public FirebaseUser getUser() {
        FirebaseUser userPrincipal = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication().getPrincipal();
        if (principal instanceof FirebaseUser) {
            userPrincipal = ((FirebaseUser) principal);
        }
        return userPrincipal;
    }

    public Credentials getCredentials() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (Credentials) securityContext.getAuthentication().getCredentials();
    }

    public boolean isPublic() {
        return securityProps.getAllowedPublicApis().contains(httpServletRequest.getRequestURI());
    }

    public String getBearerToken(HttpServletRequest request) {
        String bearerToken = null;
        String authorization = request.getHeader("Authorization");
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            bearerToken = authorization.substring(7);
        }
        return bearerToken;
    }
}