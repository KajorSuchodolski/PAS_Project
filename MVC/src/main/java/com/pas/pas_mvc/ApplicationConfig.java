package com.pas.pas_mvc;

import javax.faces.annotation.FacesConfig;
import javax.faces.bean.ApplicationScoped;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@FacesConfig
@ApplicationScoped
@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/index.xhtml",
                errorPage = "/auth/error.xhtml",
                useForwardToLogin = false
        )
)
public class ApplicationConfig {
}
