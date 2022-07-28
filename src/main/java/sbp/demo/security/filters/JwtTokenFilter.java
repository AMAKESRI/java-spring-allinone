package sbp.demo.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sbp.demo.security.utils.JwtTokenUtil;

import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Get authorization header and validate
        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isEmpty(authorizationHeader) || !authorizationHeader.startsWith(AUTHORIZATION_HEADER_PREFIX)){
            filterChain.doFilter(request,response);
            return;
        }

        // Get jwt token and validate
        final String jwtToken = authorizationHeader.split(" ")[1];
        if(!JwtTokenUtil.validate(jwtToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Get user identity
        UserDetails userDetails = userDetailsService.loadUserByUsername(JwtTokenUtil.getUsernameFromToken(jwtToken));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );

        // set additional details about the authentication request like IP address, Certificate Serial Number etc.
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // set it on the spring security context
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
