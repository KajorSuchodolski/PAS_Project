package com.pas.rest_pas.repositories;
import com.pas.rest_pas.exceptions.EntityValidationException;
import com.pas.rest_pas.exceptions.UserUpdateException;
import com.pas.rest_pas.entities.user.User;
import com.pas.rest_pas.exceptions.UserAdditionException;
import com.pas.rest_pas.global_config.Validation;
import com.pas.rest_pas.global_config.ValidationParameter;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserRepository extends AbstractRepository<User> {

    public UserRepository() throws UserAdditionException {
        User user1 = new User(
                "Radosław",
                "Zyzik",
                "KajorSuchodolski",
                "hehe",
                "radek2000@onet.pl"
        );
        User user2 = new User(
                "Radosław",
                "Hyży",
                "Radek460",
                "admin1",
                "test@upd.pl"
        );
        User user3 = new User(
                "Radosław",
                "Kowalski",
                "MrRadek2000",
                "admin1",
                "293032@ea.pl"
        );

        addUser(user1);
        addUser(user2);
        addUser(user3);
    }
    public void activateUser(String login) throws UserUpdateException{
        if(getUserByLogin(login) == null) throw new UserUpdateException();
        getUserByLogin(login).setActive(true);
    }

    public void deactivateUser(String login) throws UserUpdateException{
        if(getUserByLogin(login) == null) throw new UserUpdateException();
        getUserByLogin(login).setActive(false);
    }

    public User getUserByLogin(String login) {
        return getAll()
                .stream()
                .filter(e -> login.equals(e.getLogin()))
                .findAny()
                .orElse(null);
    }

    public User getUserByEmail(String login) {
        return getAll()
                .stream()
                .filter(e -> login.equals(e.getEmail()))
                .findAny()
                .orElse(null);
    }

    public List<User> searchUsersByLogin(String login) {
        return getAll()
                .stream()
                .filter(e -> e.getLogin().contains(login))
                .collect(Collectors.toList());
    }

    public void addUser(User user) throws UserAdditionException {
        if(getUserByLogin(user.getLogin()) != null) {
            throw new UserAdditionException();
        }
        if(getUserByEmail(user.getEmail()) != null) {
            throw new UserAdditionException();
        }
        add(user);
    }

    public void updateUser(String login, User user) throws UserUpdateException {
        if(getUserByLogin(login) == null) {
            throw new UserUpdateException();
        } else {
            if(user.getFirstName() != null) {
                if( Validation.validateData(user.getFirstName(), ValidationParameter.FIRSTNAME) ) {
                    throw new EntityValidationException();
                }
                getUserByLogin(login).setFirstName(user.getFirstName());
            }
            if(user.getLastName() != null) {
                if( Validation.validateData(user.getLastName(), ValidationParameter.LASTNAME) ) {
                    throw new EntityValidationException();
                }
                getUserByLogin(login).setLastName(user.getLastName());
            }
            if(user.getLogin() != null) {
                if( Validation.validateData(user.getLogin(), ValidationParameter.LOGIN) ) {
                    throw new EntityValidationException();
                }
                getUserByLogin(login).setLogin(user.getLogin());
            }
            if(user.getPassword() != null) {
                if( Validation.validateData(user.getPassword(), ValidationParameter.PASSWORD) ) {
                    throw new EntityValidationException();
                }
                getUserByLogin(login).setPassword(user.getPassword());
            }
            if(user.getEmail() != null) {
                if( Validation.validateData(user.getEmail(), ValidationParameter.EMAIL) ) {
                    throw new EntityValidationException();
                }
                getUserByLogin(login).setEmail(user.getEmail());
            }
        }
    }
}
