package edu.miu.group3.appointment.system.service;

import edu.miu.group3.appointment.system.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    UserRoleRepository userRoleRepository;
}
