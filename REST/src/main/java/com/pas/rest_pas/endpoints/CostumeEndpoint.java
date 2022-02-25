package com.pas.rest_pas.endpoints;

import com.pas.rest_pas.entities.costume.Costume;
import com.pas.rest_pas.exceptions.EntityValidationException;
import com.pas.rest_pas.filter.SignatureVerifier;
import com.pas.rest_pas.managers.CostumeManager;
import com.pas.rest_pas.exceptions.CostumeByIdNotFound;
import com.pas.rest_pas.exceptions.CostumeCreationException;
import com.pas.rest_pas.exceptions.CostumeInUseException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/costumes")
public class CostumeEndpoint {

    @Inject
    private CostumeManager costumeManager;

    // READ
    @GET
    @Path("/all")
    @Produces("application/json")
    @RolesAllowed({"Admin", "Manager"})
    public Response getAll() {
        return Response.ok().entity(costumeManager.getAll()).build();
    }

    @GET
    @Path("/getAllRented")
    @Produces("application/json")
    @RolesAllowed({"Admin", "Manager", "Client"})
    public Response getAllRented() {
        return Response.ok().entity(costumeManager.getAllByRentStatus(true)).build();
    }

    @GET
    @Path("/getAllAvailable")
    @Produces("application/json")
    @RolesAllowed({"Admin", "Manager", "Client"})
    public Response getAllAvailable() {
        return Response.ok().entity(costumeManager.getAllByRentStatus(false)).build();
    }

    @GET
    @Path("/getAllByAge/{age}")
    @Produces("application/json")
    @RolesAllowed({"Admin", "Manager"})
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
    @RolesAllowed({"Admin", "Manager"})
    public Response getCostumeById( @PathParam("id") String uuid ) {
        if(uuid == null || uuid.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
        }
        try {
            Costume costumeFound = costumeManager.getCostumeById(UUID.fromString(uuid));
            return Response.ok(costumeFound).header("Etag", SignatureVerifier.calculateEntitySignature(costumeFound)).build();
        } catch(CostumeByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/searchByName/{name}")
    @Produces("application/json")
    @RolesAllowed({"Admin", "Manager"})
    public Response searchAllCostumesByName(@PathParam("name") String name) {
        if(name == null || name.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Name parameter is empty").build();
        }
        return Response.ok().entity(costumeManager.searchAllCostumesByName(name)).build();
    }

    @GET
    @Path("/getAllByParams")
    @Produces("application/json")
    @RolesAllowed({"Admin", "Manager"})
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
    @Produces("application/json")
    @Consumes("application/json")
    @RolesAllowed({"Admin", "Manager"})
    public Response addCostume( Costume costume) {
        if(costume == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Given costume is null").build();
        }
        try {
            costumeManager.addCostume(costume);
            return Response.ok(Response.Status.CREATED)
                    .entity("Costume added!")
                    .build();
        } catch(CostumeCreationException | EntityValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // UPDATE

    @PUT
    @Path("/update/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    @RolesAllowed({"Admin", "Manager"})
    public Response updateCostume( @PathParam("id") String id, Costume costume, @NotNull @NotEmpty @HeaderParam("If-Match") String etag) throws CostumeByIdNotFound {
        if(id == null || id.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
        }
        if(costume == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Given costume is null").build();
        }
        try {
            if (!SignatureVerifier.verifyEntityIntegrity(etag, costume)) {
                throw new IllegalArgumentException("Trying to modify the wrong costume!");
            }
            costumeManager.updateCostume(UUID.fromString(id), costume);
            return Response.ok(Response.Status.OK)
                    .entity("Costume updated successfully")
                    .build();
        } catch(CostumeByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(EntityValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch(IllegalArgumentException e) {
            System.out.println("HAHAHHAHA");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/activate/{id}")
    @Produces("application/json")
    @RolesAllowed({"Admin", "Manager"})
    public Response activateRent(@PathParam("id") String id) {
        if(id == null || id.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
        }
        try {
            costumeManager.activateRent(UUID.fromString(id));
            return Response.ok(Response.Status.OK)
                    .entity("Rent activated")
                    .build();
        } catch(CostumeByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/deactivate/{id}")
    @Produces("application/json")
    @RolesAllowed({"Admin", "Manager"})
    public Response deactivateRent(@PathParam("id") String id) {
        if(id == null || id.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
        }
        try {
            costumeManager.deactivateRent(UUID.fromString(id));
            return Response.ok(Response.Status.OK)
                    .entity("Rent activated")
                    .build();
        } catch(CostumeByIdNotFound e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // DELETE
    @DELETE
    @Path("/delete/{id}")
    @Produces("application/json")
    @RolesAllowed({"Admin", "Manager"})
    public Response removeRent(@PathParam("id") String id) {
        if(id == null || id.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty").build();
        }
        try {
            costumeManager.removeCostume(UUID.fromString(id));
            return Response.ok(Response.Status.OK)
                    .entity("Rent removed")
                    .build();
        } catch(CostumeByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch(CostumeInUseException e) {
            return Response.status(Response.Status.FORBIDDEN).entity(e.getMessage()).build();
        }
    }




}
