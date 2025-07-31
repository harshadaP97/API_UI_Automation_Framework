package com.harshada.api.utils;

import io.restassured.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.harshada.api.payload.UserPayload;
import java.io.File;

import static org.testng.Assert.assertEquals;

public class APIUtil {

    public static void assertStatusCode(Response response, int expectedCode) {
        assertEquals(response.getStatusCode(), expectedCode, "Unexpected status code");
    }

    public static void printResponse(Response response) {
        System.out.println("------ API Response ------");
        System.out.println(response.prettyPrint());
        System.out.println("--------------------------");
    }

    public static int extractId(Response response) {
        return response.jsonPath().getInt("id");
    }
    
    public static UserPayload getUserDataFromJson(String relativePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File(relativePath);
            return mapper.readValue(file, UserPayload.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load JSON file: " + e.getMessage());
        }
    }
}
