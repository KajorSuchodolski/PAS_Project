package com.pas.rest_pas.managers;
import com.pas.rest_pas.exceptions.EntityValidationException;
import com.pas.rest_pas.global_config.Validation;
import com.pas.rest_pas.global_config.ValidationParameter;
import com.pas.rest_pas.repositories.UserRepository;
import com.pas.rest_pas.entities.user.User;
import com.pas.rest_pas.exceptions.UserAdditionException;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;


public class UserManager  {

    private UserRepository userRepository;

    @Inject
    public void setUserRepository( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    // C

    public void addUser(User user) throws UserAdditionException {
        userRepository.addUser(user);
    }

    // R

    public User getUserById(UUID id) {
       return userRepository.getById(id);
    }

    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    public List<User> getAll() {
       return userRepository.getAll();
    }

    public List<User> searchUsersByLogin(String login) {
        return userRepository.searchUsersByLogin(login);
    }

    // U

    public void updateUser(String login, User user) {
        userRepository.updateUser(login, user);

    }

    public void activateUser(String login) {
        userRepository.activateUser(login);
    }

    public void deactivateUser(String login) {
        userRepository.deactivateUser(login);
    }



}
