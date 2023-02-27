package com.khpi.service.security.filters;

import com.khpi.service.security.token.TokenAuthentication;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Token Authentication Filter
// The token authentication filter is responsible to get the authentication filter
// from the header and call the authentication manager for authentication.
//    https://www.marcobehler.com/guides/spring-security

@Component
public class TokenAuthFilter  extends OncePerRequestFilter
{
    private AuthenticationManager authenticationManager;

    @Autowired
    public TokenAuthFilter(AuthenticationManager authenticationManager) {
        Assert.notNull(authenticationManager, "authenticationManager cannot be null");
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
        throws IOException, ServletException
    {
        String token = request.getParameter("token");

        TokenAuthentication tokenAuthentication = new TokenAuthentication(token);
        if (token == null) {
            tokenAuthentication.setAuthenticated(false);
        }
        else {
            SecurityContextHolder
                .getContext()
                .setAuthentication(authenticationManager.authenticate(tokenAuthentication));
        }

        filterChain.doFilter(request, response);
    }
}
