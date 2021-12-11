package com.pas.rest_pas.endpoints;


import com.pas.rest_pas.entities.user.User;
import com.pas.rest_pas.exceptions.UserAdditionException;
import com.pas.rest_pas.managers.UserManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import java.util.UUID;

@Path("/clients")
public class UserEndpoint {

    @Inject
    private UserManager userManager;


    // READ
    @GET
    @Path("/all")
    @Produces("application/json")
    public List<User> getAll() {
        return userManager.getAll();
    }

    @GET
    @Path("/getByLogin/{login}")
    @Produces("application/json")
    public User getUserByLogin(@PathParam("login") String login) {
        return userManager.getUserByLogin(login) ;
    }

    @GET
    @Path("/getById/{id}")
    @Produces("application/json")
    public User getUserById( @PathParam("id") String uuid ) {
        return userManager.getUserById(UUID.fromString(uuid));
    }

    @GET
    @Path("/searchByLogin/{login}")
    @Produces("application/json")
    public List<User> searchUsersByLogin(@PathParam("login") String login) {
        return userManager.searchUsersByLogin(login) ;
    }

    // CREATE
    @POST
    @Path("/add")
    @Consumes("application/json")
    public void addUser( User user) throws UserAdditionException {
        userManager.addUser(user);
    }

    // UPDATE
    @PUT
    @Path("/update")
    @Produces("application/text")
    public String updateUser(User user) {
        userManager.updateUser(user);
        return "User updated successfully";
    }

    @PUT
    @Path("/activate/{login}")
    public void activateUser(@PathParam("login") String login) {
        userManager.activateUser(login);
    }

    @PUT
    @Path("/deactivate/{login}")
    public void deactivateUser(@PathParam("login") String login) {
        userManager.deactivateUser(login);
    }




}