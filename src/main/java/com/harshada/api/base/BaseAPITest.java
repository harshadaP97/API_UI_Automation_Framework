package com.harshada.api.base;

import com.harshada.base.TestBase;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class BaseAPITest extends TestBase {

    protected static String baseURL;
    protected static String token;

    static {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/main/java/com/harshada/config/config.properties"));
            baseURL = prop.getProperty("baseURL");
            token = prop.getProperty("token");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RequestSpecification request() {
        return given()
                .baseUri(baseURL)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");
    }
}
