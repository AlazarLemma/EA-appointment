package edu.miu.group3.appointment.system.domain;

import javax.persistence.*;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String userRole;

    public UserRole() {
    }

    public UserRole(String userRole) {
        this.userRole = userRole;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
