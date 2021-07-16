package logic.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import logic.responses.inner.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespCreateBooking {

    @SerializedName("bookingid")
    @Expose
    private String bookingId;

    @SerializedName("booking")
    @Expose
    private Booking booking;

}
