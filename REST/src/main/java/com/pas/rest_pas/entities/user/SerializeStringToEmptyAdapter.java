package com.pas.rest_pas.entities.user;

import javax.json.bind.adapter.JsonbAdapter;

public class SerializeStringToEmptyAdapter implements JsonbAdapter {
    @Override
    public Object adaptToJson(Object o) throws Exception {
        return null;
    }

    @Override
    public Object adaptFromJson(Object o) throws Exception {
        return null;
    }
}
