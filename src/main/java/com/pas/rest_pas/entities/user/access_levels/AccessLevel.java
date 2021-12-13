package com.pas.rest_pas.entities.user.access_levels;

public abstract class AccessLevel {
    
    private AccessLevelType accessLevelType;

    public AccessLevel(AccessLevelType accessLevelType) {
        this.accessLevelType = accessLevelType;
    }

    public AccessLevelType getAccessLevelType() {
        return accessLevelType;
    }

    public void setAccessLevelType( AccessLevelType accessLevelType ) {
        this.accessLevelType = accessLevelType;
    }

    @Override
    public String toString() {
        return "AccessLevel{}";
    }
}
