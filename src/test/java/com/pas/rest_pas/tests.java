//package com.pas.rest_pas;
//
//import com.pas.rest_pas.endpoints.*;
//import com.pas.rest_pas.entities.user.*;
//import com.pas.rest_pas.entities.costume.*;
//import com.pas.rest_pas.entities.user.*;
//import com.pas.rest_pas.exceptions.*;
//import com.pas.rest_pas.global_config.*;
//import com.pas.rest_pas.managers.*;
//import com.pas.rest_pas.repositories.*;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.http.Method;
//import io.restassured.specification.RequestSpecification;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.testng.Assert;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.*;
//import io.restassured.response.Response;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import org.hamcrest.Matchers.*;
//
//import static io.restassured.parsing.Parser.JSON;
//import static org.hamcrest.Matchers.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.client.WebTarget;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Request;
//import java.sql.Array;
//import java.util.*;
//
//public class tests {
//    @Test
//    void user_display(){
//
//        RestAssured.baseURI="http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/clients";
//        RequestSpecification httpRequest = given();
//
//        JSONObject requestParams = new JSONObject();
//
//        //requestParams.put("id","33cf4e6a-5cd7-41ce-bf61-79dc03c0f123");
//        requestParams.put("active",true);
//        requestParams.put("email","arturo@upd.pl");
//        requestParams.put("firstName","Artur");
//        requestParams.put("lastName","Kowalski");
//        requestParams.put("login","arturo99");
//        requestParams.put("password","artur1");
//
//        httpRequest.header("Content-type","application/json");
//        httpRequest.body(requestParams.toJSONString());
//
//        Response response = httpRequest.request(Method.POST,"/add");
//
//        String responseBody = response.getBody().asString();
//        System.out.println("Response body is: " + responseBody);
//        int statusCode = response.getStatusCode();
//        System.out.println("Status code is: "+ statusCode);
//        Assert.assertEquals(statusCode, 200);
//
//        //String successCode = response.jsonPath().get("SuccessCode");
//        //Assert.assertEquals(successCode, "OPERATION_SUCCESS");
//        given().get("http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/clients/all")
//                .then().statusCode(200).body("login", hasItem("Artur29"), "email", hasItem("Artur29@upd.pl"));
//        Response response2 = request(Method.GET,"http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/clients/all");
//
//        String responseBody2 = response2.getBody().asString();
//        System.out.println("Response body is: " + responseBody2);
//
//
//    }
//
//    /*@Test
//    void test_adding(){
//        //JSONObject request = new JSONObject();
//
//        List<Map<String, Object>> userinfo = new ArrayList<>();
//
//        Map<String, Object> request = new HashMap<>();
//
//        request.put("id","33cf4e6a-5cd7-41ce-bf61-79dc03c0f55c");
//        request.put("active",true);
//        request.put("firstName","Bogdan");
//        request.put("lastName","Kowalski");
//        request.put("login","Bogdan211");
//        request.put("password","bogdan1");
//
//       //Response response = given().header("Content-Type", "application/json").contentType("application/json").
//        Response response = given().contentType("application/json").
//                accept("application/json").
//                body(request)
//                .when().post("http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/clients/add")
//                .then().statusCode(200).contentType("application/json").extract().response();
//
//       String userID = response.jsonPath().getString("userID");
//       assertNotNull(userID);
//        /*ClientBuilder clientBuilder = ClientBuilder.newBuilder();
//        Client client = clientBuilder.build();
//
//        WebTarget target = client.target("http://localhost:8080/PAS_Project-1.0-SNAPSHOT/");
//        Entity client1 = target.path("rest").path("clients").path("all").request(MediaType.APPLICATION_JSON).get(Entity.class);
//        assertNotNull(client1);
//        assertEquals("33cf4e6a-5cd7-41ce-bf61-79dc03c0fe4c" ,client1.getId());*/
//
//
//    //}
//    @Test
//    void user_create() {
//        RestAssured.baseURI="http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/clients";
//        RequestSpecification httpRequest = given();
//
//        JSONObject requestParams = new JSONObject();
//
//        requestParams.put("id","33cf4e6a-5cd7-41ce-bf61-79dc03c0f55c");
//        requestParams.put("active",true);
//        requestParams.put("email","Bogdan22@upd.pl");
//        requestParams.put("firstName","Bogdan");
//        requestParams.put("lastName","Kowalski");
//        requestParams.put("login","Bogdan22");
//        requestParams.put("password","bogdan1");
//
//        httpRequest.header("Content-type","application/json");
//        httpRequest.body(requestParams.toJSONString());
//
//        Response response = httpRequest.request(Method.POST,"/add");
//
//        String responseBody = response.getBody().asString();
//        System.out.println("Response body is: " + responseBody);
//        int statusCode = response.getStatusCode();
//        System.out.println("Status code is: "+ statusCode);
//        Assert.assertEquals(statusCode, 200);
//
//        //String successCode = response.jsonPath().get("SuccessCode");
//        //Assert.assertEquals(successCode, "OPERATION_SUCCESS");
//        given().get("http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/clients/all")
//                .then().statusCode(200).body("login", hasItem("Bogdan22"), "email", hasItem("Bogdan22@upd.pl"));
//
//        Response response2 = request(Method.GET,"http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/clients/all");
//
//        String responseBody2 = response2.getBody().asString();
//        System.out.println("Response body is: " + responseBody2);
//
//
//    }
//
//    @Test
//    void costume_display(){
//
//        RestAssured.baseURI="http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes";
//        RequestSpecification httpRequest = given();
//
//        JSONObject requestParams = new JSONObject();
//
//
//        requestParams.put("costumeSize","XL");
//        requestParams.put("name","Funny costume");
//        requestParams.put("price", 155.0);
//        requestParams.put("forWhom", "MAN");
//
//
//        httpRequest.header("Content-type","application/json");
//        httpRequest.body(requestParams.toJSONString());
//
//        Response response = httpRequest.request(Method.POST,"/add");
//
//        String responseBody = response.getBody().asString();
//        System.out.println("Response body is: " + responseBody);
//        int statusCode = response.getStatusCode();
//        System.out.println("Status code is: "+ statusCode);
//        Assert.assertEquals(statusCode, 200);
//
//        //String successCode = response.jsonPath().get("SuccessCode");
//        //Assert.assertEquals(successCode, "OPERATION_SUCCESS");
//        given().get("http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes/all")
//                .then().statusCode(200).body("forWhom", hasItem("MAN"));
//
//        Response response2 = request(Method.GET,"http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes/all");
//
//        String responseBody2 = response2.getBody().asString();
//        System.out.println("Response body is: " + responseBody2);
//
//    }
//
//    @Test
//    void costume_read(){
//
//        RestAssured.baseURI="http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes";
//        RequestSpecification httpRequest = given();
//
//        JSONObject requestParams = new JSONObject();
//
//
//        requestParams.put("costumeSize","L");
//        requestParams.put("name","BESTcostume");
//        requestParams.put("price", 100.0);
//        requestParams.put("forWhom", "GIRLS");
//
//
//
//
//        httpRequest.header("Content-type","application/json");
//        httpRequest.body(requestParams.toJSONString());
//
//        Response response = httpRequest.request(Method.POST,"/add");
//
//        String responseBody = response.getBody().asString();
//        System.out.println("Response body is: " + responseBody);
//        int statusCode = response.getStatusCode();
//        System.out.println("Status code is: "+ statusCode);
//        Assert.assertEquals(statusCode, 200);
//
//        //String successCode = response.jsonPath().get("SuccessCode");
//        //Assert.assertEquals(successCode, "OPERATION_SUCCESS");
//        given().get("http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes/all")
//                .then().statusCode(200).body("forWhom", hasItem("MAN"));
//
//        Response response2 = request(Method.GET,"http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes/all");
//
//        String responseBody2 = response2.getBody().asString();
//        System.out.println("Response body is: " + responseBody2);
//
//    }
//
//
//
//    @Test
//    void user_syntax() {
//        RestAssured.baseURI="http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/clients";
//        RequestSpecification httpRequest = given();
//
//        JSONObject requestParams = new JSONObject();
//
//        //requestParams.put("id","33cf4e6a-5cd7-41ce-bf61-79dc03c0f55c");
//        requestParams.put("active", false);
//        requestParams.put("email","Bogdan6667@upd.pl");
//        requestParams.put("firstName", 123);
//        requestParams.put("lastName","Kowalski");
//        requestParams.put("login","Bogdan6667");
//        requestParams.put("password","bogdan1");
//
//        httpRequest.header("Content-type","application/json");
//        httpRequest.body(requestParams.toJSONString());
//
//        Response response = httpRequest.request(Method.POST,"/add");
//
//        //String responseBody = response.getBody().asString();
//        //System.out.println("Response body is: " + responseBody);
//        int statusCode = response.getStatusCode();
//        System.out.println("Status code is: "+ statusCode);
//        Assert.assertEquals(statusCode, 400);
//
//        //String successCode = response.jsonPath().get("SuccessCode");
//        //Assert.assertEquals(successCode, "OPERATION_SUCCESS");
//        //given().get("http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/clients/all")
//        //.then().statusCode(200).body("login", hasItem("Bogdan22"), "email", hasItem("Bogdan22@upd.pl"));
//
//
//    }
//
//    @Test
//    void user_id_replication() {
//        RestAssured.baseURI="http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/clients";
//        RequestSpecification httpRequest = given();
//
//        JSONObject requestParams = new JSONObject();
//
//        //requestParams.put("id","33cf4e6a-5cd7-41ce-bf61-79dd03c0155c");
//        requestParams.put("active", true);
//        requestParams.put("email","Bogdan22@upd.pl");
//        requestParams.put("firstName","Ignacy");
//        requestParams.put("lastName","Nowak");
//        requestParams.put("login","Bogdan22");
//        requestParams.put("password","ignacyy");
//
//        httpRequest.header("Content-type","application/json");
//        httpRequest.body(requestParams.toJSONString());
//
//        Response response = httpRequest.request(Method.POST,"/add");
//
//        String responseBody = response.getBody().asString();
//        System.out.println("Response body is: " + responseBody);
//        int statusCode = response.getStatusCode();
//        System.out.println("Status code is: "+ statusCode);
//        Assert.assertEquals(statusCode, 400);
//
//        //String successCode = response.jsonPath().get("SuccessCode");
//        //Assert.assertEquals(successCode, "OPERATION_SUCCESS");
//        given().get("http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/clients/all")
//                .then().statusCode(200).body("login", hasItem("Bogdan22"), "email", hasItem("Bogdan22@upd.pl"));
//
//
//    }
//
//
//    @Test
//    void costume_update(){
//
//
//        RestAssured.baseURI="http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes";
//        RequestSpecification httpRequest = given();
//
//        JSONObject requestParams = new JSONObject();
//
//        requestParams.put("id","33cf4e6a-5cd7-41ce-bf61-11dd03c01554");
//        requestParams.put("costumeSize","L");
//        requestParams.put("name","BESTcostume");
//        requestParams.put("price", 100.0);
//        //requestParams.put("forWhom", "GIRLS");
//
//        httpRequest.header("Content-type","application/json");
//        httpRequest.body(requestParams.toJSONString());
//
//        Response response = httpRequest.request(Method.POST,"/add");
//
//        String responseBody = response.getBody().asString();
//        System.out.println("Response body is: " + responseBody);
//        int statusCode3 = response.getStatusCode();
//        System.out.println("Status code is: "+ statusCode3);
//        Assert.assertEquals(statusCode3, 200);
//        RestAssured.baseURI="http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes";
//        RequestSpecification httpRequest2 = RestAssured.given();
//
//        JSONObject requestParams2 = new JSONObject();
//
//
//        requestParams2.put("costumeSize", "S");
//        requestParams2.put("price", 123.0);
//
//
//
//
//
//
//        httpRequest2.header("Content-type","application/json");
//        httpRequest2.body(requestParams2.toJSONString());
//
//        Response response2 = httpRequest2.request(Method.PUT,"/update/33cf4e6a-5cd7-41ce-bf61-11dd03c01554");
//
//        //String responseBody = response.getBody().asString();
//        //System.out.println("Response body is: " + responseBody);
//        int statusCode2 = response2.getStatusCode();
//        System.out.println("Status code is: "+ statusCode2);
//        Assert.assertEquals(statusCode2, 200);
//
//        //String successCode = response.jsonPath().get("SuccessCode");
//        //Assert.assertEquals(successCode, "OPERATION_SUCCESS");
//        given().get("http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes/all")
//                .then().statusCode(200).body("id", hasItem("33cf4e6a-5cd7-41ce-bf61-11dd03c01554"));
//
//        Response response3 = request(Method.GET,"http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes/all");
//
//        String responseBody3 = response3.getBody().asString();
//        System.out.println("Response body is: " + responseBody3);
//
//    }
//
//    @Test
//    void costume_delete(){
//
//
//        RestAssured.baseURI="http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes";
//        RequestSpecification httpRequest = given();
//
//        JSONObject requestParams = new JSONObject();
//
//        requestParams.put("id","33114e6a-5cd7-41ce-bf61-11dd03c01555");
//        requestParams.put("costumeSize","L");
//        requestParams.put("name","BESTcostume");
//        requestParams.put("price", 100.0);
//        //requestParams.put("forWhom", "GIRLS");
//
//        httpRequest.header("Content-type","application/json");
//        httpRequest.body(requestParams.toJSONString());
//
//        Response response = httpRequest.request(Method.POST,"/add");
//
//        String responseBody = response.getBody().asString();
//        System.out.println("Response body is: " + responseBody);
//        int statusCode3 = response.getStatusCode();
//        System.out.println("Status code is: "+ statusCode3);
//        Assert.assertEquals(statusCode3, 200);
//        RestAssured.baseURI="http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes";
//        RequestSpecification httpRequest2 = RestAssured.given();
//
//
//
//
//
//
//
//
//
//
//
//        httpRequest2.header("Content-type","application/json");
//
//
//        Response response2 = httpRequest2.request(Method.DELETE,"/delete/33114e6a-5cd7-41ce-bf61-11dd03c01555");
//
//        String responseBody2 = response2.getBody().asString();
//        System.out.println("Response body is: " + responseBody2);
//        int statusCode2 = response2.getStatusCode();
//        System.out.println("Status code is: "+ statusCode2);
//        Assert.assertEquals(statusCode2, 200);
//
//        RequestSpecification httpRequest3 = RestAssured.given();
//        Response response3 = httpRequest3.request(Method.GET,"/getById/33114e6a-5cd7-41ce-bf61-11dd03c01555");
//        String responseBody3 = response3.getBody().asString();
//        System.out.println("Response body is: " + responseBody3);
//        int statusCode4 = response3.getStatusCode();
//        System.out.println("Status code is: "+ statusCode4);
//        Assert.assertEquals(statusCode4, 200);
//
//        //String successCode = response.jsonPath().get("SuccessCode");
//        //Assert.assertEquals(successCode, "OPERATION_SUCCESS");
//        //given().get("http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes/all")
//        //        .then().statusCode(404).body("id", hasItem("33114e6a-5cd7-41ce-bf61-11dd03c01555"));
//
//        // Response response3 = request(Method.GET,"http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes/all");
//
//        //String responseBody3 = response3.getBody().asString();
//        //System.out.println("Response body is: " + responseBody3);
//
//    }
//
//    @Test
//    void rent_add(){
//
//
//
//        RestAssured.baseURI="http://localhost:8080/PAS_Project-1.0-SNAPSHOT";
//        RequestSpecification httpRequest = given();
//
//
//
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.add("ccb834cd-ead7-4037-a09b-906a3f5a0c5f");
//        System.out.println(jsonArray);
//
//
//
//
//
//
//        httpRequest.header("Content-type","application/json");
//        httpRequest.body(jsonArray);
//
//        Response response = httpRequest.request(Method.POST,"/rest/rents/add?userLogin=Radek460&date=2022-10-10");
//
//        String responseBody = response.getBody().asString();
//        System.out.println("Response body is: " + responseBody);
//        int statusCode = response.getStatusCode();
//        System.out.println("Status code is: "+ statusCode);
//        Assert.assertEquals(statusCode, 200);
//
//        //String successCode = response.jsonPath().get("SuccessCode");
//        //Assert.assertEquals(successCode, "OPERATION_SUCCESS");
//        //given().get("http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes/all")
//        //        .then().statusCode(200).body("forWhom", hasItem("MAN"));
//
//        //Response response2 = request(Method.GET,"http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes/all");
//
//        //String responseBody2 = response2.getBody().asString();
//        //System.out.println("Response body is: " + responseBody2);
//
//    }
//
//    @Test
//    void rent_already_rented(){
//
//        RestAssured.baseURI="http://localhost:8080/PAS_Project-1.0-SNAPSHOT";
//        RequestSpecification httpRequest2 = given();
//
//
//
//        JSONArray jsonArray2 = new JSONArray();
//        jsonArray2.add("af5449b0-4fe1-4784-a284-da62d30c692b");
//        System.out.println(jsonArray2);
//
//
//
//
//
//
//        httpRequest2.header("Content-type","application/json");
//        httpRequest2.body(jsonArray2);
//
//        Response response2 = httpRequest2.request(Method.POST,"/rest/rents/add?userLogin=Radek460&date=2022-10-10");
//
//        String responseBody2 = response2.getBody().asString();
//        System.out.println("Response body is: " + responseBody2);
//        int statusCode2 = response2.getStatusCode();
//        System.out.println("Status code is: "+ statusCode2);
//        Assert.assertEquals(statusCode2, 200);
//
//        RestAssured.baseURI="http://localhost:8080/PAS_Project-1.0-SNAPSHOT";
//        RequestSpecification httpRequest = given();
//
//        //JSONObject requestParams = new JSONObject();
//
//
//        //List<Map> result = new ArrayList();
//        //Map<String, Object> mapObject= new HashMap();
//
//        //mapObject.put("uuid", "bd314d4a-a3a8-41aa-89fb-6572ab269253");
//        //result.add(mapObject);
//        //mapObject.();
//        // Os[] osArray = new Os[1];
//        // Os os1 = new Os();
//        //os1.os_id = "bd314d4a-a3a8-41aa-89fb-6572ab269253";
//
//        // osArray[0] = os1;
//        //List<String> myList = Arrays.asList("bd314d4a-a3a8-41aa-89fb-6572ab269253");
//        //HashMap<String, Object> myList = new HashMap<>();
//
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.add("af5449b0-4fe1-4784-a284-da62d30c692b");
//        System.out.println(jsonArray);
//
//
//        httpRequest.header("Content-type","application/json");
//        httpRequest.body(jsonArray);
//
//        Response response = httpRequest.request(Method.POST,"/rest/rents/add?userLogin=MrRadek2000&date=2022-11-10");
//
//        String responseBody = response.getBody().asString();
//        System.out.println("Response body is: " + responseBody);
//        int statusCode = response.getStatusCode();
//        System.out.println("Status code is: "+ statusCode);
//        Assert.assertEquals(statusCode, 403);
//
//        //String successCode = response.jsonPath().get("SuccessCode");
//        //Assert.assertEquals(successCode, "OPERATION_SUCCESS");
//        //given().get("http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes/all")
//        //        .then().statusCode(200).body("forWhom", hasItem("MAN"));
//
//        //Response response2 = request(Method.GET,"http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes/all");
//
//        //String responseBody2 = response2.getBody().asString();
//        //System.out.println("Response body is: " + responseBody2);
//
//    }
//
//
//    @Test
//    void costume_syntax(){
//
//        RestAssured.baseURI="http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes";
//        RequestSpecification httpRequest = given();
//
//        JSONObject requestParams = new JSONObject();
//
//        //requestParams.put("id","26a55e3e-1bc1-4117-9b3a-c01c1f82e9b0");
//        requestParams.put("costumeSize","XL");
//        requestParams.put("name", "funny costume");
//        requestParams.put("price", -25);
//        //requestParams.put("forWhom", "ME");
//
//
//
//        httpRequest.header("Content-type","application/json");
//        httpRequest.body(requestParams.toJSONString());
//
//        Response response = httpRequest.request(Method.POST,"/add");
//
//        String responseBody = response.getBody().asString();
//        System.out.println("Response body is: " + responseBody);
//        int statusCode = response.getStatusCode();
//        System.out.println("Status code is: "+ statusCode);
//        Assert.assertEquals(statusCode, 400);
//
//        //String successCode = response.jsonPath().get("SuccessCode");
//        //Assert.assertEquals(successCode, "OPERATION_SUCCESS");
//
//
//    }
//
//    @Test
//    void user_update(){
//
//
//        RestAssured.baseURI="http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/clients";
//        RequestSpecification httpRequest = given();
//
//        JSONObject requestParams = new JSONObject();
//
//        //requestParams.put("id","33cf4e6a-5cd7-41ce-bf61-79dd03c0155c");
//        requestParams.put("email","Daro1@upd.pl");
//        requestParams.put("firstName","Ignacy");
//        requestParams.put("lastName","Nowak");
//        requestParams.put("login","Daro1");
//        requestParams.put("password","ignacyy");
//
//        httpRequest.header("Content-type","application/json");
//        httpRequest.body(requestParams.toJSONString());
//
//        Response response = httpRequest.request(Method.POST,"/add");
//
//        String responseBody = response.getBody().asString();
//        System.out.println("Response body is: " + responseBody);
//        int statusCode = response.getStatusCode();
//        System.out.println("Status code is: "+ statusCode);
//        Assert.assertEquals(statusCode, 200);
//
//        RequestSpecification httpRequest2 = given();
//
//        JSONObject requestParams2 = new JSONObject();
//
//
//        requestParams2.put("login","Szymon123");
//
//
//
//
//
//        httpRequest2.header("Content-type","application/json");
//        httpRequest2.body(requestParams2.toJSONString());
//
//        Response response2 = httpRequest2.request(Method.PUT,"/update/Daro1");
//
//        String responseBody2 = response2.getBody().asString();
//        System.out.println("Response body is: " + responseBody2);
//        int statusCode2 = response2.getStatusCode();
//        System.out.println("Status code is: "+ statusCode2);
//        Assert.assertEquals(statusCode2, 200);
//
//        //String successCode = response.jsonPath().get("SuccessCode");
//        //Assert.assertEquals(successCode, "OPERATION_SUCCESS");
//        //given().get("http://localhost:8080/PAS/rest/clients/all")
//        //       .then().statusCode(200).body("login", hasItem("Szymon123"));
//
//        // Response response3 = request(Method.GET,"http://localhost:8080/PAS_Project-1.0-SNAPSHOT/rest/costumes/all");
//
//        //String responseBody3 = response3.getBody().asString();
//        //System.out.println("Response body is: " + responseBody3);
//
//    }
//}
