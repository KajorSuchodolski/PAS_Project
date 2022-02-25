package com.pas.pas_mvc.beans;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

@SessionScoped
@Named
public class ChangeLanguageBean implements Serializable {

    private Locale locale;

    public ChangeLanguageBean() {

    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale( Locale locale ) {
        this.locale = locale;
    }

    public void changeLanguage( String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);

    }
}
