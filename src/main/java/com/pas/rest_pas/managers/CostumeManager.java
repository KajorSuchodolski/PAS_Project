package com.pas.rest_pas.managers;
import com.pas.rest_pas.dao.CostumeRepository;
import com.pas.rest_pas.entities.Costume;

import java.util.List;
import java.util.UUID;

public class CostumeManager {

    private CostumeRepository costumeRepository;

    public CostumeManager(CostumeRepository costumeRepository) {
        this.costumeRepository = costumeRepository;
    }

    public boolean add(Costume costume) {
        return costumeRepository.add(costume);
    }

    public List<Costume> getCostumesBySize(int size) {
        return costumeRepository.getCostumesBySize(size);
    }

    public void getAll() {
        costumeRepository.getAll();
    }

}
