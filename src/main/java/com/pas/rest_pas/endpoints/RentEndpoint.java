package com.pas.rest_pas.endpoints;

import com.pas.rest_pas.entities.Rent;
import com.pas.rest_pas.entities.costume.Costume;
import com.pas.rest_pas.entities.user.User;
import com.pas.rest_pas.exceptions.CostumeInUseException;
import com.pas.rest_pas.exceptions.RentByIdNotFound;
import com.pas.rest_pas.exceptions.UserAdditionException;
import com.pas.rest_pas.exceptions.UserByLoginNotFound;
import com.pas.rest_pas.managers.RentManager;
import com.pas.rest_pas.managers.UserManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import java.util.UUID;

@Path("/rents")
public class RentEndpoint {

    @Inject
    private RentManager rentManager;

    // CREATE
    @POST
    @Path("/add")
    @Consumes("application/json")
    public void addRent(@QueryParam("userLogin") String userLogin, @QueryParam("date") String date, List<UUID> costumeIds) throws CostumeInUseException {
        rentManager.addRent(userLogin, costumeIds, date);
    }

    // READ
    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Rent> getAll() {
        return rentManager.getAll();
    }

    @GET
    @Path("/getByCusomer/{login}")
    @Produces("application/json")
    public List<Rent> getRentsByCustomer(@PathParam("login") String login) throws UserByLoginNotFound {
        return rentManager.getRentsByCustomer(login);
    }

    @GET
    @Path("/getById/{UUID}")
    @Produces("application/json")
    public Rent getRentById(@PathParam("UUID") UUID rentId) throws RentByIdNotFound {
        return rentManager.getRentById(rentId);
    }

    // DELETE
    @POST
    @Path("/delete/{UUID}")
    public void deleteRent(@PathParam("UUID") UUID rentId) throws RentByIdNotFound {
        rentManager.deleteRentFromRepo(rentId);
    }

    @PUT
    @Path("/end/{UUID}")
    public void endRent(@PathParam("UUID") UUID rentId, @QueryParam("date") String date) throws RentByIdNotFound{
        rentManager.endRent(date, rentId);
    }
}