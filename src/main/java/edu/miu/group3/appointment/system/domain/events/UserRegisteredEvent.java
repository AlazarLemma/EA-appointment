package edu.miu.group3.appointment.system.domain.events;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisteredEvent {
    private String username;
    private String uuid;
    private Boolean isActive;
    private List<String> roles;
}
