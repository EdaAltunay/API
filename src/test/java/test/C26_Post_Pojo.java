package test;

import baseUrl.HerokuAppBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;



import pojos.POJO_HerokuAppBooking;
import pojos.POJO_HerokuAppBookingDates;
import pojos.POJO_HerokuAppExpectedBody;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C26_Post_Pojo extends HerokuAppBaseURL {
    /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id disinda asagidaki gibi oldugunu test edin.
    	                Request body
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


    	            	Response Body = Expected Data
    	            	{
                    "bookingid":24,
                    "booking":{
                        "firstname":"Ali",
                        "lastname":"Bak",
                        "totalprice":500,
                        "depositpaid":false,
                        "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                                        }
                        ,
                        "additionalneeds":"wi-fi"
                              }
                    }
         */


    @Test
    public void post01(){
        // 1- url ve request body hazirla
        specHerokuApp.pathParam("pp1","booking");

        POJO_HerokuAppBookingDates bookingdates = new POJO_HerokuAppBookingDates("2021-06-01","2021-06-10");

        POJO_HerokuAppBooking reqBody = new POJO_HerokuAppBooking("Ali","Bak",500,false,bookingdates,"wi-fi");

        //2 - expected body hazirla.
        POJO_HerokuAppExpectedBody expData = new POJO_HerokuAppExpectedBody(24,reqBody);

        //3- response'u kaydet.
        Response response = given()
                                    .spec(specHerokuApp)
                                    .contentType(ContentType.JSON)
                            .when()
                                    .body(reqBody)
                                    .post("/{pp1}");

        response.prettyPrint();

        //4- assertion
        POJO_HerokuAppExpectedBody respPojo = response.as(POJO_HerokuAppExpectedBody.class);

        assertEquals(expData.getBooking().getFirstname(), respPojo.getBooking().getFirstname() );
        assertEquals(expData.getBooking().getLastname() , respPojo.getBooking().getLastname());
        assertEquals(expData.getBooking().getTotalprice(),respPojo.getBooking().getTotalprice());
        assertEquals(expData.getBooking().isDepositpaid(),respPojo.getBooking().isDepositpaid());
        assertEquals(expData.getBooking().getAdditionalneeds(),respPojo.getBooking().getAdditionalneeds());
        assertEquals(expData.getBooking().getBookingdates().getCheckin(),
                     respPojo.getBooking().getBookingdates().getCheckin());
        assertEquals(expData.getBooking().isDepositpaid() , respPojo.getBooking().isDepositpaid());







    }
}
