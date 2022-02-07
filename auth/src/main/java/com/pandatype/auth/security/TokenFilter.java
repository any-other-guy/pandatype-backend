package com.pandatype.auth.security;

import com.pandatype.auth.entities.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TokenFilter extends OncePerRequestFilter {
    final Logger LOGGER = LoggerFactory.getLogger(TokenFilter.class);
    @Resource
    TokenUtils tokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        // Get token from Header
        final String authorizationKey = "Authorization";
        String authorizationValue;
        try {
            authorizationValue = request.getHeader(authorizationKey);
        } catch (Exception e) {
            authorizationValue = null;
        }

        // Token 开头部分
        String bearer = "Bearer ";
        if (authorizationValue != null && authorizationValue.startsWith(bearer)) {
            // token
            String token = authorizationValue.substring(bearer.length());

            UserEntity userEntity = tokenUtils.validationToken(token);
            if (userEntity != null) {
                // Grant authentication with basic info from userEntity
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getUserRole()));
                UserDetails userDetails = new User(userEntity.getEmail(), "NO PASSWORD", authorities);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(userDetails.getUsername());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}