package com.yifan.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.yifan.entrypoint.SimpleAuthenticationEntryPoint;

public class CustomerAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private AuthenticationEntryPoint authenticationEntryPoint = new SimpleAuthenticationEntryPoint();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        authenticationEntryPoint.commence(request, response, exception);
    }
}