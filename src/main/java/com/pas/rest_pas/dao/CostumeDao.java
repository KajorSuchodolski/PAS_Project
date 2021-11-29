package com.pas.rest_pas.dao;
import com.pas.rest_pas.entities.Costume;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CostumeDao implements Dao<Costume> {

    List<Costume> costumes = new ArrayList<>();

    @Override
    public Optional get(long id) {
        return Optional.ofNullable(costumes.get((int) id));
    }

    @Override
    public List getAll() {
        return costumes;
    }

    @Override
    public void add(Costume costume) {
        costumes.add(costume);
    }


    @Override
    public void remove(Costume costume) {
        costumes.remove(costume);
    }
}
