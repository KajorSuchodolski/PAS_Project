package com.pas.rest_pas.dao;
import com.pas.rest_pas.entities.access_levels.AccessLevel;
import com.pas.rest_pas.entities.access_levels.AdministatorLevel;
import com.pas.rest_pas.entities.access_levels.ManagerLevel;
import com.pas.rest_pas.entities.access_levels.UserLevel;
import com.pas.rest_pas.entities.user.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepository extends AbstractRepository<User> {

    public UserRepository() {
        AccessLevel accessLevel = new AdministatorLevel();
        User userOne = new User(
                accessLevel,
                "Chrigel",
                "Glanzmann",
                123456789,
                "chrigel",
                "1234",
                "chrigel@gmail.com");

        User userTwo = new User(
                accessLevel,
                "Fabienne",
                "Erni",
                198765432,
                "fabi",
                "9876",
                "fabi@gmail.com");

        getAll().add(userOne);
        getAll().add(userTwo);
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

    // możliwe w sumie ograniczenie do samych uzytkownikow (klientow wypozyczalni)
    public List<User> getUsersByAccountType(AccessLevel.AccessLevelType type) {
        AccessLevel accessLevel;
        switch(type) {
            case USER:
                accessLevel = new UserLevel();
            case MANAGER:
                accessLevel = new ManagerLevel();
            case ADMINISTRATOR:
                accessLevel = new AdministatorLevel();
            default:
                accessLevel = null;
        }
        AccessLevel finalAccessLevel = accessLevel;
        return getAll()
                .stream()
                .filter(e -> e.getAccessLevel().getClass() == finalAccessLevel.getClass())
                .collect(Collectors.toList());
    }

    public boolean addUser(User user) {
        if(getUserByLogin(user.getLogin()) != null) {
            return add(user);
        }
        return false;
    }
}
