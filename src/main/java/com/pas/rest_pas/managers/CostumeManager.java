package com.pas.rest_pas.managers;
import com.pas.rest_pas.dao.CostumeRepository;
import com.pas.rest_pas.entities.Costume;

import java.util.UUID;

public class CostumeManager {

    private CostumeRepository costumeRepository;

    public CostumeManager(CostumeRepository costumeRepository) {
        this.costumeRepository = costumeRepository;
    }

//    public boolean add(Costume costume) {
//        if (!userRepository.add(user)) return false;
//        return true;
//    }
//
//    public User getUserById(UUID id) {
//        return userRepository.getById(id);
//    }
//
//    public User getUserByLogin(String login) {
//        return userRepository.getUserByLogin(login);
//    }
//
//    public void getAll() {
//        userRepository.getAll();
//    }

}
