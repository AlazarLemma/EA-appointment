package edu.miu.group3.appointment.system.controller;

import edu.miu.group3.appointment.system.domain.User;
import edu.miu.group3.appointment.system.service.UserService;
import edu.miu.group3.appointment.system.service.dto.AuthUserSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/client")
    public String client(HttpServletRequest request) {
        AuthUserSubject user = (AuthUserSubject) request.getAttribute("user");
        System.out.println(user);
        return "client";
    }

    @PostMapping()
    public void createUser(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping()
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/{userid}")
    public User getUserById(@PathVariable int userid) {
        return userService.findById(userid);
    }

    @PutMapping("/{userid}")
    public User updateById(@PathVariable int userid, @RequestBody User user) {
        User u = userService.findById(userid);
        if (u == null) return null;
        user.setId(userid);
        return userService.update(user);
    }

    @DeleteMapping("/{userid}")
    public void deleteUser(@PathVariable int userid) {
        userService.delete(userid);
    }
}
