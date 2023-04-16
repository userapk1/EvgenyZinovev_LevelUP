package api.hm6.service;

import static io.restassured.RestAssured.given;

import api.hm6.BaseApi;
import api.hm6.configuration.data.posts.CreatePostReq;
import api.hm6.configuration.data.posts.PostResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.SoftAssertions;

@RequiredArgsConstructor
public class PostsService extends BaseApi {

    private final RequestSpecification requestSpecification;

    public Response viewAllPosts() {
        return given(requestSpecification)
            .when()
            .get("/posts?page=1&per_page=100")
            .andReturn();
    }

    public Response createPost(CreatePostReq request) {
        return given(requestSpecification)
            .body(request)
            .when()
            .post("/posts")
            .andReturn();
    }

    public Response getPost(final String id) {
        return given(requestSpecification)
            .when()
            .get("/posts" + "/{postId}", id)
            .andReturn();
    }

    public Response putPost(PostResponse request, final String id) {
        return given(requestSpecification)
            .body(request)
            .when()
            .put("/posts" + "/{postId}", id)
            .andReturn();
    }

    public Response deletePost(final String id) {
        return given(requestSpecification)
            .when()
            .delete("/posts" + "/{postId}", id)
            .andReturn();
    }

    public static void checkCreatePost(CreatePostReq request, PostResponse actualResponse) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResponse.getId()).isNotNull();
            softAssertions.assertThat(actualResponse.getTitle())
                          .isEqualTo(request.getTitle());
            softAssertions.assertThat(actualResponse.getBody())
                          .isEqualTo(request.getBody());
        });
    }

    public static void checkModifyPost(PostResponse request, PostResponse actualResponse) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResponse.getId()).isNotNull();
            softAssertions.assertThat(actualResponse.getTitle())
                          .isEqualTo(request.getTitle());
            softAssertions.assertThat(actualResponse.getBody())
                          .isEqualTo(request.getBody());
        });
    }
}
