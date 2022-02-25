/*package com.pas.rest_pas.jsonb.filters;


import com.pas.rest_pas.entities.Entity;
import com.pas.rest_pas.jsonb.verifier.SignerVerifier;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@FilterBindings

public class Filter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException{
        String header = requestContext.getHeaderString("If-Match");
        if (header == null || header.isEmpty()){
            requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).build());
        }//else if (!SignerVerifier.validateEntitySignature(header)) {
           // requestContext.abortWith(Response.status(Response.Status.PRECONDITION_FAILED).build());
       // }
    }
}*/
