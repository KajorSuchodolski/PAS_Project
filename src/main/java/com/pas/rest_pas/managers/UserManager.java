package com.pas.rest_pas.managers;
import com.pas.rest_pas.dao.UserRepository;
import com.pas.rest_pas.entities.user.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
public class UserManager {

    private UserRepository userRepository;

    @Inject
    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean add(User user) {
        if (!userRepository.add(user)) return false;
        return true;
    }

    public User getUserById(UUID id) {
       return userRepository.getById(id);
    }

    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    public void getAll() {
        userRepository.getAll();
    }

}
