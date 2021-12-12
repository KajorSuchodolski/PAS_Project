package com.pas.rest_pas.endpoints;

import com.pas.rest_pas.entities.costume.Costume;
import com.pas.rest_pas.exceptions.CostumeByIdNotFound;
import com.pas.rest_pas.managers.CostumeManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import java.util.UUID;

@Path("/costumes")
public class CostumeEndpoint {

    @Inject
    private CostumeManager costumeManager;

    // READ
    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Costume> getAll() {
        return costumeManager.getAll();
    }

    @GET
    @Path("/getAllByAge/{age}")
    @Produces("application/json")
    public List<Costume> getAllCostumesByAge( @PathParam("age") String age ) {
        return costumeManager.getAllCostumesByAge(age);
    }

    @GET
    @Path("/getById/{id}")
    @Produces("application/json")
    public Costume getCostumeById( @PathParam("id") String uuid ) {
        return costumeManager.getCostumeById(UUID.fromString(uuid));
    }

    @GET
    @Path("/searchByName/{name}")
    @Produces("application/json")
    public List<Costume> search(@PathParam("name") String name) {
        return costumeManager.searchAllCostumesByName(name) ;
    }

    @GET
    @Path("/searchByName/{age}/{size}")
    @Produces("application/json")
    public List<Costume> getAllCostumesByParams(@PathParam("age") String age, @PathParam("size") String size) {
        return costumeManager.getAllCostumesByParams(age, size) ;
    }



    // CREATE
    @POST
    @Path("/add")
    @Consumes("application/json")
    public void addCostume( Costume costume) {
        costumeManager.addCostume(costume);
    }

    // UPDATE

    @PUT
    @Path("/update")
    @Produces("application/text")
    public String updateCostume(Costume costume) throws CostumeByIdNotFound {
        costumeManager.updateCostume(costume);
        return "Costume updated successfully";
    }

    @PUT
    @Path("/activate/{id}")
    @Produces("application/text")
    public String activateRent(@PathParam("id") String id) {
        costumeManager.activateRent(UUID.fromString(id));
        return "Rent activated!";
    }

    @PUT
    @Path("/deactivate/{id}")
    @Produces("application/text")
    public String deactivateRent(@PathParam("id") String id) {
        costumeManager.deactivateRent(UUID.fromString(id));
        return "Rent deactivated!";
    }

    // DELETE
    @DELETE
    @Path("/delete/{id}")
    @Produces("application/text")
    public String removeRent(@PathParam("id") String id) {
        costumeManager.removeCostume(UUID.fromString(id));
        return "Rent removed!";
    }




}
