package com.pas.rest_pas.repositories;
import com.pas.rest_pas.entities.costume.Costume;
import com.pas.rest_pas.entities.costume.CostumeSize;
import com.pas.rest_pas.entities.costume.ForWhom;
import com.pas.rest_pas.exceptions.CostumeByIdNotFound;
import com.pas.rest_pas.exceptions.CostumeInUseException;
import com.pas.rest_pas.exceptions.EntityValidationException;
import com.pas.rest_pas.global_config.Validation;
import com.pas.rest_pas.global_config.ValidationParameter;


import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class CostumeRepository extends AbstractRepository<Costume> {

    public CostumeRepository() {
        Costume costume1 = new Costume(
                "Furry Costume",
                CostumeSize.XL,
                ForWhom.BOYS,
                1000000
        );
        Costume costume2 = new Costume(
                "Furry Costume",
                CostumeSize.XL,
                ForWhom.GIRLS,
                666
        );
        Costume costume3 = new Costume(
                "Pope",
                CostumeSize.XXL,
                ForWhom.MAN,
                420
        );
        Costume costume4 = new Costume(
                "Amogus Red Impostor",
                CostumeSize.S,
                ForWhom.BOYS,
                0.001
        );
        Costume costume5 = new Costume(
                "Zorro",
                CostumeSize.XL,
                ForWhom.GIRLS,
                100
        );
        addCostume(costume1);
        addCostume(costume2);
        addCostume(costume3);
        addCostume(costume4);
        addCostume(costume5);
    }

    // READ
    public List<Costume> getAllCostumesByAge(String age) {
        ForWhom forWhom;
        try {
            forWhom = ForWhom.valueOf(age);
        } catch(IllegalArgumentException e) {
            throw new EntityValidationException();
        }
        return getAll()
                .stream()
                .filter(e -> e.getForWhom().equals(forWhom))
                .collect(Collectors.toList());
    }

    public List<Costume> getAllCostumesByParams(String age, String size) {
        CostumeSize costumeSize;
        ForWhom forWhom;
        try {
           costumeSize = CostumeSize.valueOf(size);
           forWhom = ForWhom.valueOf(age);
        } catch(IllegalArgumentException e) {
            throw new EntityValidationException();
        }
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

    public void updateCostume(UUID id, Costume costume) throws CostumeByIdNotFound {

        if(getById(id) == null) {
            throw new CostumeByIdNotFound();
        } else {

            if(costume.getName() != null) {
                if( Validation.validateData(costume.getName(), ValidationParameter.COSTUME_NAME)) {
                    throw new EntityValidationException();
                }
                getById(id).setName(costume.getName());
            }
            if(costume.getCostumeSize() != null) {
                if( !Validation.validateData(costume.getCostumeSize().toString(), ValidationParameter.COSTUME_SIZE)) {
                    throw new EntityValidationException();
                }
                getById(id).setCostumeSize(costume.getCostumeSize());
            }
            if(costume.getForWhom() != null) {
                if( !Validation.validateData(costume.getForWhom().toString(), ValidationParameter.FOR_WHOM)) {
                    throw new EntityValidationException();
                }
                getById(id).setForWhom(costume.getForWhom());
            }
            if(costume.getPrice() < 999999999) {
                if( !Validation.validateData(Double.toString(costume.getPrice()), ValidationParameter.PRICE)) {
                    throw new EntityValidationException();
                }
                getById(id).setPrice(costume.getPrice());
            }


        }

    }



}
