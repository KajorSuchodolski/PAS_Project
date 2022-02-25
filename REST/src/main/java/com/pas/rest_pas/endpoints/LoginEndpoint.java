package com.pas.rest_pas.endpoints;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.pas.rest_pas.entities.user.User;
import com.pas.rest_pas.exceptions.UserByIdNotFound;
import com.pas.rest_pas.exceptions.UserByLoginNotFound;
import com.pas.rest_pas.exceptions.UserUpdateException;
import com.pas.rest_pas.managers.UserManager;
import com.pas.rest_pas.security.InMemoryIdentityStore;
import com.pas.rest_pas.security.JWTMechanism;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;


@Path("authenticate")
public class LoginEndpoint {

    public LoginEndpoint() {
    }

    @Inject
    private InMemoryIdentityStore identityStoreHandler;

    @Inject
    private UserManager userManager;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces
    public Response authenticate(@NotNull String body) {
        try {
            JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
            String login = jsonObject.get("login").getAsString();
            System.out.println(login);
            String password = jsonObject.get("password").getAsString();
            System.out.println(password);
            Credential credential = new UsernamePasswordCredential(login, password);
            CredentialValidationResult result = identityStoreHandler.validate(credential);
            if (result.getStatus() == CredentialValidationResult.Status.VALID) {
                return Response.accepted()
                        .type("application/jwt")
                        .entity(JWTMechanism.generateJWT(result))
                        .build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (JsonSyntaxException | NullPointerException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid json name").build();
        }
    }

    @POST
    @Path("{userID}/changePassword")
    @RolesAllowed({"Admin", "Manager", "Client"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeUserPassword( @PathParam("userID") String login, String body) {
        JsonObject jsonBody = JsonParser.parseString(body).getAsJsonObject();
        String newPassword = jsonBody.get("password").getAsString();
        System.out.println("Trying to change user password to " + newPassword);

        try {
            userManager.updateUserPassword(login, newPassword);
            return Response.ok().build();
        } catch (UserByLoginNotFound | UserUpdateException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e).build();
        }
    }

}
