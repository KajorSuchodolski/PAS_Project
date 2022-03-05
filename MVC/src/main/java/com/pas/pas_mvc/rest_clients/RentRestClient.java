package com.pas.pas_mvc.rest_clients;

import com.pas.pas_mvc.model.RentDTO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@SessionScoped
public class RentRestClient implements Serializable {

    @Inject
    private LoginRestClient loginRestClient;

    private WebTarget getTarget() {
        Client client = ClientBuilder.newClient();
        return client.target("https://localhost:8181/REST-1.0-SNAPSHOT/rents");
    }

    public List<RentDTO> getAllRents() {

        return getTarget().request(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + loginRestClient.getToken()).get(new GenericType<List<RentDTO>>() {});
    }

    public RentDTO getRentById( String id) {
        return getTarget().path(id).request(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + loginRestClient.getToken()).get(RentDTO.class);
    }

    public List<RentDTO> userCurrentRents(String login) {
        return getTarget().path(login + "/current-rents").request(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + loginRestClient.getToken()).get(new GenericType<List<RentDTO>>() {});
    }

    public List<RentDTO> userPastRents(String login) {
        return getTarget().path(login + "/past-rents").request(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + loginRestClient.getToken()).get(new GenericType<List<RentDTO>>() {});
    }

    public List<RentDTO> costumeRents(String id) {
        return getTarget().path("/costume-allocations").queryParam("id", id).request(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + loginRestClient.getToken()).get(new GenericType<List<RentDTO>>() {});
    }

    // READ - GET CURRENT

    public List<RentDTO> getCurrentRents() {
        return getTarget().path("all-current-rents")
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .get(new GenericType<>(){});
    }


    // CREATE

    public void createRent( String login, List<UUID> costumes, String date) {
        getTarget()
            .queryParam("login", login)
            .queryParam("date", date)
            .request(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + loginRestClient.getToken()).
            post(Entity.json(costumes));
    }

    // UPDATE - END

    public void endRent(String id, String date) {
        getTarget().path(id + "/end")
                .queryParam("date", date)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .put(Entity.json(""));
    }
}
