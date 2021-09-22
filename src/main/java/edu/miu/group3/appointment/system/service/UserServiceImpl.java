package edu.miu.group3.appointment.system.service;

import edu.miu.group3.appointment.system.domain.User;
import edu.miu.group3.appointment.system.domain.events.UserRegisteredEvent;
import edu.miu.group3.appointment.system.repository.UserRepository;
import edu.miu.group3.appointment.system.service.util.CustomLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAdapterService adapterService;

    @Autowired
    private CustomLoggerService loggerService;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long userid) {
        Optional<User> user = userRepository.findById(userid);
        return user.isPresent() ? user.get() : null;
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(long userId) {
        User oldUser = findById(userId);
        if (oldUser == null) {
            return;
        }
        userRepository.deleteById(userId);
    }

    @Override
    public void handleUserRegistered(UserRegisteredEvent event) {
        loggerService.log("User registered event received : " + event);

        User user = adapterService.fromUserRegisteredEvent(event);
        userRepository.save(user);

        loggerService.log("User registered : " + user);
    }

    @Override
    public User findByUUID(String uuid) {
        return userRepository.findByUuid(uuid).orElse(null);
    }
}
