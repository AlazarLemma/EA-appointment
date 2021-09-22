package edu.miu.group3.appointment.system.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private long id;

    @NonNull
    @Column(unique = true)
    private String username;
    
    @NonNull
    @Column(unique = true)
    private String uuid;

    @NonNull
    private boolean isActive;

    @ElementCollection
    private List<String> roles = new ArrayList<>();

    public User(String username, String uuid, Boolean isActive, List<String> roles) {

        this.username = username;
        this.uuid = uuid;
        this.isActive = isActive;
        this.roles = roles;
    }
}
