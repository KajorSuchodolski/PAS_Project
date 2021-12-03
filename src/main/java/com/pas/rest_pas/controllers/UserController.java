package com.pas.rest_pas.controllers;


import com.pas.rest_pas.entities.user.User;
import com.pas.rest_pas.managers.UserManager;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;

@Path("/clients")
public class UserController {

    @Inject
    private UserManager userManager;

    @GET
    @Produces("application/json")
    @Path("/find/{login}")
    public User getUserByLogin( @PathParam("login") String login) {
        return userManager.getUserByLogin("login");
    }
}
