package com.pas.rest_pas.repositories;
import com.pas.rest_pas.entities.costume.Costume;
import com.pas.rest_pas.entities.costume.CostumeSize;
import com.pas.rest_pas.entities.costume.ForWhom;
import com.pas.rest_pas.exceptions.CostumeByIdNotFound;
import com.pas.rest_pas.exceptions.CostumeInUseException;


import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class CostumeRepository extends AbstractRepository<Costume> {


    // READ
    public List<Costume> getAllCostumesByAge(String age) {
        ForWhom forWhom = ForWhom.valueOf(age);
        return getAll()
                .stream()
                .filter(e -> e.getForWhom().equals(forWhom))
                .collect(Collectors.toList());
    }

    public List<Costume> getAllCostumesByParams(String age, String size) {
        CostumeSize costumeSize = CostumeSize.valueOf(size);
        ForWhom forWhom = ForWhom.valueOf(age);
        return getAll()
                .stream()
                .filter(e -> e.getCostumeSize().equals(costumeSize) && e.getForWhom().equals(forWhom))
                .collect(Collectors.toList());
    }

    public List<Costume> searchCostumesByName(String name) {
        return getAll()
                .stream()
                .filter(e -> e.getName().contains(name))
                .collect(Collectors.toList());
    }



    // CREATE

    public void addCostume(Costume costume) {
        add(costume);
    }

    // DELETE

    public void removeCostume(UUID id) throws CostumeByIdNotFound, CostumeInUseException {
        if(getById(id) == null) {
            throw new CostumeByIdNotFound();
        } else if(getById(id).isRented()) {
            throw new CostumeInUseException();
        } else {
            delete(getById(id));
        }
    }

    // UPDATE

    public void updateCostume(Costume costume) throws CostumeByIdNotFound {

        if(getById(costume.getId()) == null) {
            throw new CostumeByIdNotFound();
        } else {

            if(!costume.getName().equals("")) {
                getById(costume.getId()).setName(costume.getName());
            }
            if(costume.getCostumeSize() != null) {
                getById(costume.getId()).setCostumeSize(costume.getCostumeSize());
            }
            if(costume.getForWhom() != null ) {
                getById(costume.getId()).setForWhom(costume.getForWhom());
            }


        }

    }



}
