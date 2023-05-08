package api.hm6.service;

import static io.restassured.RestAssured.given;

import api.hm6.BaseApi;
import api.hm6.configuration.data.users.CreateUserReq;
import api.hm6.configuration.data.users.PutUserReq;
import api.hm6.configuration.data.users.UserResponse;
import api.hm6.configuration.data.users.UserResponseAfterModify;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.SoftAssertions;

@RequiredArgsConstructor
public class UsersService extends BaseApi {
    private final RequestSpecification requestSpecification;

    public Response viewAllUsers() {
        return given(requestSpecification)
            .when()
            .get("/users?page=1&per_page=100")
            .andReturn();
    }

    public Response createUser(CreateUserReq request) {
        return given(requestSpecification)
            .body(request)
            .when()
            .post("/users")
            .andReturn();
    }

    public Response getUser(final String id) {
        return given(requestSpecification)
            .when()
            .get("/users" + "/{userId}", id)
            .andReturn();
    }

    public Response putUser(PutUserReq request, final String id) {
        return given(requestSpecification)
            .body(request)
            .when()
            .put("/users" + "/{userId}", id)
            .andReturn();
    }

    public Response deleteUser(final String id) {
        return given(requestSpecification)
            .when()
            .delete("/users" + "/{userId}", id)
            .andReturn();
    }

    public static void checkCreateUser(CreateUserReq request, UserResponse actualResponse) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResponse.getId()).isNotNull();
            softAssertions.assertThat(actualResponse.getName())
                          .isEqualTo(request.getName());
            softAssertions.assertThat(actualResponse.getEmail())
                          .isEqualTo(request.getEmail());
        });
    }

    public static void checkModifyUser(PutUserReq request, UserResponseAfterModify actualResponse) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResponse.getId()).isNotNull();
            softAssertions.assertThat(actualResponse.getName())
                          .isEqualTo(request.getName());
            softAssertions.assertThat(actualResponse.getEmail())
                          .isEqualTo(request.getEmail());
        });
    }

}
