package com.pas.rest_pas.managers;
import com.pas.rest_pas.entities.costume.Costume;
import com.pas.rest_pas.entities.user.User;
import com.pas.rest_pas.exceptions.*;
import com.pas.rest_pas.exceptions.UserByLoginNotFound;
import com.pas.rest_pas.global_config.Validation;
import com.pas.rest_pas.global_config.ValidationParameter;
import com.pas.rest_pas.repositories.RentRepository;
import com.pas.rest_pas.entities.Rent;

import javax.inject.Inject;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class RentManager {

    private RentRepository rentRepository;
    private UserManager userManager;
    private CostumeManager costumeManager;

    @Inject
    public void setRentRepository(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @Inject
    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    @Inject
    public void setCostumeManager(CostumeManager costumeManager) {
        this.costumeManager = costumeManager;
    }

    public List<Rent> getRentsByCustomer(String login) {
        return rentRepository.getRentsByCustomer(login);
    }

//    public List<Rent> getRentsByDate(Predicate<Rent> predicate) {
//        return rentRepository.getRentsByDate(predicate);
//    }

    public List<Rent> getAll() {
        return rentRepository.getAll();
    }

    public void addRent(String userLogin, List<UUID> costumeIds, String date) throws UserByLoginNotFound, CostumeInUseException, CostumeByIdNotFound, DateInPastException, WrongDateFormatException {
        if (userManager.getUserByLogin(userLogin) == null) {
            throw new UserByLoginNotFound();
        }
        if (!userManager.getUserByLogin(userLogin).isActive()) {
            throw new UserInactiveException();
        }
        if (!Objects.equals(date, "now")) {
            try {
                LocalDate.parse(date);
            } catch (DateTimeException e) {
                throw new WrongDateFormatException();
            }

            LocalDate dateRented = LocalDate.parse(date);

            if (dateRented.isBefore(LocalDate.now())) {
                throw new DateInPastException();
            }
        }


        List<Costume> costumes = new ArrayList<>();
        Iterator<UUID> id = costumeIds.iterator();

        double totalPrice = 0;

        while(id.hasNext()) {
            Costume currentCostume = costumeManager.getCostumeById(id.next());
            if (currentCostume == null) {
                throw new CostumeByIdNotFound();
            }
            if (currentCostume.isRented()) {
                throw new CostumeInUseException();
            }
            currentCostume.setRented(true);
            costumes.add(currentCostume);
            totalPrice += currentCostume.getPrice();
        }

        if (Objects.equals(date, "now")) {
            LocalDate dateRented = LocalDate.now();
            Rent newRent = new Rent(userManager.getUserByLogin(userLogin), costumes, totalPrice, dateRented);
            rentRepository.add(newRent);
        } else {
            LocalDate dateRented = LocalDate.parse(date);

            Rent newRent = new Rent(userManager.getUserByLogin(userLogin), costumes, totalPrice, dateRented);
            rentRepository.add(newRent);
        }
    }

    public Rent getRentById(UUID rentId) {
        return rentRepository.getRentById(rentId);
    }

    public List<Rent> userCurrentRents(String userLogin) throws UserByLoginNotFound {
        if (userManager.getUserByLogin(userLogin) == null) {
            throw new UserByLoginNotFound();
        }
        return rentRepository.userCurrentRents(userLogin);
    }
    public List<Rent> userPastRents(String userLogin) throws UserByLoginNotFound {
        if (userManager.getUserByLogin(userLogin) == null) {
            throw new UserByLoginNotFound();
        }
        return rentRepository.userPastRents(userLogin);
    }

    public void endRent(String date, UUID rentId) throws RentByIdNotFound{
        rentRepository.endRent(date, rentId);
    }

    public void deleteRentFromRepo(UUID rentId) throws RentByIdNotFound{
        Rent rentToBeDeleted = rentRepository.getRentById(rentId);
        if (rentToBeDeleted == null) {
            throw new RentByIdNotFound();
        }
        rentRepository.setRentedCostumesToNotRented(rentId);
        rentRepository.delete(rentToBeDeleted);
    }

}
