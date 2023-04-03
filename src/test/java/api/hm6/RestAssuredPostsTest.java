package api.hm6;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestAssuredPostsTest extends BaseApi{

    @BeforeEach
    public void setUp(){
        super.setUp();
        post();
        postUp();
    }

    /*@Test
    void viewAllPosts() {
        given()
            .header("Authorization", "Bearer "+ token)
            .log().all()
            .when()
            .get("/posts?page=1&per_page=100")
            .then()
            .spec(responseSpecificationStatusOk);
    }*/

    @Test
    void createViewUpdateDeletePost() {
        final var title = faker;
        final var titleTwo = faker;

        final int id = 1317;

        bodyPost = bodyPost.set("title", title)
            .set("user_id",);

        updateBodyPost = updateBodyPost.set("title", titleTwo);

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

        given()
            .header("Authorization", "Bearer " + token)
            .header("content-type", "application/json")
            .log().all()
            .when()
            .delete("/post/" + id)
            .then()
            .spec(responseSpecificationStatusNoContent)
            .body(Matchers.emptyString());
    }

}
