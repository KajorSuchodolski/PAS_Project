package com.pas.rest_pas.managers;

import com.pas.rest_pas.entities.costume.Costume;
import com.pas.rest_pas.entities.costume.ForWhom;
import com.pas.rest_pas.exceptions.CostumeByIdNotFound;
import com.pas.rest_pas.exceptions.CostumeInUseException;
import com.pas.rest_pas.repositories.CostumeRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

public class CostumeManager {

    private CostumeRepository costumeRepository;

    public CostumeManager() {
    }

    @Inject
    public void setCostumeRepository( CostumeRepository costumeRepository ) {
        this.costumeRepository = costumeRepository;
    }

    public CostumeRepository getCostumeRepository() {
        return costumeRepository;
    }

    // CREATE
    public void addCostume(Costume costume) {
        costumeRepository.addCostume(costume);
    }

    // READ

    public Costume getCostumeById(UUID id) {
        return costumeRepository.getById(id);
    }

    public List<Costume> getAll() {
        return costumeRepository.getAll();
    }

    public List<Costume> searchAllCostumesByName(String name) {
        return costumeRepository.searchCostumesByName(name);
    }

    public List<Costume> getAllCostumesByAge(String forWhom) {
        return costumeRepository.getAllCostumesByAge(forWhom);
    }

    public List<Costume> getAllCostumesByParams(String age, String size) {
        return costumeRepository.getAllCostumesByParams(age, size);
    }

    // UPDATE

    public void updateCostume(Costume costume) throws CostumeByIdNotFound {
        costumeRepository.updateCostume(costume);
    }

    public void activateRent(UUID id) {
        costumeRepository.getById(id).setRented(true);
    }

    public void deactivateRent(UUID id) {
        costumeRepository.getById(id).setRented(false);
    }

    // DELETE
    public void removeCostume(UUID id) throws CostumeByIdNotFound, CostumeInUseException {
        costumeRepository.removeCostume(id);
    }



}
