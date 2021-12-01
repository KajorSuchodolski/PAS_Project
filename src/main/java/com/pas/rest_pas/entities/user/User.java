package com.pas.rest_pas.entities.user;

import com.pas.rest_pas.entities.Entity;
import com.pas.rest_pas.entities.access_levels.AccessLevel;

import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

public class User extends Entity {

    private boolean isActive = true;

    private AccessLevel accessLevel;


    private String firstName;

    private String lastName;

    private String Address;

    @Min(value = 9, message = "Phone number should not be less than 9!")
    @Max(value = 9, message = "Phone number should not be greater than 9!")
    private int phoneNumber;

    private String login;

    @Email(message = "Email is invalid!")
    private String email;

    private String password;

    public User( AccessLevel accessLevel, String firstName, String lastName, int phoneNumber, String login, String password, String email ) {
        this.accessLevel = accessLevel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress( String address ) {
        Address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber( int phoneNumber ) {
        this.phoneNumber = phoneNumber;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin( String login ) {
        this.login = login;
    }

    // UMMM wtf ?!
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
        return isActive == user.isActive && Objects.equals(accessLevel, user.accessLevel) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(Address, user.Address) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(login, user.login) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isActive, accessLevel, firstName, lastName, Address, phoneNumber, login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "isActive=" + isActive +
                ", accessLevel=" + accessLevel +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", Address='" + Address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
