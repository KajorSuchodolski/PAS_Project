package com.pas.rest_pas.endpoints;

import com.pas.rest_pas.entities.Rent;
import com.pas.rest_pas.entities.costume.Costume;
import com.pas.rest_pas.entities.user.User;
import com.pas.rest_pas.managers.RentManager;
import com.pas.rest_pas.managers.UserManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/rents")
public class RentEndpoint {

    @Inject
    private RentManager rentManager;

    // READ
    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Rent> getAll() {
        return rentManager.getAll();
    }





}
