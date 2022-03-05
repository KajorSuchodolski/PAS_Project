package com.pas.pas_mvc.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pas.pas_mvc.beans.login.UserStatusBean;
import com.pas.pas_mvc.rest_clients.LoginRestClient;
import org.glassfish.soteria.SecurityContextImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

@ApplicationScoped
public class JwtIdentityStore implements IdentityStore {

    @Inject
    private LoginRestClient loginRestClient;

    @Inject
    private UserStatusBean userStatusBean;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        System.out.println("Wywolano validate");
        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential upCredential = (UsernamePasswordCredential) credential;

            String login = upCredential.getCaller();
            String password = upCredential.getPasswordAsString();
            System.out.println(login);
            System.out.println(password);

            try {
                return loginRestClient.getLogin(login, password);

            } catch (Exception e) {
                System.out.println("Bad request " + e.getMessage());
                return CredentialValidationResult.INVALID_RESULT;
            }
        }

        return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }


}
