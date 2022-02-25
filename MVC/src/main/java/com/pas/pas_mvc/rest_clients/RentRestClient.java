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

    private Client client = ClientBuilder.newClient();
    private WebTarget target = client.target("https://localhost:8181/REST-1.0-SNAPSHOT/rest/rents");

    @Inject
    private LoginRestClient loginRestClient;

    public List<RentDTO> getAllRents() {

        return target.path("all").request(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + loginRestClient.getToken()).get(new GenericType<List<RentDTO>>() {});
    }

    public RentDTO getRentById( String id) {
        return target.path("getById/" + id).request(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + loginRestClient.getToken()).get(RentDTO.class);
    }

    public List<RentDTO> userCurrentRents(String login) {
        return target.path("userCurrentRents/" + login).request(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + loginRestClient.getToken()).get(new GenericType<List<RentDTO>>() {});
    }

    public List<RentDTO> userPastRents(String login) {
        return target.path("userPastRents/" + login).request(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + loginRestClient.getToken()).get(new GenericType<List<RentDTO>>() {});
    }

    public List<RentDTO> costumeRents(String id) {
        return target.path("getCostume/" + id).request(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + loginRestClient.getToken()).get(new GenericType<List<RentDTO>>() {});
    }

    // READ - GET CURRENT

    public List<RentDTO> getCurrentRents() {
        return target.path("allCurrent")
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .get(new GenericType<>(){});
    }


    // CREATE

    public void createRent( String login, List<UUID> costumes, String date) {
        target.path("add")
            .queryParam("login", login)
            .queryParam("date", date)
            .request(MediaType.APPLICATION_JSON).header("Authorization", "Bearer " + loginRestClient.getToken()).
            post(Entity.json(costumes));
    }

    // UPDATE - END

    public void endRent(String id, String date) {
        target.path("end")
                .queryParam("id", id)
                .queryParam("date", date)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .put(Entity.json(""));
    }

    public LoginRestClient getLoginRestClient() {
        return loginRestClient;
    }

    public void setLoginRestClient( LoginRestClient loginRestClient ) {
        this.loginRestClient = loginRestClient;
    }
}
