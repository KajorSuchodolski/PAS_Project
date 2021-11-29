package com.pas.rest_pas.managers;

import com.pas.rest_pas.dao.CostumeDao;
import com.pas.rest_pas.entities.Costume;

public class CostumeManager {

    final private CostumeDao costumeDao = new CostumeDao();

    public CostumeManager() {
    }

    public void add(Costume costume) {
        costumeDao.add(costume);
    }

    public void remove(Costume costume) {
        costumeDao.remove(costume);
    }

    public void get(long id) {
        costumeDao.get(id);
    }

    public void getAll() {
        costumeDao.getAll();
    }
}
