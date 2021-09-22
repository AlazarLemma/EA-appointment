package edu.miu.group3.appointment.system.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserSubject {
    private String username;
    private Boolean isActive;
    private List<String> roles;
    private String uuid;

    public static AuthUserSubject fromMap(Map<String, Object> map) {
        return new AuthUserSubject(map.get("username").toString(), (Boolean) map.get("isActive"), (List)map.get("roles"), map.get("uuid").toString());
    }
}
