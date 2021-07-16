package logic.common;


public enum EndPoints {

    CREATE_TOKEN ("https://restful-booker.herokuapp.com/auth"),
    GET_BOOKING_IDS ("https://restful-booker.herokuapp.com/booking"),
    GET_BOOKING ("https://restful-booker.herokuapp.com/booking/{id}"),
    CREATE_BOOKING  ("https://restful-booker.herokuapp.com/booking"),
    UPDATE_BOOKING ("https://restful-booker.herokuapp.com/booking/{id}"),
    PARTIAL_UPDATE_BOOKING ("https://restful-booker.herokuapp.com/booking/{id}"),
    DELETE_BOOKING ("https://restful-booker.herokuapp.com/booking/{id}");

    private final String endPoint;

    public String getEndPoint() {
        return endPoint;
    }

    EndPoints(String endPoingt) {
        this.endPoint = endPoingt;
    }
}
