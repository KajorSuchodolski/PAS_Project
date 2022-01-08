package com.pas.rest_pas.managers;
import com.pas.rest_pas.exceptions.*;
import com.pas.rest_pas.global_config.Validation;
import com.pas.rest_pas.global_config.ValidationParameter;
import com.pas.rest_pas.repositories.UserRepository;
import com.pas.rest_pas.entities.user.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;


@RequestScoped
public class UserManager  {

    private UserRepository userRepository;

    @Inject
    public void setUserRepository( UserRepository userRepository ) {
        this.userRepository = userRepository;
    }


    // CREATE

    /*
    *
    * TODO
    *  FIX VALIADATION
    *
    *
    * */

    public void addUser(User user) throws UserCreationException {
        if( user.getLogin() == null ) {
            throw new UserCreationException("Login field is empty");
        }
        if( user.getEmail() == null ) {
            throw new UserCreationException("Email field is empty");
        }
        if( user.getPassword() == null ) {
            throw new UserCreationException("Password field is empty");
        }
        if( user.getFirstName() == null ) {
            throw new UserCreationException("Firstname field is empty");
        }
        if( user.getLastName() == null ) {
            throw new UserCreationException("Lastname field is empty");
        }

        if(Validation.validateData(user.getFirstName(), ValidationParameter.FIRSTNAME)
                || Validation.validateData(user.getLastName(), ValidationParameter.LASTNAME)
                || Validation.validateData(user.getLogin(), ValidationParameter.LOGIN)
                || Validation.validateData(user.getPassword(), ValidationParameter.PASSWORD)
                || Validation.validateData(user.getEmail(), ValidationParameter.EMAIL)) {
            throw new UserCreationException("Sth is fucked up with validation");
        }
        userRepository.addUser(user);
    }

    // READ


    public User getUserById(UUID id) throws UserByIdNotFound {
        User user = userRepository.getById(id);
        if(user == null) {
            throw new UserByIdNotFound();
        }
        return user;
    }

    public User getUserByLogin(String login) throws UserByLoginNotFound{
        User user = userRepository.getUserByLogin(login);
        if(user == null) {
            throw new UserByLoginNotFound();
        }
        return user;
    }

    public List<User> getAll() {
       return userRepository.getAll();
    }

    public List<User> searchUsersByLogin(String login) {
        return userRepository.searchUsersByLogin(login);
    }

    // UPDATE

    public void updateUser(String login, User user) throws EntityValidationException, UserUpdateException {
        User tmpUser = new User();
        if(user.getFirstName() != null) {
            if(Validation.validateData(user.getFirstName(), ValidationParameter.FIRSTNAME) ) {
                throw new EntityValidationException("User firstname is invalid");
            }
            tmpUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName() != null) {
            if(Validation.validateData(user.getLastName(), ValidationParameter.LASTNAME) ) {
                throw new EntityValidationException("User lastname is invalid");
            }
            tmpUser.setLastName(user.getLastName());
        }
        if(user.getPassword() != null) {
            if(Validation.validateData(user.getPassword(), ValidationParameter.PASSWORD) ) {
                throw new EntityValidationException("User password is invalid");
            }
            tmpUser.setPassword(user.getPassword());
        }
        if(user.getEmail() != null) {
            if(Validation.validateData(user.getEmail(), ValidationParameter.EMAIL)) {
                throw new EntityValidationException("User email is invalid");
            }
            tmpUser.setEmail(user.getEmail());
        }
        userRepository.updateUser(login, tmpUser);

    }

    public void activateUser(String login) throws UserByLoginNotFound {
        userRepository.activateUser(login);
    }

    public void deactivateUser(String login) throws UserByLoginNotFound {
        userRepository.deactivateUser(login);
    }



}
