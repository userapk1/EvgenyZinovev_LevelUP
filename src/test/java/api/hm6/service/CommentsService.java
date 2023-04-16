package api.hm6.service;

import api.hm6.BaseApi;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CommentsService extends BaseApi {

    private final RequestSpecification requestSpecification;
}
