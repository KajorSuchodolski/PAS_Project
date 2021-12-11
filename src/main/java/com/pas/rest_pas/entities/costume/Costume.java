package com.pas.rest_pas.entities.costume;

import com.pas.rest_pas.entities.Entity;

import javax.management.AttributeNotFoundException;
import java.util.Objects;

public class Costume extends Entity {

    private boolean isRented;
    private CostumeSize costumeSize;
    private ForWhom forWhom;
    private String name;

    public Costume(String name, CostumeSize costumeSize, ForWhom forWhom) throws AttributeNotFoundException {
        if(name.equals("")) {
            throw new AttributeNotFoundException("Name is invalid!");
        }
        this.isRented = false;
        this.costumeSize = costumeSize;
        this.name = name;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented( boolean rented ) {
        isRented = rented;
    }

    public CostumeSize getCostumeSize() {
        return costumeSize;
    }

    public void setCostumeSize( CostumeSize costumeSize ) {
        this.costumeSize = costumeSize;
    }

    public ForWhom getForWhom() {
        return forWhom;
    }

    public void setForWhom( ForWhom forWhom ) {
        this.forWhom = forWhom;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    @Override
    public boolean equals( Object o ) {
        if( this == o ) return true;
        if( !(o instanceof Costume) ) return false;
        if( !super.equals(o) ) return false;
        Costume costume = (Costume) o;
        return isRented == costume.isRented && costumeSize == costume.costumeSize && forWhom == costume.forWhom && Objects.equals(name, costume.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isRented, costumeSize, forWhom, name);
    }

    @Override
    public String toString() {
        return "Costume{" +
                "isRented=" + isRented +
                ", costumeSize=" + costumeSize +
                ", forWhom=" + forWhom +
                ", name='" + name + '\'' +
                '}';
    }
}