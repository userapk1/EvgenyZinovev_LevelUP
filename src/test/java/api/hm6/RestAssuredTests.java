package api.hm6;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestAssuredTests extends BaseApi {
    @BeforeEach
    public void setUp(){
        super.setUp();
        person();
        personUp();
    }

    @Test
    void viewAllUsers() {

        given()
            .spec(requestSpecification)
            .when()
            .get("/users?page=1&per_page=100")
            .then()
            .spec(responseSpecificationStatusOk);
    }

   /* @Test
    void createViewUpdateDeleteUser() {
        final var email = faker.internet().emailAddress().toLowerCase();
        final var name = faker.name().fullName().toLowerCase();
        final var title = faker.random().hex();
        //временные переменные
        final int userId = 598207;

        bodyPerson = bodyPerson.set("email", email)
                   .set("name", name);


        bodyPost = bodyPost.set("title", title)
                           .set("user_id", userId);


        //создание, просмотр, апдейт пользователя
        given()
            .header("Authorization", "Bearer " + token)
            .header("content-type", "application/json")
            .body(bodyPerson.jsonString())
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
            .get("/users/" + userId)
            .then()
            .spec(responseSpecificationStatusOk)
            .body("email", equalTo(email))
            .body("name", equalTo(name))
            .body("id", equalTo(userId));

        given()
            .header("Authorization", "Bearer " + token)
            .header("content-type", "application/json")
            .body(updateBodyPerson.jsonString())
            .log().all()
            .when()
            .put("/users/" + userId)
            .then()
            .spec(responseSpecificationStatusOk)
            .body("name", equalTo(nameTwo))
            .body("id", equalTo(userId));

        //создание, просмотр, апдейт поста
        given()
            .header("Authorization", "Bearer " + token)
            .header("content-type", "application/json")
            .body(bodyPost.jsonString())
            .log().all()
            .when()
            .post("/posts")
            .then()
            .spec(responseSpecificationStatusCreated)
            .body("user_id", equalTo(userId));

        given()
            .header("Authorization", "Bearer " + token)
            .header("content-type", "application/json")
            .log().all()
            .when()
            .get("/post/" + id)
            .then()
            .spec(responseSpecificationStatusOk)
            .body("user_id", equalTo(userId))
            .body("id", equalTo(id));

        given()
            .header("Authorization", "Bearer " + token)
            .header("content-type", "application/json")
            .body(updateBodyPost.jsonString())
            .log().all()
            .when()
            .put("/post/" + id)
            .then()
            .spec(responseSpecificationStatusOk)
            .body("user_id", equalTo(userIdTwo))
            .body("id", equalTo(id));












        //удаление поста
        given()
            .header("Authorization", "Bearer " + token)
            .header("content-type", "application/json")
            .log().all()
            .when()
            .delete("/post/" + id)
            .then()
            .spec(responseSpecificationStatusNoContent)
            .body(Matchers.emptyString());

        //удаление пользователя
        given()
            .header("Authorization", "Bearer " + token)
            .header("content-type", "application/json")
            .log().all()
            .when()
            .delete("/users/" + userId)
            .then()
            .spec(responseSpecificationStatusNoContent)
            .body(Matchers.emptyString());
    }
*/
}
