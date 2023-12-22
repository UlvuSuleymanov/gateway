package com.tourist.gateway.security;

import com.tourist.gateway.dto.AuthorizeResponseDto;
import com.tourist.gateway.dto.UserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.Collections;
@Component
public class AuthenticationFilter  extends OncePerRequestFilter {

    private   AuthenticationService authenticationService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException {
        String token = extractToken(request);
        if (token != null) {
            AuthorizeResponseDto authorizeResponseDto = authenticationService.validateAccess(token);
            if (!authorizeResponseDto.getIsAuthorize()) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            } else {
                UsernamePasswordAuthenticationToken authToken = toAuthenticationToken(toPrincipal(request,response));
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request,response);
    }

    private UserPrincipal toPrincipal(HttpServletRequest request, HttpServletResponse response) {
        UserPrincipal principal = new UserPrincipal();
        principal.setToken(response.getHeader("Authorization"));
        return principal;
    }

    private UsernamePasswordAuthenticationToken toAuthenticationToken(UserPrincipal principal) {
        return new UsernamePasswordAuthenticationToken(principal, null, Collections.emptyList());
    }

    private String extractToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
