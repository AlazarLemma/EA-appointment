package edu.miu.group3.appointment.system.service;

import edu.miu.group3.appointment.system.domain.User;
import edu.miu.group3.appointment.system.domain.events.UserRegisteredEvent;
import edu.miu.group3.appointment.system.repository.UserRepository;
import edu.miu.group3.appointment.system.service.util.CustomLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public void save(User user);
    public List<User> findAll();
    public User findById(long userid);
    public User update(User user);
    public void delete(long userId);
    public void handleUserRegistered(UserRegisteredEvent event);
    public User findByUUID(String uuid);

}
