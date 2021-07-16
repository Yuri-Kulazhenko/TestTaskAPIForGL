package logic.responses.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @SerializedName("firstname")
    @Expose
    private String firstname;

    @SerializedName("lastname")
    @Expose
    private String lastname;

    @SerializedName("totalprice")
    @Expose
    private int totalprice;

    @SerializedName("depositpaid")
    @Expose
    private boolean depositpaid;

    @SerializedName("bookingdates")
    @Expose
    private BookingDates bookingdates;

    @SerializedName("additionalneeds")
    @Expose
    private String additionalneeds;

}
