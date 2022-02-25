package com.pas.pas_mvc.rest_clients;

import com.pas.pas_mvc.model.costume.CostumeDTO;
import org.apache.commons.logging.Log;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;

import static javax.ws.rs.core.Response.ok;

@SessionScoped
public class CostumeRestClient implements Serializable {



    private Client client = ClientBuilder.newClient();
    private WebTarget target = client.target("https://localhost:8181/REST-1.0-SNAPSHOT/rest/costumes/");


    @Inject
    private LoginRestClient loginRestClient;

    // READ
    public List<CostumeDTO> getAllCostumes() {
        return target.path("all")
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .get(new GenericType<>(){});
    }

    // CREATE
    public void postCostume(CostumeDTO newCostume) {
        target.path("add")
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .post(Entity.json(newCostume));
    }

    // DELETE
    public void deleteCostume(String idDeletion) {
        System.out.println("Wywolano delete costume");
        Response response = target.path("delete/" + idDeletion)
                .request()
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .delete();
        System.out.println("odpowiedz to: " + response);
    }

    // UPDATE

    public void updateCostume(CostumeDTO newCostume) {
        System.out.println("Wywolano update costume");
        Response res =  ok().entity(target.path("update/" + newCostume.getId().toString())
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .header("If-Match", newCostume.getEtag())
                .put(Entity.json(newCostume))).build();
        System.out.println("Odpowiedz to: " + res);
    }

    // READ - SEARCH BY NAME

    public List<CostumeDTO> searchCostumeByName(String searchName) {
        return target.path("searchByName/" + searchName)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .get(new GenericType<>(){});
    }

    // READ - GET BY ID (COSTUME)

    public CostumeDTO getCostumeById(String getId) {
        Response response = target.path("getById/" + getId)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .get();
       String etag = (String) response.getHeaders().getFirst("Etag");

       CostumeDTO costumeDTO = target.path("getById/" + getId)
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .get(CostumeDTO.class);
       costumeDTO.setEtag(etag);
        System.out.println(costumeDTO);
       return costumeDTO;
    }

    // READ - GET ALL AVAILABLE

    public List<CostumeDTO> getAllAvailableCostumes() {
        return target.path("getAllAvailable")
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + loginRestClient.getToken())
                .get(new GenericType<>(){});
    }

    public LoginRestClient getLoginRestClient() {
        return loginRestClient;
    }

    public void setLoginRestClient( LoginRestClient loginRestClient ) {
        this.loginRestClient = loginRestClient;
    }
}
