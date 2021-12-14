package com.pas.rest_pas.repositories;

import com.pas.rest_pas.entities.Rent;
import com.pas.rest_pas.entities.costume.Costume;
import com.pas.rest_pas.exceptions.*;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class RentRepository extends AbstractRepository<Rent> {

    public List<Rent> getRentsByCustomer(String login) {
        return getAll()
                .stream()
                .filter(e -> Objects.equals(e.getUser().getLogin(), login))
                .collect(Collectors.toList());
    }

    public Rent getRentById(UUID rentId) {
        return getAll()
                .stream()
                .filter(e -> e.getId().compareTo(rentId) == 0)
                .findAny()
                .orElse(null);
    }

    public void endRent(String date, UUID rentId) {
        if(getRentById(rentId) == null) {
            throw new RentByIdNotFound();
        } else if (Objects.equals(date, "now")) {
            LocalDate dateRentEnded = LocalDate.now();
            getRentById(rentId).setEndTime(dateRentEnded);
            for(Costume costume : getRentById(rentId).getCostumes()) {
                costume.setRented(true);
            }
        } else {
            LocalDate dateRentEnded = LocalDate.parse(date);
            System.out.println(dateRentEnded);
//            if(dateRentEnded.isBefore(LocalDate.now())) {
//                throw new DateInPastException();
//            }
            if(dateRentEnded.isBefore(LocalDate.now()))
            getRentById(rentId).setEndTime(dateRentEnded);
        }
    }

    public void setRentedCostumesToNotRented(UUID rentId) {
        if(getRentById(rentId) == null) {
            throw new RentByIdNotFound();
        } else {
            Iterator<Costume> it = getRentById(rentId).getCostumes().iterator();
            while(it.hasNext()) {
                it.next().setRented(false);
            }
        }
    }
}
