package com.pas.rest_pas.repositories;
import com.pas.rest_pas.exceptions.UserUpdateException;
import com.pas.rest_pas.entities.user.User;
import com.pas.rest_pas.exceptions.UserAdditionException;

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
    public void activateUser(User user) {
        getById(user.getId()).setActive(true);
    }

    public void deactivateUser(User user) {
        getById(user.getId()).setActive(false);
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

    public void updateUser(User user) throws UserUpdateException {

        if(getUserByLogin(user.getLogin()) == null) {
            throw new UserUpdateException();
        } else {

            if( !user.getFirstName().equals("") ) {
                getUserByLogin(user.getLogin()).setFirstName(user.getFirstName());
            }
            if( !user.getLastName().equals("") ) {
                getUserByLogin(user.getLogin()).setFirstName(user.getLastName());
            }
            if( !user.getLogin().equals("") ) {
                getUserByLogin(user.getLogin()).setFirstName(user.getLogin());
            }
            if( !user.getPassword().equals("") ) {
                getUserByLogin(user.getLogin()).setFirstName(user.getPassword());
            }
            if( !user.getEmail().equals("") ) {
                getUserByLogin(user.getLogin()).setFirstName(user.getEmail());
            }

        }

    }
}
