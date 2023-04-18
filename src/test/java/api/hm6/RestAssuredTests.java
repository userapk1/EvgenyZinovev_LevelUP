package api.hm6;

import api.hm6.configuration.data.comments.CommentResponse;
import api.hm6.configuration.data.comments.CommentResponseAfterModify;
import api.hm6.configuration.data.comments.CreateCommentReq;
import api.hm6.configuration.data.comments.PutCommentReq;
import api.hm6.configuration.data.posts.CreatePostReq;
import api.hm6.configuration.data.posts.PostResponse;
import api.hm6.configuration.data.posts.PostResponseAfterModify;
import api.hm6.configuration.data.posts.PutPostReq;
import api.hm6.configuration.data.users.CreateUserReq;
import api.hm6.configuration.data.users.PutUserReq;
import api.hm6.configuration.data.users.UserResponse;
import api.hm6.configuration.data.users.UserResponseAfterModify;
import api.hm6.service.CommentsService;
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
    void viewAllComments() {
        commentsService.viewAllComments()
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
                                      .spec(responseSpecificationStatusOk);


        var modifyUser = usersService.putUser(reqModifyBodyForUser, createdUser.getId().toString())
                                      .then()
                                      .spec(responseSpecificationStatusOk)
                                      .extract()
                                      .as(UserResponseAfterModify.class);

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

        var viewCreatedPost = postsService.getPost(createdPost.getId().toString())
                                          .then()
                                          .spec(responseSpecificationStatusOk);

        var modifyPost = postsService.putPost(reqModifyBodyForPost, createdPost.getId().toString())
                                     .then()
                                     .spec(responseSpecificationStatusOk)
                                     .extract()
                                     .as(PostResponseAfterModify.class);

        PostsService.checkModifyPost(reqModifyBodyForPost, modifyPost);

        //создание, просмотр, апдейт коммента
        var reqCreateBodyForComment = generateCommentReqBody(modifyPost.getId(), modifyUser.getName(), modifyUser.getEmail());
        var reqModifyBodyForComment = generateCommentModifyReqBody(modifyPost.getId(), modifyUser.getName(), modifyUser.getEmail());
        var createdComment = commentsService.createComment(reqCreateBodyForComment)
                                      .then()
                                      .spec(responseSpecificationStatusCreated)
                                      .extract()
                                      .as(CommentResponse.class);

        CommentsService.checkCreateComment(reqCreateBodyForComment, createdComment);

        var viewCreatedComment = commentsService.getComment(createdComment.getId().toString())
                                          .then()
                                          .spec(responseSpecificationStatusOk);

        var modifyComment = commentsService.putComment(reqModifyBodyForComment, createdComment.getId().toString())
                                     .then()
                                     .spec(responseSpecificationStatusOk)
                                     .extract()
                                     .as(CommentResponseAfterModify.class);

        CommentsService.checkModifyComment(reqModifyBodyForComment, modifyComment);

        //удаление коммента, поста, юзера
        commentsService.deleteComment(createdComment.getId().toString())
                      .then()
                      .spec(responseSpecificationStatusNoContent);

        postsService.deletePost(createdPost.getId().toString())
            .then()
            .spec(responseSpecificationStatusNoContent);

        usersService.deleteUser(createdUser.getId().toString())
            .then()
            .spec(responseSpecificationStatusNoContent);
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

    private PutUserReq generateUserModifyReqBody() {
        return PutUserReq
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

    private PutPostReq generatePostModifyReqBody(Integer userId) {
        return PutPostReq
            .builder()
            .user_id(userId)
            .title(faker.lorem().words(3).toString())
            .body(faker.lorem().sentences(5).toString())
            .build();
    }

    private CreateCommentReq generateCommentReqBody(Integer postId, String name, String email) {
        return CreateCommentReq
            .builder()
            .post_id(postId)
            .name(name)
            .email(email)
            .body(faker.lorem().sentences(5).toString())
            .build();
    }

    private PutCommentReq generateCommentModifyReqBody(Integer postId, String name, String email) {
        return PutCommentReq
            .builder()
            .post_id(postId)
            .name(name)
            .email(email)
            .body(faker.lorem().sentences(5).toString())
            .build();
    }

}
