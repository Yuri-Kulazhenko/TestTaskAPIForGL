package logic.responses.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDates {

    @SerializedName("checkin")
    @Expose
    private String checkin;

    @SerializedName("checkout")
    @Expose
    private String checkout;

}
