package logic.common;

import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestHandler {

    public RequestSpecification setupRequestSpecification(Map<String, String> headers, String body){
        return given()
               .headers(headers)
               .log().all()
               .when()
               .body(body);
    }

    public RequestSpecification setupRequestSpecification(Map<String, String> headers, Map<String, String> pathParams){
        return given()
                .headers(headers)
                .pathParams(pathParams)
                .log().all();
    }

    public RequestSpecification setupRequestSpecification(Map<String, String> headers, Map<String, String> pathParams, String body){
        return given()
                .headers(headers)
                .pathParams(pathParams)
                .body(body)
                .log().all();
    }

    public RequestSpecification setupRequestSpecification(Map<String, String> formParams){
        return given()
                .formParams(formParams)
                .log().all()
                .when();
    }
}
