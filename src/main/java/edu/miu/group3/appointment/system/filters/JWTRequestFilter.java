package edu.miu.group3.appointment.system.filters;

import edu.miu.group3.appointment.system.service.UserAdapterService;
import edu.miu.group3.appointment.system.service.dto.AuthUserSubject;
import edu.miu.group3.appointment.system.service.util.CustomLoggerService;
import edu.miu.group3.appointment.system.util.EncryptionUtil;
import edu.miu.group3.appointment.system.util.JWTUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserAdapterService adapterService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private EncryptionUtil encryptionUtil;

    @Autowired
    private CustomLoggerService loggerService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String decryptedJWt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            try {
                decryptedJWt = encryptionUtil.decrypt(jwt);
                username = jwtUtil.extractUsername(decryptedJWt);
            } catch (Exception ex) {
                loggerService.log("Cannot decrypt token " + jwt + " Exception : " +  ex.toString());
            }
        }

        if (username != null) {
            AuthUserSubject userSubject = jwtUtil.extractUserData(decryptedJWt);
            UserDetails userDetails = adapterService.fromJWTClaim(userSubject);
            if (jwtUtil.validateToken(decryptedJWt, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken
                        (userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                request.setAttribute("user", userSubject);
            }
        }

        filterChain.doFilter(request, response);
    }
}
