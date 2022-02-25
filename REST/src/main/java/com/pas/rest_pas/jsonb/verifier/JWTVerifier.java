/*package com.pas.rest_pas.jsonb.verifier;

import com.auth0.jwt.JWT;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.auth0.jwt.algorithms.Algorithm;

import javax.security.enterprise.identitystore.CredentialValidationResult;


public class JWTVerifier {
    private static final String SECRET = "L9IYdCLMFgLqYE8faIaLgBtJqqPrTsC0ikVv2hpytxcBx6n5uPskxXthucRLlapOuDVFdjPKDQoxjSmbVKK_yMH16qwyd1UT9Y3qHx7KHDYZIE7M8m_nI2-7nFRD-Dg1suKY0OKfBL2kuwYeLjwo7ZgveyJKdiOKjpLLQ9DuGU1oHF-L3T0Q56IqnuRZXEVzQFWPXDQGzCnYHkvaAqLiCNAC_8aB1GaKeft-a1H5mngJLc38opWx7qXdGrLfqf-V-58UExGg_OekBmw_gsAUwr0_jv83wPAmfkRIImAc52oEylPK5z0C6Ge6Bvzxl25o3PfmG6LSiDTm3bZ0j6BcLw";
    public static String generateJWTString(CredentialValidationResult credential) {
        Algorithm algo = Algorithm.HMAC256(SECRET);
        String token = JWT.create().withClaim("auth",String.join(",", credential.getCallerGroups()))
                .withSubject(credential.getCallerPrincipal().getName()).sign(algo);
        return token;

    }*/
   /*     try{
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(credential.getCallerPrincipal().getName()).
                            claim("auth",String.join(",", credential.getCallerGroups())).build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claims);

            signedJWT.sign(new MACSigner(SECRET));

            return signedJWT.serialize();
        }catch (JOSEException e) {
            e.printStackTrace();
            return "JWT failure";
        }

    }*/





//}
