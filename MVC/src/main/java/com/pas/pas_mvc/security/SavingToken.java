//
//package com.pas.pas_mvc.security;
//
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.nimbusds.jwt.SignedJWT;
//import com.pas.pas_mvc.rest_clients.LoginRestClient;
//
//import javax.enterprise.context.ApplicationScoped;
//import javax.inject.Inject;
//import javax.security.enterprise.AuthenticationException;
//import javax.security.enterprise.AuthenticationStatus;
//import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
//import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.text.ParseException;
//import java.util.Arrays;
//import java.util.HashSet;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class SavingToken implements HttpAuthenticationMechanism {
//
//    @Inject
//    LoginRestClient loginRestClient;
//
//
//    @Override
//    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext context) {
//
//        if (request.getRequestURL().toString().endsWith("authenticate") || request.getRequestURL().toString().endsWith("/error.xhtml")) {
//            return context.doNothing();
//        }
//
//        try {
//            String token = loginRestClient.getToken();
//            SignedJWT jwt = SignedJWT.parse(token);
//            String login = jwt.getJWTClaimsSet().getSubject();
//            String groups = jwt.getJWTClaimsSet().getStringClaim("role");
//            System.out.println("ClaimSet: " + jwt.getJWTClaimsSet().getClaims().toString());
//
//            return context.notifyContainerAboutLogin(login, new HashSet<>(Arrays.asList(groups.split(","))));
//        } catch (JWTVerificationException | ParseException e) {
//            e.printStackTrace();
//            return context.responseUnauthorized();
//        }
//    }
//
//    @Override
//    public AuthenticationStatus secureResponse(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
//        return HttpAuthenticationMechanism.super.secureResponse(request, response, httpMessageContext);
//    }
//
//    @Override
//    public void cleanSubject(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) {
//        HttpAuthenticationMechanism.super.cleanSubject(request, response, httpMessageContext);
//    }
//}
