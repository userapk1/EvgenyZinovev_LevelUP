package api.hm6;

import api.hm6.configuration.ConfigProvider;
import com.github.javafaker.Faker;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import java.io.File;
import java.io.IOException;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseApi {
    protected DocumentContext bodyPerson;
    protected DocumentContext updateBodyPerson;
    protected DocumentContext bodyPost;
    protected DocumentContext updateBodyPost;
    void person() {
        try {
           bodyPerson = JsonPath.parse(new File("src/main/resources/personBodyForHm6.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void personUp() {
        try {
            updateBodyPerson = JsonPath.parse(new File("src/main/resources/personBodyForHm6.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void post() {
        try {
            bodyPost = JsonPath.parse(new File("src/main/resources/postsBodyForHm6.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void postUp() {
        try {
            updateBodyPost = JsonPath.parse(new File("src/main/resources/postsBodyForHm6.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static final String token = ConfigProvider.staticVeriables().getMyToken();
    protected static final String SERVICE_BASE_URI = "https://gorest.co.in";
    protected static final String SERVICE_BASE_PATH = "/public/v2";

    protected ResponseSpecification responseSpecificationStatusOk;
    protected ResponseSpecification responseSpecificationStatusCreated;
    protected ResponseSpecification responseSpecificationStatusNoContent;
    protected Faker faker;

    @BeforeAll
    static void beforeAll() {
        RestAssured.baseURI = SERVICE_BASE_URI;
        RestAssured.basePath = SERVICE_BASE_PATH;
    }

    @BeforeEach
    void setUp() {
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
    }
}
