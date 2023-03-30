package api.hm6;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestAssuredUsersTest extends BaseApi {
    @BeforeEach
    public void setUp(){
        super.setUp();
        person();
        personUp();
    }

    @Test
    void viewAllUsers() {
        given()
            .header("Authorization", "Bearer "+ token)
            .log().all()
            .when()
            .get("/users?page=1&per_page=100")
            .then()
            .spec(responseSpecificationStatusOk);
    }

    @Test
    void createViewUpdateDeleteUser() {
        final var email = faker.internet().emailAddress().toLowerCase();
        final var name = faker.name().fullName().toLowerCase();
        final var nameTwo = faker.name().fullName().toLowerCase();
        final int id = 576853;

        body = body.set("email", email)
                   .set("name", name);

        updateBody = updateBody.set("name", nameTwo);

        /*given()
            .header("Authorization", "Bearer " + token)
            .header("content-type", "application/json")
            .body(body.jsonString())
            .log().all()
            .when()
            .post("/users")
            .then()
            .spec(responseSpecificationStatusCreated)
            .body("email", equalTo(email))
            .body("name", equalTo(name));

        given()
            .header("Authorization", "Bearer " + token)
            .header("content-type", "application/json")
            .log().all()
            .when()
            .get("/users/" + id)
            .then()
            .spec(responseSpecificationStatusOk)
            .body("email", equalTo(email))
            .body("name", equalTo(name))
            .body("id", Matchers.equalTo(id));*/

        given()
            .header("Authorization", "Bearer " + token)
            .header("content-type", "application/json")
            .body(updateBody.jsonString())
            .log().all()
            .when()
            .put("/users/" + id)
            .then()
            .spec(responseSpecificationStatusOk)
            .body("name", equalTo(nameTwo))
            .body("id", equalTo(id));

        /*given()
            .header("Authorization", "Bearer " + token)
            .header("content-type", "application/json")
            .log().all()
            .when()
            .delete("/users/" + id)
            .then()
            .spec(responseSpecificationStatusNoContent)
            .body(Matchers.emptyString());*/
    }

}
