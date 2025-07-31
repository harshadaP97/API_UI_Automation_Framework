package com.harshada.api.client;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;

import java.util.Map;

public class APIClient {

    // GET request with path param
    public static Response getRequest(String endpoint, Map<String, Object> pathParams) {
    	return given()
    	        .header("Content-Type", "application/json")
    	        .pathParams(pathParams)
    	        .log().all()
    	        .when()
    	        .get(endpoint)
    	        .then()
    	        .log().all()
    	        .extract().response();

    }

    // POST request with body
    public static Response postRequest(String endpoint, Object body) {
                		return given()
                		        .header("Content-Type", "application/json")
                		        .log().all()
                		        .body(body)
                		        .when()
                		        .post(endpoint)
                		        .then()
                		        .log().all()
                		        .extract().response();

    }

    // PUT request with path param and body
    public static Response putRequest(String endpoint, Map<String, Object> pathParams, Object body) {
    	return given()
    	        .header("Content-Type", "application/json")
    	        .pathParams(pathParams)
    	        .log().all()
    	        .body(body)
    	        .when()
    	        .put(endpoint)
    	        .then()
    	        .log().all()
    	        .extract().response();

    }

    // DELETE request with path param
    public static Response deleteRequest(String endpoint, Map<String, Object> pathParams) {
        return given()
        		.header("Content-Type", "application/json")
                .pathParams(pathParams)
                .log().all()
                .when()
                .delete(endpoint)
                .then()
                .log().all()
                .extract().response();
    }
}
