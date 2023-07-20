package pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class POJO_HerokuAppBooking {
    /*
    {
    	                "firstname" : "Ali",
    	                "lastname" : “Bak",
    	                "totalprice" : 500,
    	                "depositpaid" : false,
    	                "bookingdates" : {
    	                         "checkin" : "2021-06-01",
    	                         "checkout" : "2021-06-10"
    	                                  },
    	                "additionalneeds" : "wi-fi"
    	            }
     */

    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private POJO_HerokuAppBookingDates bookingdates;
    private String additionalneeds;


}
