package test;

import baseUrl.HerokuAppBaseURL;
import baseUrl.JsonPlaceHolderBaseURL;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C16_BaseUrlHerokuapp extends HerokuAppBaseURL {
      /*
    Class icinde 2 Test metodu olusturun ve asagidaki testleri yapin
       */


    @Test
    public void get() {
        /*
           1- https://restful-booker.herokuapp.com/booking endpointine bir GET request
              gonderdigimizde donen response’un status code’unun 200 oldugunu ve
              Response’ta 12 booking oldugunu test edin
         */

        // 1- url hazirla
        specHerokuApp.pathParam("pp1", "booking");

        //2- expected data hazirla

        //3- response'u kaydet
        Response response = given().spec(specHerokuApp).when().get("/{pp1}");

        response.prettyPrint();

        // 4 - Assertion

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("bookingid", Matchers.hasItem(12));

    }

    @Test
    public void post() {

    /*
        2- https://restful-booker.herokuapp.com/booking endpointine asagidaki
           body’ye sahip bir POST request gonderdigimizde donen response’un status
           code’unun 200 oldugunu ve “firstname” degerinin “Ali” oldugunu test edin.

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
        // 1 - URL ve body hazirla

        specHerokuApp.pathParam("pp1", "booking");

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2021-06-01")
                .put("checkout", "2021-06-10");

        JSONObject reqBody = new JSONObject();
        reqBody.put("firstname", "Ali")
                .put("lastname", "Bak")
                .put("totalprice", 500)
                .put("depositpaid", false)
                .put("bookingdates", bookingdates)
                .put("additionalneeds", "wi-fi");

        //2- expected data hazirla

        //3- Response'u kaydet

        Response response=given()
                .spec(specHerokuApp)
                .contentType(ContentType.JSON)
                .when()
                .body(reqBody.toString())
                .post("/{pp1}");
        response.prettyPrint();

        /*
    {
    "bookingid": 833,
    "booking": {
        "firstname": "Ali",
        "lastname": "Bak",
        "totalprice": 500,
        "depositpaid": false,
        "bookingdates": {
            "checkin": "2021-06-01",
            "checkout": "2021-06-10"
                        },
        "additionalneeds": "wi-fi"
               }
     }

         */

        //4- Assertion

        response
                .then()
                .assertThat()
                .statusCode(200)
                .body("booking.firstname",Matchers.equalTo("Ali"));


    }
}