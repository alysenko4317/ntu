package com.khpi.service.security.filters;

import com.khpi.service.models.Token;
import com.khpi.service.repositories.TokensRepository;
import com.khpi.service.security.details.UserDetailsServiceImpl;
import com.khpi.service.security.token.TokenAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

// Token Authentication Filter
// The token authentication filter is responsible to get the authentication filter
// from the header and call the authentication manager for authentication.
//    https://www.marcobehler.com/guides/spring-security
//
// THIS IS SAMPLE OF NOT FULLY CORRECT IMPLEMENTATION
//    but this filter is also functionally working well.

//@Component
public class TokenAuthFilter2 extends OncePerRequestFilter
{
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TokensRepository tokensRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
        throws ServletException, IOException
    {
        String token = request.getParameter("token");
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }

        Optional<Token> tokenCandidate = tokensRepository.findOneByValue(token);
        if (! tokenCandidate.isPresent()) {
            filterChain.doFilter(request, response);
            return;
        }

        // *** Instead of doing this here better to call AuthenticationManager ***
        // ***           see TokenAuthFilter.class for details                 ***

        UserDetails userDetails =
            userDetailsService.loadUserByUsername(
                tokenCandidate.get().getAccount().getEmail());

        UsernamePasswordAuthenticationToken authentication
            = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // ***           see TokenAuthFilter.class for details                 ***

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
