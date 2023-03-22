package api.hm6;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class RestAssuredUsersTest extends BaseApi {

    @Test
    void getAllUsers() {
        RestAssured
            .given()
            .header(token)
            .log().all()
            .when()
            .get("/users" + token)
            .then()
            .spec(responseSpecificationStatusOk);
    }

    @Test
    void createUser() {
        final var randomEmail = faker.internet().emailAddress().toLowerCase();

        RestAssured
            .given()
            .header("Authorization", "Bearer "+ token)
            .log().all()
            .when()
            .get("/users")
            .then()
            .spec(responseSpecificationStatusOk);

      /*  RestAssured
            .given()
            .header(token)
            .log().all()
            .when()
            .post("/users" + randomEmail)
            .then()
            .spec(responseSpecificationStatusCreated)
            .body("email", Matchers.hasItem(randomEmail));
*/
    }

}
