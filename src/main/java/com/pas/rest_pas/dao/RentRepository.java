package com.pas.rest_pas.dao;

import com.pas.rest_pas.entities.Rent;
import com.pas.rest_pas.entities.user.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RentRepository extends AbstractRepository<Rent> {

    public List<Rent> getRentsByCustomer(User user) {
        return getAll()
                .stream()
                .filter(e -> user.equals(e.getUser()))
                .collect(Collectors.toList());
    }

    public List<Rent> getRentsByDate(Predicate<Rent> predicate) {
        return getAll()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
