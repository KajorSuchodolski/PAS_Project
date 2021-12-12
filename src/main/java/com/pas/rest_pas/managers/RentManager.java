package com.pas.rest_pas.managers;
import com.pas.rest_pas.repositories.RentRepository;
import com.pas.rest_pas.entities.Rent;
import com.pas.rest_pas.entities.user.User;
import com.pas.rest_pas.repositories.UserRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public class RentManager {
    private RentRepository rentRepository;

    @Inject
    public void setRentRepository(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    public boolean add(Rent rent) {
        return rentRepository.add(rent);
    }

    public List<Rent> getRentsByCustomer(User user) {
        return rentRepository.getRentsByCustomer(user);
    }

    public List<Rent> getRentsByDate(Predicate<Rent> predicate) {
        return rentRepository.getRentsByDate(predicate);
    }

    public List<Rent> getAll() {
        return rentRepository.getAll();
    }
}
