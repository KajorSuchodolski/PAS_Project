package com.pas.rest_pas.endpoints;

import com.pas.rest_pas.entities.costume.Costume;
import com.pas.rest_pas.exceptions.CostumeByIdNotFound;
import com.pas.rest_pas.exceptions.EntityValidationException;
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
    public Response getAll() {
        return Response.ok().entity(costumeManager.getAll()).build();
    }

    @GET
    @Path("/getAllRented")
    @Produces("application/json")
    public Response getAllRented() {
        return Response.ok().entity(costumeManager.getAllByRentStatus(true)).build();
    }

    @GET
    @Path("/getAllAvailable")
    @Produces("application/json")
    public Response getAllAvailable() {
        return Response.ok().entity(costumeManager.getAllByRentStatus(false)).build();
    }

    @GET
    @Path("/getAllByAge/{age}")
    @Produces("application/json")
    public Response getAllCostumesByAge( @PathParam("age") String age ) {
        if(age == null || age.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Age parameter is empty").build();
        }
        try {
            return Response.ok().entity(costumeManager.getAllCostumesByAge(age)).build();
        } catch(EntityValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/getById/{id}")
    @Produces("application/json")
    public Response getCostumeById( @PathParam("id") String uuid ) {
        if(uuid == null || uuid.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
        }
        try {
            return Response.ok().entity(costumeManager.getCostumeById(UUID.fromString(uuid))).build();
        } catch(CostumeByIdNotFound e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/searchByName/{name}")
    @Produces("application/json")
    public Response searchAllCostumesByName(@PathParam("name") String name) {
        if(name == null || name.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Name parameter is empty").build();
        }
        return Response.ok().entity(costumeManager.searchAllCostumesByName(name)).build();
    }

    @GET
    @Path("/getAllByParams")
    @Produces("application/json")
    public Response getAllCostumesByParams(@QueryParam("age") String age, @QueryParam("size") String size) {
        if(age == null || age.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Age parameter is empty").build();
        }
        if(size == null || size.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Size parameter is empty").build();
        }
        try {
            return Response.ok().entity(costumeManager.getAllCostumesByParams(age, size)).build();
        } catch(EntityValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


    // CREATE
    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addCostume( Costume costume) {
        costumeManager.addCostume(costume);
        return Response.ok(Response.Status.CREATED)
                .entity("Costume added!")
                .build();
    }

    // UPDATE

    @PUT
    @Path("/update/{id}")
    @Consumes("application/json")
    public Response updateCostume(@PathParam("id") String id, Costume costume) throws CostumeByIdNotFound {
        if(id == null || id.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
        }
        if(costume == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Given costume is null").build();
        }
        try {
            costumeManager.updateCostume(UUID.fromString(id), costume);
            return Response.ok(Response.Status.OK)
                    .entity("Costume updated successfully")
                    .build();
        } catch(CostumeByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(EntityValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/activate/{id}")
    public Response activateRent(@PathParam("id") String id) {
        costumeManager.activateRent(UUID.fromString(id));
        return Response.ok(Response.Status.OK)
                .entity("Rent activated!")
                .build();
    }

    @PUT
    @Path("/deactivate/{id}")
    public Response  deactivateRent(@PathParam("id") String id) {
        costumeManager.deactivateRent(UUID.fromString(id));
        return Response.ok(Response.Status.OK)
                .entity("Rent deactivated!")
                .build();
    }

    // DELETE
    @DELETE
    @Path("/delete/{id}")
    public Response removeRent(@PathParam("id") String id) {
        costumeManager.removeCostume(UUID.fromString(id));
        return Response.ok(Response.Status.OK)
                .entity("Rent removed")
                .build();
    }




}
