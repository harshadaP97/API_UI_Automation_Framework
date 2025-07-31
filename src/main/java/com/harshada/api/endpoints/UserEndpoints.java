package com.harshada.api.endpoints;

import com.harshada.api.base.BaseAPITest;
import com.harshada.api.payload.UserPayload;
import io.restassured.response.Response;

public class UserEndpoints extends BaseAPITest {

    public Response getAllUsers() {
        return request()
                .when()
                .get("/users");
    }

    public Response getUserById(int id) {
        return request()
                .when()
                .get("/users/" + id);
    }

    public Response createUser(UserPayload payload) {
        return request()
                .body(payload) // POJO automatically converted to JSON
                .when()
                .post("/users");
    }

    public Response updateUser(int id, UserPayload payload) {
        return request()
                .body(payload)
                .when()
                .put("/users/" + id);
    }

    public Response deleteUser(int id) {
        return request()
                .when()
                .delete("/users/" + id);
    }
}
