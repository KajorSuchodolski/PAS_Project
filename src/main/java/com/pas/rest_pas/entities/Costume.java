package com.pas.rest_pas.entities;

import java.util.Objects;

public class Costume extends Entity {

    public enum Category {
        GAME, ANIME, MOVIE, ANIMAL;
    }

    private boolean isRented;
    private int size;
    private String name;
    private Category category;

    public Costume(boolean isRented, int size, String name, Category category) {
        this.isRented = isRented;
        this.size = size;
        this.name = name;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
