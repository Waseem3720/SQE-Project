package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class APITest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void testGetUser() {
        Response response =
            given()
                .when()
                .get("/users/2");

        System.out.println("GET Response Code: " + response.statusCode());
        response.then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.first_name", equalTo("Janet"));
    }

    @Test
    public void testCreateUser() {
        String requestBody = "{ \"name\": \"John\", \"job\": \"Engineer\" }";

        Response response =
            given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/users");

        System.out.println("POST Response Code: " + response.statusCode());
        response.then()
                .statusCode(201)
                .body("name", equalTo("John"))
                .body("job", equalTo("Engineer"));
    }

    @Test
    public void testUpdateUser() {
        String requestBody = "{ \"name\": \"John\", \"job\": \"Manager\" }";

        Response response =
            given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put("/users/2");

        System.out.println("PUT Response Code: " + response.statusCode());
        response.then()
                .statusCode(200)
                .body("name", equalTo("John"))
                .body("job", equalTo("Manager"));
    }

    @Test
    public void testDeleteUser() {
        Response response =
            given()
                .when()
                .delete("/users/2");

        System.out.println("DELETE Response Code: " + response.statusCode());
        response.then()
                .statusCode(204);
    }

    @Test
    public void testGetUserList() {
        Response response =
            given()
                .when()
                .get("/users");

        System.out.println("GET User List Response Code: " + response.statusCode());
        response.then()
                .statusCode(200)
                .body("data.size()", greaterThan(0));
    }

    @Test
    public void testGetUserNotFound() {
        Response response =
            given()
                .when()
                .get("/users/9999");

        System.out.println("GET User Not Found Response Code: " + response.statusCode());
        response.then()
                .statusCode(404);
    }

    @Test
    public void testPatchUpdateUserJob() {
        String requestBody = "{ \"job\": \"Lead Engineer\" }";

        Response response =
            given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch("/users/2");

        System.out.println("PATCH Update Job Response Code: " + response.statusCode());
        response.then()
                .statusCode(200)
                .body("job", equalTo("Lead Engineer"));
    }

    @Test
    public void testGetUserListWithPagination() {
        Response response =
            given()
                .param("page", 2)
                .when()
                .get("/users");

        System.out.println("GET Paginated User List Response Code: " + response.statusCode());
        response.then()
                .statusCode(200)
                .body("page", equalTo(2));
    }
}

