package com.almada.clinicaapi.filter;

import com.almada.clinicaapi.entity.User;
import com.almada.clinicaapi.repository.UserRepository;
import com.almada.clinicaapi.service.customException.JWTException;
import com.almada.clinicaapi.service.serviceAction.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
public class AuthenticationJWTFilter extends OncePerRequestFilter {

    private TokenService tokenService;

    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenJWT = recoverToken(request);

        if (tokenJWT != null) {

            try {
                User user = recoverUser(tokenJWT);
                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null,
                        List.of(new SimpleGrantedAuthority(roleValidFormat(user))));

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (NoSuchElementException exception) {
                exception.printStackTrace();
                request.setAttribute("exception", "Exception: JWT é inválido ou está expirado!");
            } catch (JWTException exception) {
                exception.printStackTrace();
                request.setAttribute("exception", exception);
            }

        }

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }

    private User recoverUser(String tokenJWT) {
        String subject = tokenService.getSubject(tokenJWT);
        return userRepository.findById(subject).get();
    }

    private String roleValidFormat(User user) {
        return "ROLE_" + user.getRole().toString();
    }

}