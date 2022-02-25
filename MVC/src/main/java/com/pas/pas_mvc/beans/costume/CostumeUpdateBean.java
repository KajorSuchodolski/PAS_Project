package com.pas.pas_mvc.beans.costume;

import com.pas.pas_mvc.model.costume.CostumeDTO;
import com.pas.pas_mvc.rest_clients.CostumeRestClient;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.UUID;

@Named
@SessionScoped
public class CostumeUpdateBean implements Serializable {

    @Inject
    private CostumeRestClient costumeRestClient;

    @Inject
    private CostumeListBean costumeListBean;

    private String id;

    private CostumeDTO updatedCostume;


    public String updateCostume() {
        System.out.println("Wywolano costume beana update");
        System.out.println("Costume wyglada tak" + updatedCostume.toString());


        updatedCostume.setId(UUID.fromString(id));
        costumeRestClient.updateCostume(updatedCostume);
        updatedCostume = new CostumeDTO();
        costumeListBean.init();
        return "goBack";

    }

    public CostumeRestClient getCostumeRestClient() {
        return costumeRestClient;
    }

    public void setCostumeRestClient( CostumeRestClient costumeRestClient ) {
        this.costumeRestClient = costumeRestClient;
    }
    public CostumeDTO getUpdatedCostume() {
        return updatedCostume;
    }

    public void setUpdatedCostume( CostumeDTO updatedCostume ) {
        this.updatedCostume = updatedCostume;
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }
}
