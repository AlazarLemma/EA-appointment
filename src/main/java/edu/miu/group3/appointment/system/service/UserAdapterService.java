package edu.miu.group3.appointment.system.service;

import edu.miu.group3.appointment.system.service.dto.AuthUserDetails;
import edu.miu.group3.appointment.system.service.dto.AuthUserSubject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAdapterService {
    public UserDetails fromJWTClaim(AuthUserSubject subject) {
        List<String> roles = subject.getRoles();
        List<GrantedAuthority> grantedAuthorities = roles.stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return new AuthUserDetails(
                subject.getUsername(),
                null,
                subject.getIsActive(),
                grantedAuthorities
        );
    }
}
