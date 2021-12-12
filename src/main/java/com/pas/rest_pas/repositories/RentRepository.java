package com.pas.rest_pas.repositories;

import com.pas.rest_pas.entities.Rent;
import com.pas.rest_pas.entities.user.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@ApplicationScoped
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
