package com.pas.pas_mvc.beans.login;

import com.pas.pas_mvc.rest_clients.LoginRestClient;
import com.pas.pas_mvc.security.JwtIdentityStore;
import org.glassfish.soteria.SecurityContextImpl;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.Context;
import java.io.Serializable;
import java.net.SocketTimeoutException;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import static javax.security.auth.message.AuthStatus.SEND_CONTINUE;
import static javax.security.auth.message.AuthStatus.SEND_FAILURE;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

@SessionScoped
@Named
public class LoginBean implements Serializable {

    @Inject
    private SecurityContext securityContext;

    @Inject
    private LoginRestClient loginRestClient;

//    @Inject
//    private HttpServletRequest request;
//
//    @Context
//    private HttpServletResponse response;

    private String login;
    private String password;

    public LoginBean() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin( String login ) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public LoginRestClient getLoginRestClient() {
        return loginRestClient;
    }


    public boolean signIn() {
        try {
//            request.login(login, password);

//        Credential credential = new UsernamePasswordCredential(
//            login, new Password(password));
//            FacesContext context = FacesContext.getCurrentInstance();
//        AuthenticationStatus status = securityContext.authenticate(
//            getRequest(context), getResponse(context), withParams().credential(credential));

//            System.out.println(status.toString());
            loginRestClient.getLogin(login, password);
//            System.out.println(securityContext.getCallerPrincipal());

//            if (status.equals(SEND_CONTINUE)) {
//                // Authentication mechanism has send a redirect, should not
//                // send anything to response from JSF now.
//                context.responseComplete();
//            } else if (status.equals(SEND_FAILURE)) {
//                addError(context, "Authentication failed");
//            }
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String signOut() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            getRequest(context).logout();
            loginRestClient.signOut();
            return "home"; }
        catch (ServletException e) {
            e.printStackTrace();
            return "";
        }

    }

    private static HttpServletResponse getResponse(FacesContext context) {
        return (HttpServletResponse) context.getExternalContext().getResponse();
    }

    private static HttpServletRequest getRequest(FacesContext context) {
        return (HttpServletRequest) context.getExternalContext().getRequest();
    }

    private static void addError(FacesContext context, String message) {
        context.addMessage(null, new FacesMessage(SEVERITY_ERROR, message, null));
    }

}
