package com.pandatype.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pandatype.auth.entities.AuthResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class ErrorAuthenticationEntryPoint implements AuthenticationEntryPoint {
    Logger LOGGER = LoggerFactory.getLogger(ErrorAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        AuthResponseEntity authResponseEntity = new AuthResponseEntity("error", authException.getMessage());
        writer.write(mapper.writeValueAsString(authResponseEntity));
        LOGGER.error("Unauthorized error: {}", authException.getMessage());
    }
}