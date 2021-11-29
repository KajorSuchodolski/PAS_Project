package com.pas.rest_pas.dao;
import com.pas.rest_pas.entities.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {

    List<User> users = new ArrayList<>();

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(users.get((int) id));
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void add(User user) {
        users.add(user);
    }


    @Override
    public void remove(User user) {
        users.remove(user);
    }
}
