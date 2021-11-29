package com.pas.rest_pas.dao;

import com.pas.rest_pas.entities.Rent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentDao implements Dao<Rent> {

    List<Rent> rents = new ArrayList<>();

    @Override
    public Optional<Rent> get( long id ) {
        return Optional.ofNullable(rents.get((int) id));
    }

    @Override
    public List<Rent> getAll() {
        return rents;
    }

    @Override
    public void add(Rent rent) {
        rents.add(rent);
    }

    @Override
    public void remove(Rent rent) {
        rents.remove(rent);
    }
}
