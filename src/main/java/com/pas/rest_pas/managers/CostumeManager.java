package com.pas.rest_pas.managers;

import com.pas.rest_pas.entities.costume.Costume;
import com.pas.rest_pas.entities.costume.CostumeSize;
import com.pas.rest_pas.entities.costume.ForWhom;
import com.pas.rest_pas.exceptions.CostumeByIdNotFound;
import com.pas.rest_pas.exceptions.CostumeInUseException;
import com.pas.rest_pas.exceptions.EntityValidationException;
import com.pas.rest_pas.global_config.Validation;
import com.pas.rest_pas.global_config.ValidationParameter;
import com.pas.rest_pas.repositories.CostumeRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

@RequestScoped
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

    public Costume getCostumeById(UUID id) throws CostumeByIdNotFound {
        return costumeRepository.getById(id);
    }

    public List<Costume> getAll() {
        return costumeRepository.getAll();
    }

    public List<Costume> getAllByRentStatus(boolean flag) { return costumeRepository.getAllByRentStatus(flag); }

    public List<Costume> searchAllCostumesByName(String name) {
        return costumeRepository.searchCostumesByName(name);
    }

    public List<Costume> getAllCostumesByAge(String forWhom) throws EntityValidationException {
        try {
            ForWhom parameterValidationChecker = ForWhom.valueOf(forWhom);
            return costumeRepository.getAllCostumesByAge(parameterValidationChecker);
        } catch(IllegalArgumentException e) {
            throw new EntityValidationException("Invalid parameter for enum type: ForWhom");
        }

    }

    public List<Costume> getAllCostumesByParams(String age, String size) throws EntityValidationException {
        try {
            CostumeSize costumeSize = CostumeSize.valueOf(size);
            ForWhom forWhom = ForWhom.valueOf(age);
            return costumeRepository.getAllCostumesByParams(forWhom, costumeSize);
        } catch(IllegalArgumentException e) {
            throw new EntityValidationException("Invalid parameter for ForWhom or CostumeSize");
        }

    }

    // UPDATE

    public void updateCostume(UUID id, Costume costume) throws CostumeByIdNotFound, EntityValidationException {

        Costume tmpCostume = new Costume();

        if(costume.getName() != null) {
            if( Validation.validateData(costume.getName(), ValidationParameter.COSTUME_NAME)) {
                throw new EntityValidationException("Costume name is invalid");
            }
            tmpCostume.setName(costume.getName());
        }
        if(costume.getCostumeSize() != null) {
            try {
                CostumeSize costumeSize = costume.getCostumeSize();
            } catch(IllegalArgumentException e){
                throw new EntityValidationException("Invalid parameter for: CostumeSize");
            }
            tmpCostume.setCostumeSize(costume.getCostumeSize());
        }
        if(costume.getForWhom() != null) {
            try {
                ForWhom forWhom = costume.getForWhom();
            }
            catch(IllegalArgumentException e) {
                throw new EntityValidationException("Invalid parameter for: ForWhom");
            }
            tmpCostume.setForWhom(costume.getForWhom());
        }
        if(!Validation.validateData(Double.toString(costume.getPrice()), ValidationParameter.PRICE)) {
            throw new EntityValidationException("Price of the costume is invalid");
        }
        costumeRepository.updateCostume(id, tmpCostume);
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
