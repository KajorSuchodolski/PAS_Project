//package com.pas.rest_pas.jsonb.adapters;
//
//import com.pas.rest_pas.entities.user.User;
//import javax.json.Json;
//import javax.json.JsonObject;
//import javax.json.bind.adapter.*;
//
//
//
//
//
//public class passwordAdapter implements JsonbAdapter<User, JsonObject> {
//
//    @Override
//    public JsonObject adaptToJson(User u) throws Exception {
//        return Json.createObjectBuilder()
//                .add("first_name", u.getFirstName())
//                .add("name", u.getLastName())
//                .add("login", u.getLogin())
//                .add("email", u.getEmail())
//                .build();
//    }
//
//    @Override
//    public User adaptFromJson(JsonObject adapted) throws Exception {
//        User user = new User();
//        user.setFirstName(adapted.getString("first_name"));
//        user.setLastName(adapted.getString("last_name"));
//        user.setLogin(adapted.getString("login"));
//        user.setEmail(adapted.getString("email"));
//        user.setPassword(adapted.getString("password"));
//        return user;
//    }
//}
