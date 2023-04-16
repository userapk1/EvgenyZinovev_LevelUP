package api.hm6;

import api.hm6.configuration.data.posts.CreatePostReq;
import api.hm6.configuration.data.posts.PostResponse;
import api.hm6.configuration.data.users.CreateUserReq;
import api.hm6.configuration.data.users.UserResponse;
import api.hm6.service.PostsService;
import api.hm6.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestAssuredTests extends BaseApi {
    @BeforeEach
    public void setUp(){
        super.setUp();
    }

    @Test
    void viewAllUsers() {
        usersService.viewAllUsers()
            .then()
            .spec(responseSpecificationStatusOk);
    }

    @Test
    void viewAllPosts() {
        postsService.viewAllPosts()
                    .then()
                    .spec(responseSpecificationStatusOk);
    }

    @Test
    void createViewUpdateDelete() {
        var reqBodyForUser = generateUserCreateReqBody();
        var reqModifyBodyForUser = generateUserModifyReqBody();
        var createdUser = usersService.createUser(reqBodyForUser)
                                      .then()
                                      .spec(responseSpecificationStatusCreated)
                                      .extract()
                                      .as(UserResponse.class);

        UsersService.checkCreateUser(reqBodyForUser,createdUser);

        var viewCreatedUser = usersService.getUser(createdUser.getId().toString())
                                      .then()
                                      .spec(responseSpecificationStatusOk)
                                      .extract()
                                      .as(UserResponse.class);

        var modifyUser = usersService.putUser(reqModifyBodyForUser, createdUser.getId().toString())
                                      .then()
                                      .spec(responseSpecificationStatusOk)
                                      .extract()
                                      .as(UserResponse.class);

        UsersService.checkModifyUser(reqModifyBodyForUser,modifyUser);
        //создание, просмотр, апдейт поста
        var reqCreateBodyForPost = generatePostCreateReqBody(modifyUser.getId());
        var reqModifyBodyForPost = generatePostModifyReqBody(modifyUser.getId());
        var createdPost = postsService.createPost(reqCreateBodyForPost)
                                      .then()
                                      .spec(responseSpecificationStatusCreated)
                                      .extract()
                                      .as(PostResponse.class);

        PostsService.checkCreatePost(reqCreateBodyForPost, createdPost);


        /*given()
            .header("Authorization", "Bearer " + token)
            .header("content-type", "application/json")
            .body(bodyPost.jsonString())
            .log().all()
            .when()
            .post("/posts")
            .then()
            .spec(responseSpecificationStatusCreated)
            .body("user_id", equalTo(userId));*/
/*
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
            .body(Matchers.emptyString());*/
    }

    private CreateUserReq generateUserCreateReqBody() {
        return CreateUserReq
            .builder()
            .name(faker.name().fullName())
            .email(faker.internet().emailAddress())
            .gender(gender)
            .status(status)
            .build();
    }

    private UserResponse generateUserModifyReqBody() {
        return UserResponse
            .builder()
            .name(faker.name().fullName())
            .email(faker.internet().emailAddress())
            .gender(gender)
            .status(status)
            .build();
    }

    private CreatePostReq generatePostCreateReqBody(Integer userId) {
        return CreatePostReq
            .builder()
            .user_id(userId)
            .title(faker.lorem().words(3).toString())
            .body(faker.lorem().sentences(5).toString())
            .build();
    }

    private PostResponse generatePostModifyReqBody(Integer userId) {
        return PostResponse
            .builder()
            .user_id(userId)
            .title(faker.lorem().words(3).toString())
            .body(faker.lorem().sentences(5).toString())
            .build();
    }

}
