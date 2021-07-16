package tests;


import io.restassured.response.Response;

import logic.common.*;
import logic.responses.*;

import org.hamcrest.core.StringContains;
import org.json.JSONObject;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static logic.common.EndPoints.*;
import static logic.common.RequestJsonFiles.AUTH_JSON;
import static logic.common.RequestJsonFiles.REQUEST_TEMPLATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;


public class RestfulBookerTest extends ResponseHandler {

    private final RequestHandler requestHandler = new RequestHandler();
    private RespCreateToken respCreateToken = null;
    private RespCreateBooking respCreateBooking = null;
    private RespGetBooking respGetBooking = null;
    private JSONObject bodyTemplate = null;

    @BeforeTest
    public void install() {
        bodyTemplate = FileManager.getJsonFromFile(REQUEST_TEMPLATE.getFilePath());
        String body = FileManager.getJsonFromFile(AUTH_JSON.getFilePath()).toString();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        Response response = ResponseHandler.obtainPostResponse(CREATE_TOKEN.getEndPoint(),
                requestHandler.setupRequestSpecification(headers, body));
        respCreateToken = ResponseMapper.parseResponse(response.getBody().asString(), RespCreateToken.class);
    }

    @AfterTest
    public void cleanUp() {

    }

    @Test
    public void test001_createBooking() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("Cookie", "token=" + respCreateToken.getToken());

        Response response = ResponseHandler.obtainPostResponse(CREATE_BOOKING.getEndPoint(),
                requestHandler.setupRequestSpecification(headers, bodyTemplate.toString()));
        respCreateBooking = ResponseMapper.parseResponse(response.getBody().asString(), RespCreateBooking.class);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getStatusLine(), StringContains.containsString("OK"));
        assertThat(Integer.valueOf(respCreateBooking.getBookingId()), greaterThan(0));
    }

    @Test
    public void test002_getBookingInfo() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", respCreateBooking.getBookingId());

        Response response = ResponseHandler.obtainGetResponse(GET_BOOKING.getEndPoint(),
                requestHandler.setupRequestSpecification(headers, pathParams));
        respGetBooking = ResponseMapper.parseResponse(response.getBody().asString(), RespGetBooking.class);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getStatusLine(), StringContains.containsString("OK"));
        assertThat(respGetBooking.getFirstName(),
                StringContains.containsString(bodyTemplate.getString("firstname")));
        assertThat(respGetBooking.getLastName(),
                StringContains.containsString(bodyTemplate.getString("lastname")));
        assertThat(respGetBooking.getTotalPrice(),
                is(bodyTemplate.getInt("totalprice")));
        assertThat(respGetBooking.isDepositPaid(),
                is(bodyTemplate.getBoolean("depositpaid")));
        assertThat(respGetBooking.getAdditionalneeds(),
                StringContains.containsString(bodyTemplate.getString("additionalneeds")));
        assertThat(respGetBooking.getBookingDates().getCheckin(),
                StringContains.containsString(bodyTemplate.getJSONObject("bookingdates").getString("checkin")));
        assertThat(respGetBooking.getBookingDates().getCheckout(),
                StringContains.containsString(bodyTemplate.getJSONObject("bookingdates").getString("checkout")));
    }

    @Test
    public void test003_updateBookingInfo() {

        JSONObject body = bodyTemplate;
        bodyTemplate.put("totalprice", "2222");

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("Cookie", "token=" + respCreateToken.getToken());

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", respCreateBooking.getBookingId());

        Response response = ResponseHandler.obtainPutResponse(UPDATE_BOOKING.getEndPoint(),
                requestHandler.setupRequestSpecification(headers, pathParams, body.toString()));
        RespUpdateBooking respUpdateBooking = ResponseMapper.parseResponse(response.getBody().asString(), RespUpdateBooking.class);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getStatusLine(), StringContains.containsString("OK"));
        assertThat(respUpdateBooking.getFirstName(),
                StringContains.containsString(body.getString("firstname")));
        assertThat(respUpdateBooking.getLastName(),
                StringContains.containsString(body.getString("lastname")));
        assertThat(respUpdateBooking.getTotalPrice(),
                is(body.getInt("totalprice")));
        assertThat(respUpdateBooking.isDepositPaid(),
                is(body.getBoolean("depositpaid")));
        assertThat(respUpdateBooking.getAdditionalNeeds(),
                StringContains.containsString(body.getString("additionalneeds")));
        assertThat(respUpdateBooking.getBookingDates().getCheckin(),
                StringContains.containsString(body.getJSONObject("bookingdates").getString("checkin")));
        assertThat(respUpdateBooking.getBookingDates().getCheckout(),
                StringContains.containsString(body.getJSONObject("bookingdates").getString("checkout")));
    }

    @Test
    public void test004_partialUpdateBookingInfo() {
        JSONObject body = new JSONObject();
        body.put("totalprice", "1515");

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("Cookie", "token=" + respCreateToken.getToken());

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", respCreateBooking.getBookingId());

        Response response = ResponseHandler.obtainPatchResponse(PARTIAL_UPDATE_BOOKING.getEndPoint(),
                requestHandler.setupRequestSpecification(headers, pathParams, body.toString()));
        RespPartialUpdateBooking respPartialUpdateBooking = ResponseMapper.parseResponse(response.getBody().asString(), RespPartialUpdateBooking.class);

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getStatusLine(), StringContains.containsString("OK"));
        assertThat(respPartialUpdateBooking.getTotalPrice(), is(body.getInt("totalprice")));
    }

    @Test
    public void test005_getAllBookingRequests() {
        Map<String, String> formParams = new HashMap<>();
        formParams.put("firstname", respGetBooking.getFirstName());
        formParams.put("lastname", respGetBooking.getLastName());

        Response response = ResponseHandler.obtainGetResponse(GET_BOOKING_IDS.getEndPoint(),
                requestHandler.setupRequestSpecification(formParams));

        List<RespGetBookingIds> listOfBookingIds = Arrays.asList(ResponseMapper.parseResponse(response.getBody().asString(), RespGetBookingIds[].class));

        assertThat(response.getStatusCode(), is(200));
        assertThat(response.getStatusLine(), StringContains.containsString("OK"));
        assertThat(listOfBookingIds.stream()
                .anyMatch(x->x.getBookingId()==Integer.parseInt(respCreateBooking.getBookingId())), is(true));
    }

    @Test
    public void test006_DeleteBooking() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Cookie", "token=" + respCreateToken.getToken());

        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("id", respCreateBooking.getBookingId());

        Response response = ResponseHandler.obtainDeleteResponse(DELETE_BOOKING.getEndPoint(),
                requestHandler.setupRequestSpecification(headers, pathParams));

        assertThat(response.getStatusCode(), is(201));
        assertThat(response.getStatusLine(), StringContains.containsString("Created"));
    }
}
