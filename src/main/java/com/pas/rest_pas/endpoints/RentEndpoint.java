package com.pas.rest_pas.endpoints;

import com.pas.rest_pas.entities.Rent;
import com.pas.rest_pas.entities.costume.Costume;
import com.pas.rest_pas.entities.user.User;
import com.pas.rest_pas.exceptions.*;
import com.pas.rest_pas.managers.RentManager;
import com.pas.rest_pas.managers.UserManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
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
    public Response addRent( @QueryParam("userLogin") String userLogin, @QueryParam("date") String date, List<UUID> costumeIds) throws CostumeInUseException, DateInPastException, WrongDateFormatException {
        rentManager.addRent(userLogin, costumeIds, date);
        return Response.ok(Response.Status.OK)
                .entity("Rent added successfully")
                .build();
    }

    // READ
    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Rent> getAll() {
        return rentManager.getAll();
    }


    @GET
    @Path("/getById/{UUID}")
    @Produces("application/json")
    public Rent getRentById(@PathParam("UUID") UUID rentId) throws RentByIdNotFound {
        return rentManager.getRentById(rentId);
    }

    // DELETE
    @DELETE
    @Path("/delete/{UUID}")
    public Response deleteRent(@PathParam("UUID") UUID rentId) throws RentByIdNotFound {
        rentManager.deleteRentFromRepo(rentId);
        return Response.ok(Response.Status.OK)
                .entity("Rent removed successfully")
                .build();
    }

    @PUT
    @Path("/end")
    public Response endRent(@QueryParam("UUID") UUID rentId, @QueryParam("date") String date) throws RentByIdNotFound{
        rentManager.endRent(date, rentId);
        return Response.ok(Response.Status.OK)
                .entity("Rent was ended successfully")
                .build();
    }
}
