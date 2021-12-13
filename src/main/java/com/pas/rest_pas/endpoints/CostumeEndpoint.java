package com.pas.rest_pas.endpoints;

import com.pas.rest_pas.entities.costume.Costume;
import com.pas.rest_pas.exceptions.CostumeByIdNotFound;
import com.pas.rest_pas.managers.CostumeManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
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
    public List<Costume> searchAllCostumesByName(@PathParam("name") String name) {
        return costumeManager.searchAllCostumesByName(name) ;
    }

    @GET
    @Path("/getAllByParams/")
    @Produces("application/json")
    public List<Costume> getAllCostumesByParams(@QueryParam("age") String age, @QueryParam("size") String size) {
        return costumeManager.getAllCostumesByParams(age, size) ;
    }


    // CREATE
    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addCostume( Costume costume) {
        costumeManager.addCostume(costume);
        return Response.ok(Response.Status.OK)
                .entity("Costume added!")
                .build();
    }

    // UPDATE

    @PUT
    @Path("/update")
    @Produces("application/text")
    public Response updateCostume( Costume costume) throws CostumeByIdNotFound {
        costumeManager.updateCostume(costume);
        return Response.ok(Response.Status.OK)
                .entity("Costume updated successfully")
                .build();
    }

    @PUT
    @Path("/activate/{id}")
    @Produces("application/text")
    public Response activateRent(@PathParam("id") String id) {
        costumeManager.activateRent(UUID.fromString(id));
        return Response.ok(Response.Status.OK)
                .entity("Rent activated!")
                .build();
    }

    @PUT
    @Path("/deactivate/{id}")
    @Produces("application/text")
    public Response  deactivateRent(@PathParam("id") String id) {
        costumeManager.deactivateRent(UUID.fromString(id));
        return Response.ok(Response.Status.OK)
                .entity("Rent deactivated!")
                .build();
    }

    // DELETE
    @DELETE
    @Path("/delete/{id}")
    @Produces("application/text")
    public Response removeRent(@PathParam("id") String id) {
        costumeManager.removeCostume(UUID.fromString(id));
        return Response.ok(Response.Status.OK)
                .entity("Rent removed")
                .build();
    }




}
