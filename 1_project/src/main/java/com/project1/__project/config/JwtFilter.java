package com.project1.__project.config;

import com.project1.__project.service.JwtService;
import com.project1.__project.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

         String authHeader = request.getHeader("Authorization");
         String Token = null;
         String userName = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            Token = authHeader.substring(7);
            userName = jwtService.extractUserName(Token);
        }


        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){

             UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(userName);
             if(jwtService.validateToken(Token, userDetails)){
                 UsernamePasswordAuthenticationToken authToken=
                         new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
                 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(authToken);
             }
         }
         filterChain.doFilter(request,response);
    }
}
