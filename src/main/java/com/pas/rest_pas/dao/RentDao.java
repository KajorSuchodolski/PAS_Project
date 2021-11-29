package com.pas.rest_pas.dao;

import com.pas.rest_pas.entities.Rent;

import java.util.List;
import java.util.Optional;

public class RentDao implements Dao<Rent> {

    @Override
    public Optional<Rent> get( long id ) {
        return Optional.empty();
    }

    @Override
    public List<Rent> getAll() {
        return null;
    }

    @Override
    public void add( Rent rent ) {

    }

    @Override
    public void remove( Rent rent ) {

    }
}
