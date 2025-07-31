package com.harshada.api.test;

import com.harshada.api.base.BaseAPITest;
import com.harshada.api.endpoints.UserEndpoints;
import com.harshada.api.payload.UserPayload;
import com.harshada.api.utils.APIUtil;
import io.restassured.response.Response;
import org.testng.annotations.Test;

public class UserCRUDTests extends BaseAPITest {

    UserEndpoints userEndpoints = new UserEndpoints();
    static int userId;

    @Test(priority = 1)
    public void testCreateUser() {
        UserPayload payload = APIUtil.getUserDataFromJson("src/test/resources/testdata/userData.json");
        payload.setEmail("harshada" + System.currentTimeMillis() + "@qa.com"); // Make email unique

        logger.info("Creating user with payload: " + payload.getName() + ", " + payload.getEmail());

        Response response = userEndpoints.createUser(payload);
        logger.info("Response status: " + response.getStatusCode());
        logger.info("Response body:\n" + response.asPrettyString());

        APIUtil.assertStatusCode(response, 201);
        userId = APIUtil.extractId(response);

        logger.pass("User created successfully. ID: " + userId);
    }

    @Test(priority = 2, dependsOnMethods = "testCreateUser")
    public void testGetUserById() {
        logger.info("Fetching user by ID: " + userId);

        Response response = userEndpoints.getUserById(userId);
        logger.info("Response:\n" + response.asPrettyString());

        APIUtil.assertStatusCode(response, 200);
        logger.pass("User fetched successfully");
    }

    @Test(priority = 3, dependsOnMethods = "testCreateUser")
    public void testUpdateUser() {
        UserPayload updatedPayload = APIUtil.getUserDataFromJson("src/test/resources/testdata/userData.json");
        updatedPayload.setName("Harshada QA Updated");
        updatedPayload.setStatus("inactive");
        updatedPayload.setEmail("harshada.updated" + System.currentTimeMillis() + "@qa.com");

        logger.info("Updating user ID: " + userId + " with new email: " + updatedPayload.getEmail());

        Response response = userEndpoints.updateUser(userId, updatedPayload);
        logger.info("Response:\n" + response.asPrettyString());

        APIUtil.assertStatusCode(response, 200);
        logger.pass("User updated successfully");
    }

    @Test(priority = 4, dependsOnMethods = "testCreateUser")
    public void testDeleteUser() {
        logger.info("Deleting user with ID: " + userId);

        Response response = userEndpoints.deleteUser(userId);
        logger.info("Response Code: " + response.getStatusCode());

        APIUtil.assertStatusCode(response, 204);
        logger.pass("User deleted successfully");
    }

    @Test(priority = 5, dependsOnMethods = "testDeleteUser")
    public void testUserDeleted() {
        logger.info("Verifying user is deleted, ID: " + userId);

        Response response = userEndpoints.getUserById(userId);
        logger.info("Response Code: " + response.getStatusCode());

        APIUtil.assertStatusCode(response, 404);
        logger.pass("User is confirmed deleted");
    }
}
