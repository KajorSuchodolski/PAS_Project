package com.pas.pas_mvc.rest_clients;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pas.pas_mvc.beans.login.UserStatusBean;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.json.Json;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;

@SessionScoped
public class LoginRestClient implements Serializable {

    @Inject
    private UserStatusBean userStatusBean;

    @Inject
    private HttpServletRequest request;


    private WebTarget getTarget() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("https://localhost:8181/REST-1.0-SNAPSHOT/rest");
        return target.path("authenticate");
    }

    private String token;

    public CredentialValidationResult getLogin(String login, String password) throws Exception {

        System.out.println("OwO");
        Response response = getTarget().request().post(Entity.json(Json.createObjectBuilder().add("login", login)
                .add("password", password).build()));
        if (response.getStatus() == 202) {
            token = response.readEntity(String.class);
            DecodedJWT jwt = JWT.decode(token);
            List<String> groups = jwt.getClaim("groups").asList(String.class);
            String role = groups.get(0);
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("Role", role);
            session.setAttribute("Login", jwt.getClaims().get("sub").toString().replace("\"", ""));

//           System.out.println(context.getRequest().toString());
//           userStatusBean.setRole(role);
// userStatusBean.setLogin(jwt.getClaims().get("sub").toString().replace(""", ""));
//           userStatusBean.setAdmin(role.equals("Admin"));
//           userStatusBean.setManager(role.equals("Manager"));
//           userStatusBean.setClient(role.equals("Client"));
//           userStatusBean.setLoggedIn(true);

            CredentialValidationResult result = new CredentialValidationResult(login, new HashSet<>(groups));
//           context.authenticate(servletRequest, servletResponse, new AuthenticationParameters(login, new HashSet<>(groups)));
//           context.notifyContainerAboutLogin(result);
            System.out.println(result.getStatus());
            return result;

        } else if(response.getStatus() == 401) {
            throw new Exception("Login and password does not match");
        } else {
            throw new Exception("Something went wrong");
        }
    }

    public void signOut() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();

    }

    public void changePassword(String newPassword) throws ForbiddenException {
        getTarget().path(userStatusBean.getLogin() + "/changePassword")
                .request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + token)
                .post(Entity.json(Json.createObjectBuilder()
                        .add("password", newPassword).build()));

    }

    public String getBearerToken() { return "Bearer " + token;}

    public String getToken() {
        return token;
    }

    public void setToken( String token ) {
        this.token = token;
    }

    public UserStatusBean getUserStatusBean() {
        return userStatusBean;
    }

    public void setUserStatusBean( UserStatusBean userStatusBean ) {
        this.userStatusBean = userStatusBean;
    }
}
