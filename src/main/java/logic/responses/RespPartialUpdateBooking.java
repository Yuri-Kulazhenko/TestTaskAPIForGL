package logic.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import logic.responses.inner.BookingDates;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPartialUpdateBooking {

    @SerializedName("firstname")
    @Expose
    private String firstName;

    @SerializedName("lastname")
    @Expose
    private String lastName;

    @SerializedName("totalprice")
    @Expose
    private int totalPrice;

    @SerializedName("depositpaid")
    @Expose
    private boolean depositPaid;

    @SerializedName("bookingdates")
    @Expose
    private BookingDates bookingDates;

    @SerializedName("additionalneeds")
    @Expose
    private String additionalNeeds;



}
