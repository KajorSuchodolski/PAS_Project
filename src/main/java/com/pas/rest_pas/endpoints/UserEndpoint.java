package com.pas.rest_pas.endpoints;


import com.pas.rest_pas.entities.user.User;
import com.pas.rest_pas.exceptions.*;
import com.pas.rest_pas.managers.UserManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/clients")
public class UserEndpoint {

    @Inject
    private UserManager userManager;

    // READ
    @GET
    @Path("/all")
    @Produces("application/json")
    public Response getAll() {
        return Response.ok().entity(userManager.getAll()).build();
    }

    @GET
    @Path("/getByLogin/{login}")
    @Produces("application/json")
    public Response getUserByLogin(@PathParam("login") String login) {
        if(login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty!").build();
        }
        try {
            return Response.ok().entity(userManager.getUserByLogin(login)).build();
        } catch(UserByLoginNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }

    }

    @GET
    @Path("/getById/{id}")
    @Produces("application/json")
    public Response getUserById( @PathParam("id") String uuid ) {
        if(uuid == null || uuid.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Id parameter is empty!").build();
        }
        try {
            return Response.ok().entity(userManager.getUserById(UUID.fromString(uuid))).build();
        } catch(UserByIdNotFound e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/searchByLogin/{login}")
    @Produces("application/json")
    public Response searchUsersByLogin(@PathParam("login") String login) {
        if(login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty!").build();
        }
        return Response.ok().entity(userManager.searchUsersByLogin(login)).build();
    }

    // CREATE
    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addUser( User user) {
        if(user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Given user is null").build();
        }
        try {
            userManager.addUser(user);
            return Response.ok(Response.Status.CREATED)
                    .entity("User has been added")
                    .build();
        } catch(UserCreationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }

    // UPDATE
    @PUT
    @Path("/update/{login}")
    @Produces("application/json")
    public Response updateUser(@PathParam("login") String login, User user) {
        if(login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty").build();
        }
        if(user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Given user is null").build();
        }
        try {
            userManager.updateUser(login, user);
            return Response.ok(Response.Status.OK)
                    .entity("User updated successfully")
                    .build();
        } catch(UserUpdateException | EntityValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/activate/{login}")
    @Produces("application/json")
    public Response activateUser(@PathParam("login") String login) {
        if(login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty").build();
        }
        try {
            userManager.activateUser(login);
            return Response.ok(Response.Status.OK)
                    .entity("User activated")
                    .build();
        } catch(UserByLoginNotFound e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/deactivate/{login}")
    @Produces("application/json")
    public Response deactivateUser(@PathParam("login") String login) {
        if(login == null || login.trim().equals("")) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Login parameter is empty").build();
        }
        try {
            userManager.deactivateUser(login);
            return Response.ok(Response.Status.OK)
                    .entity("User activated")
                    .build();
        } catch(UserByLoginNotFound e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }




}
