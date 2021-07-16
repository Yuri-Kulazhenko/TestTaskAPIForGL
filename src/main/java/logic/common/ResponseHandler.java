package logic.common;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ResponseHandler {

    public static Response obtainPostResponse(String URL, RequestSpecification spec) {
        return spec.when()
                .post(URL)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response obtainGetResponse(String URL, RequestSpecification spec) {
        return spec.when()
                .get(URL)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response obtainPutResponse(String URL, RequestSpecification spec) {
        return spec.when()
                .put(URL)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response obtainPatchResponse(String URL, RequestSpecification spec) {
        return spec.when()
                .patch(URL)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response obtainDeleteResponse(String URL, RequestSpecification spec) {
        return spec.when()
                .delete(URL)
                .then()
                .log().all()
                .extract().response();
    }
}
