package api.hm6;

import api.hm6.configuration.ConfigProvider;
import api.hm6.service.CommentsService;
import api.hm6.service.PostsService;
import api.hm6.service.UsersService;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseApi {
    /*protected DocumentContext bodyUser;
    void user() {
        try {
           bodyUser = JsonPath.parse(new File("src/main/resources/personBodyForHm6.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/


    protected static final String token = ConfigProvider.staticVeriables().getMyToken();
    protected static final String SERVICE_BASE_URI = "https://gorest.co.in";
    protected static final String SERVICE_BASE_PATH = "/public/v2";
    protected static final String gender = "male";
    protected static final String status = "active";

    protected ResponseSpecification responseSpecificationStatusOk;
    protected ResponseSpecification responseSpecificationStatusCreated;
    protected ResponseSpecification responseSpecificationStatusNoContent;
    protected RequestSpecification requestSpecification;
    protected UsersService usersService;
    protected PostsService postsService;
    protected CommentsService commentsService;
    protected Faker faker;


    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = SERVICE_BASE_URI;
        RestAssured.basePath = SERVICE_BASE_PATH;
    }

    @BeforeEach
    void setUp() {
        requestSpecification = new RequestSpecBuilder()
            .addHeader("content-type", "application/json")
            .addHeader("Authorization", "Bearer " + token)
            .log(LogDetail.ALL)
            .build();

        responseSpecificationStatusOk = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_OK)
            .build();

        responseSpecificationStatusCreated = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_CREATED)
            .build();

        responseSpecificationStatusNoContent = new ResponseSpecBuilder()
            .log(LogDetail.ALL)
            .expectStatusCode(HttpStatus.SC_NO_CONTENT)
            .build();

        faker = new Faker();

        usersService = new UsersService(requestSpecification);
        postsService = new PostsService(requestSpecification);
        commentsService = new CommentsService(requestSpecification);
    }
}
