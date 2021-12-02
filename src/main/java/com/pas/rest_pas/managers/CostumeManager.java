package com.pas.rest_pas.managers;
import com.pas.rest_pas.dao.CostumeRepository;
import com.pas.rest_pas.entities.Costume;

import java.util.List;

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

    public List<Costume> getCostumesByCategory(Costume.Category category) {
        return costumeRepository. getCostumesByCategory(category);
    }

    public void getAll() {
        costumeRepository.getAll();
    }

}
