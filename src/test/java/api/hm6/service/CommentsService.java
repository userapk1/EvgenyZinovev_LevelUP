package api.hm6.service;

import static io.restassured.RestAssured.given;

import api.hm6.BaseApi;
import api.hm6.configuration.data.comments.CommentResponse;
import api.hm6.configuration.data.comments.CommentResponseAfterModify;
import api.hm6.configuration.data.comments.CreateCommentReq;
import api.hm6.configuration.data.comments.PutCommentReq;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.SoftAssertions;

@RequiredArgsConstructor
public class CommentsService extends BaseApi {

    private final RequestSpecification requestSpecification;

    public Response viewAllComments() {
        return given(requestSpecification)
            .when()
            .get("/comments?page=1&per_page=100")
            .andReturn();
    }

    public Response createComment(CreateCommentReq request) {
        return given(requestSpecification)
            .body(request)
            .when()
            .post("/comments")
            .andReturn();
    }

    public Response getComment(final String id) {
        return given(requestSpecification)
            .when()
            .get("/comments" + "/{commentId}", id)
            .andReturn();
    }

    public Response putComment(PutCommentReq request, final String id) {
        return given(requestSpecification)
            .body(request)
            .when()
            .put("/comments" + "/{commentId}", id)
            .andReturn();
    }

    public Response deleteComment(final String id) {
        return given(requestSpecification)
            .when()
            .delete("/comments" + "/{commentId}", id)
            .andReturn();
    }

    public static void checkCreateComment(CreateCommentReq request, CommentResponse actualResponse) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResponse.getId()).isNotNull();
            softAssertions.assertThat(actualResponse.getBody())
                          .isEqualTo(request.getBody());
            softAssertions.assertThat(actualResponse.getName())
                          .isEqualTo(request.getName());
        });
    }

    public static void checkModifyComment(PutCommentReq request, CommentResponseAfterModify actualResponse) {
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(actualResponse.getId()).isNotNull();
            softAssertions.assertThat(actualResponse.getBody())
                          .isEqualTo(request.getBody());
            softAssertions.assertThat(actualResponse.getName())
                          .isEqualTo(request.getName());
        });
    }
}
