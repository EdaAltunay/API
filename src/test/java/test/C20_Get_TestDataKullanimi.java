package test;

import baseUrl.DummyBaseURL;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.TestDataJsonPlace;


import static io.restassured.RestAssured.given;

public class C20_Get_TestDataKullanimi extends DummyBaseURL {
    /*

     http://dummy.restapiexample.com/api/employee/3 url’ine bir GET request
     gonderdigimizde donen response’un status code’unun 200, content Type’inin
     application/json ve body’sinin asagidaki gibi oldugunu test edin.
    Response Body
{
"status": "success",
"data": {
       "id": 3,
       "employee_name": "Ashton Cox",
       "employee_salary": 86000,
       "employee_age": 66,
       "profile_image": ""
         },
"message": "Successfully! Record has been fetched."
}
     */
    @Test
            public void get01(){
        //1- Url hazirla
        specDummy.pathParams("pp1","api","pp2","v1","pp3","employee","pp4",3);

        // 2 - Expected Data hazirla
        JSONObject expectedData=expectedBodyOlusturJSON();

        //3- Response'u kaydet.
        Response response=given().spec(specDummy).when().get("/{pp1}/{pp2}/{pp3}/{pp4}");

        //4- Assertion
        JsonPath respJP = response.jsonPath();

        Assert.assertEquals(basariliStatusCode,response.getStatusCode());
        Assert.assertEquals(contentType,response.getContentType());
        Assert.assertEquals(expectedData.get("status"),respJP.get("status"));
        Assert.assertEquals(expectedData.get("message"),respJP.get("message"));
        Assert.assertEquals(expectedData.getJSONObject("data").get("id"),respJP.get("data.id"));
        Assert.assertEquals(expectedData.getJSONObject("data").get("employee_name"),respJP.get("data.employee_name"));
        Assert.assertEquals(expectedData.getJSONObject("data").get("employee_salary"),respJP.get("data.employee_salary"));
        Assert.assertEquals(expectedData.getJSONObject("data").get("employee_age"),respJP.get("data.employee_age"));
        Assert.assertEquals(expectedData.getJSONObject("data").get("profile_image"),respJP.get("data.profile_image"));













    }
}
