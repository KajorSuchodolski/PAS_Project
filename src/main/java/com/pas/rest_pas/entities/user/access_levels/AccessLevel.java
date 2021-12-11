package com.pas.rest_pas.entities.user.access_levels;

public abstract class AccessLevel {

    public enum AccessLevelType {
        USER, MANAGER, ADMINISTRATOR
    }

    public AccessLevel() {
    }

    @Override
    public String toString() {
        return "AccessLevel{}";
    }
}
