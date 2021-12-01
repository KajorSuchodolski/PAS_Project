package com.pas.rest_pas.entities;

import java.util.Objects;

public class Costume extends Entity {

    /*
    *
    * Napisac przezajebista klase costume (dodac atrybuty potrzebne
    * do tego)
    *
    * */
    private boolean isRented;
    private int size;
    private String name;

    public Costume( boolean isRented, int size, String name ) {
        this.isRented = isRented;
        this.size = size;
        this.name = name;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented( boolean rented ) {
        isRented = rented;
    }

    public int getSize() {
        return size;
    }

    public void setSize( int size ) {
        this.size = size;
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
        return isRented == costume.isRented && size == costume.size && Objects.equals(name, costume.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isRented, size, name);
    }

    @Override
    public String toString() {
        return "Costume{" +
                "isRented=" + isRented +
                ", size=" + size +
                ", name='" + name + '\'' +
                '}';
    }
}
