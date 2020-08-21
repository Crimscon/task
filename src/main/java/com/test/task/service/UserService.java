package com.test.task.service;

import com.test.task.model.Role;
import com.test.task.model.User;
import com.test.task.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }

    public boolean addUser(String username, String password) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setRoles(Collections.singleton(Role.USER));

        userRepo.save(user);

        return true;
    }
}
