package com.pas.rest_pas.dao;
import com.pas.rest_pas.entities.Costume;

import java.util.List;
import java.util.stream.Collectors;

public class CostumeRepository extends AbstractRepository<Costume> {

    public CostumeRepository() {
        Costume costumeOne = new Costume(
                    false,
                    38,
                    "Wolf",
                    Costume.Category.ANIMAL);

        Costume costumeTwo = new Costume(
                    false,
                    40,
                    "Sailor Moon",
                    Costume.Category.ANIME);

        getAll().add(costumeOne);
        getAll().add(costumeTwo);
    }

    public List<Costume> getCostumesBySize(int size) {
        return getAll()
                .stream()
                .filter(e -> e.getSize() == size)
                .collect(Collectors.toList());
    }

    public List<Costume> getCostumesByCategory(Costume.Category category) {
        return getAll()
                .stream()
                .filter(e -> e.getCategory() == category)
                .collect(Collectors.toList());
    }

}
