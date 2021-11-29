package com.pas.rest_pas.managers;
import com.pas.rest_pas.dao.UserDao;
import com.pas.rest_pas.entities.Costume;
import com.pas.rest_pas.entities.user.User;

import java.util.Optional;

public class UserManager {

    final private UserDao userDao = new UserDao();

    public UserManager() {
    }

    public void add(User user) {
        userDao.add(user);
    }

    public void remove(User user) {
        userDao.remove(user);
    }

    public void get(long id) {
        userDao.get(id);
    }

    public void getAll() {
        userDao.getAll();
    }

}
