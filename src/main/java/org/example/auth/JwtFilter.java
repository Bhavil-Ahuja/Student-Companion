package org.example.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtFilter extends OncePerRequestFilter {
  @Autowired private JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session != null) {
      String token = (String) session.getAttribute("JWT_TOKEN");
      if (token != null) {
        try {
          String username = jwtUtil.extractUsername(token);
          UsernamePasswordAuthenticationToken auth =
              new UsernamePasswordAuthenticationToken(
                  username, null, Collections.singletonList(new SimpleGrantedAuthority("USER")));
          SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
          // log error or ignore
        }
      }
    }
    filterChain.doFilter(request, response);
  }
}
