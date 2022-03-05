package com.pas.pas_mvc.rest_clients;

import com.pas.pas_mvc.model.user.UserDTO;
import com.pas.pas_mvc.model.user.access_levels.AccessLevelType;
import com.pas.pas_mvc.model.user.access_levels.AdministatorLevelDTO;
import com.pas.pas_mvc.model.user.access_levels.ClientLevelDTO;
import com.pas.pas_mvc.model.user.access_levels.ManagerLevelDTO;
//import com.pas.rest_pas.entities.user.access_levels.Administrator;
//import com.pas.rest_pas.entities.user.access_levels.Manager;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

@SessionScoped
public class UserRestClient implements Serializable {

    @Inject
    private LoginRestClient loginRestClient;

    private WebTarget getTarget() {
       Client client = ClientBuilder.newClient();
       return client.target("https://localhost:8181/REST-1.0-SNAPSHOT/user");

    }


    // CREATE
    public void postUser(UserDTO newUser) {
         getTarget()
                .request(MediaType.APPLICATION_JSON)
                 .header("Authorization", "Bearer " + loginRestClient.getToken())
                .post(Entity.json(newUser));
    }

    // READ
    public List<UserDTO> getAllUsers() {
        return getTarget()
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .get(new GenericType<>(){});
    }

    // READ - SEARCH BY LOGIN (LIST)

    public List<UserDTO> searchUserByLogin(String login) {
        return getTarget().path("/search-by-login")
                .queryParam("login", login)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .get(new GenericType<>(){});
    }


    // READ - GET BY LOGIN (USER)

    public UserDTO getUserByLogin(String login) {
        Response response = getTarget().path("get-by-login")
                .queryParam("login", login)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .get();
        String etag = (String) response.getHeaders().getFirst("Etag");

        UserDTO userDTO = getTarget().path("get-by-login")
                .queryParam("login", login)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .get(UserDTO.class);
        userDTO.setEtag(etag);
        return userDTO;
    }

    // READ - GET BY ID (USER)

    public UserDTO getUserById(String id) {
        return getTarget().path("get-by-id")
                .queryParam("login", id)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .get(UserDTO.class);
    }

    // UPDATE

    public void updateUser(UserDTO newUser) {
        switch(loginRestClient.getUserStatusBean().getRole()) {
            case "Admin" -> newUser.setAccessLevel("Admin");
            case "Manager" -> newUser.setAccessLevel("Manager");
            default -> newUser.setAccessLevel("Client");
        }
        getTarget().path(newUser.getLogin() + "/update")
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .header("If-Match", newUser.getEtag())
                .put(Entity.json(newUser));

    }

    // UPDATE - ACTIVATE

    public void activateUser(String login) {
         getTarget().path(login + "/activate")
                .request(MediaType.APPLICATION_JSON)
                 .header("Authorization", "Bearer " + loginRestClient.getToken())
                .put(Entity.json(""));
    }

    // UPDATE - DEACTIVATE

    public void deactivateUser(String login) {
        getTarget().path(login + "/deactivate")
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .put(Entity.json(""));
    }
}
