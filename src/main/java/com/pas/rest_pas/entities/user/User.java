package com.pas.rest_pas.entities.user;

import com.pas.rest_pas.entities.Entity;
import com.pas.rest_pas.entities.user.access_levels.AccessLevel;
import com.pas.rest_pas.exceptions.EntityCreationException;

import java.util.Objects;


public class User extends Entity {

    private boolean isActive = true;

    private AccessLevel accessLevel;

    private String firstName;

    private String lastName;

    private Address address;

    private String login;

    private String email;

    private String password;

    public User() {

    }

    public User(String firstName, String lastName, String login, String password, String email) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;

    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive( boolean active ) {
        isActive = active;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel( AccessLevel accessLevel ) {
        this.accessLevel = accessLevel;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress( Address address ) {
        this.address = address;
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

    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    @Override
    public boolean equals( Object o ) {
        if( this == o ) return true;
        if( !(o instanceof User) ) return false;
        if( !super.equals(o) ) return false;
        User user = (User) o;
        return isActive == user.isActive && Objects.equals(accessLevel, user.accessLevel) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(address, user.address) && Objects.equals(login, user.login) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isActive, accessLevel, firstName, lastName, address, login, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "isActive=" + isActive +
                ", accessLevel=" + accessLevel +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address=" + address +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
