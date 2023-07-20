package test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;


public class E14 {
    /*
    C14_Put_SoftAssertIleExpectedDataTesti
https://dummy.restapiexample.com/api/v1/update/21 url’ine asagidaki body’ye sahip bir PUT
request gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
Request Body
{
"status": "success",
"data": {
        "name": “Ahmet",
        "salary": "1230",
        "age": "44",
        "id": 40
         }
}


 Expected Body
{
"status": "success",
"data":{
       "status": "success",
       "data": {
               "name": “Ahmet",
               "salary": "1230",
               "age": "44",
               "id": 40
               }
       },
"message": "Successfully! Record has been updated."
}
     */

    @Test
    public void put01() {
        // 1- url  ve request body hazirla

        String url = "https://dummy.restapiexample.com/api/v1/update/21";



        JSONObject data = new JSONObject();
        data.put("name", "Ahmet")
            .put("salary", "1230")
            .put("age", "44")
            .put("id", 40);

        JSONObject request = new JSONObject();
        request.put("status", "success")
                .put("data",data);
        System.out.println("request ::: "+request);
         /*
        request :::
        {
          "data":{
                 "name":"Ahmet",
                 "id":40,
                 "salary":"1230",
                 "age":"44"
                 },

          "status":"success"

         }
         */

        // 2- expected data hazirla


        JSONObject expectedData =new JSONObject();
        expectedData.put("status", "success")
                    .put("data",request)
                    .put("message", "Successfully! Record has been updated.");
        System.out.println("expectedData ::: " +expectedData );

        /*
        expectedData :::

        {
        "data":{
                "data":{
                       "name":"Ahmet",
                       "id":40,
                       "salary":"1230",
                       "age":"44"
                       },
               "status":"success"
                },
        "message":"Successfully! Record has been updated.",
        "status":"success"
        }

         */



        // 3 - Response kaydet

        Response response=given()
                              .contentType(ContentType.JSON)
                         .when()
                              .body(request.toString())
                              .put(url);
        response.prettyPrint();


        // 4 - Assertion

     JsonPath respJP = response.jsonPath();

        Assert.assertEquals(expectedData.get("status"),respJP.get("status"));
        Assert.assertEquals(expectedData.get("message"),respJP.get("message"));
        Assert.assertEquals(expectedData.getJSONObject("data").get("status"),respJP.get("data.status"));
        Assert.assertEquals(expectedData.getJSONObject("data").getJSONObject("data").get("name"),respJP.get("data.data.name"));
        Assert.assertEquals(expectedData.getJSONObject("data").getJSONObject("data").get("id"),respJP.get("data.data.id"));
        Assert.assertEquals(expectedData.getJSONObject("data").getJSONObject("data").get("salary"),respJP.get("data.data.salary"));
        Assert.assertEquals(expectedData.getJSONObject("data").getJSONObject("data").get("age"),respJP.get("data.data.age"));














    }


}
