package edu.miu.group3.appointment.system.service;

import edu.miu.group3.appointment.system.domain.User;
import edu.miu.group3.appointment.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void save(User user) {

        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long userid) {
        Optional<User> user = userRepository.findById(userid);
        return user.isPresent() ? user.get() : null;
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(long userId) {
        User oldUser = findById(userId);
        if (oldUser == null) {
            return;
        }
        userRepository.deleteById(userId);
    }
}
