package api.hm6;

import api.hm6.configuration.ConfigProvider;
import com.github.javafaker.Faker;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import java.io.File;
import java.io.IOException;

public abstract class BaseApi {
    protected DocumentContext body;
    void createPerson() {
        try {
           body = JsonPath.parse(new File("src/main/resources/createPersonBodyForHm6.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static final String token = ConfigProvider.staticVeriables().getMyToken();
    protected static final String SERVICE_BASE_URI = "https://gorest.co.in";
    protected static final String SERVICE_BASE_PATH = "/public/v2";

    protected ResponseSpecification responseSpecificationStatusOk;
    protected ResponseSpecification responseSpecificationStatusCreated;
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

        faker = new Faker();
    }
}
