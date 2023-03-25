package api.hm6;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestAssuredUsersTest extends BaseApi {

    @BeforeEach
    public void setUp(){
        super.setUp();
    }

    @Test
    void getAllUsers() {
        given()
            .header("Authorization", "Bearer "+ token)
            .log().all()
            .when()
            .get("/users?page=1&per_page=100")
            .then()
            .spec(responseSpecificationStatusOk);
    }


    @Test
    void createUser() {
        DocumentContext body;
        try {
            body = JsonPath.parse(new File("src/main/resources/createPersonBodyForHm6.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final var email = faker.internet().emailAddress();
        final var name = faker.name().fullName();

        body = body.set("email", email)
                   .set("name", name);


        given()
            .header("Authorization", "Bearer " + token)
            .body(body.jsonString())
            .log().all()
            .when()
            .post("/users")
            .then()
            .spec(responseSpecificationStatusCreated)
            .body("email", equalTo(email));
    }

}
