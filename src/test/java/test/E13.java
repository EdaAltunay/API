package test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class E13 {
    /*
    http://dummy.restapiexample.com/api/v1/employee/3 url’ine bir GET request
gonderdigimizde donen response’un asagidaki gibi oldugunu test edin.
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
    public void test(){
        String url="http://dummy.restapiexample.com/api/v1/employee/3";

        JSONObject data=new JSONObject();
        data.put("id", 3)
             .put("employee_name", "Ashton Cox")
             .put("employee_salary", 86000)
             .put("employee_age", 66).put("profile_image", "");

        JSONObject expData=new JSONObject();
        expData.put("status", "success")
               .put("data",data)
               .put("message", "Successfully! Record has been fetched.");

        System.out.println("expData"+expData);
        /*
        expData:
         {
          "data":{
                  "profile_image":"",
                  "employee_name":"Ashton Cox",
                  "employee_salary":86000,
                  "id":3,
                  "employee_age":66
                  },
          "message":"Successfully! Record has been fetched.",
          "status":"success"
          }
        */
        Response response=given().when().get(url);
        JsonPath respJP = response.jsonPath();

        Assert.assertEquals(expData.get("message"),respJP.get("message"));
        Assert.assertEquals(expData.get("status"),respJP.get("status"));
        Assert.assertEquals(expData.getJSONObject("data").get("profile_image"),respJP.get("data.profile_image"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_name"),respJP.get("data.employee_name"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_salary"),respJP.get("data.employee_salary"));
        Assert.assertEquals(expData.getJSONObject("data").get("id"),respJP.get("data.id"));
        Assert.assertEquals(expData.getJSONObject("data").get("employee_age"),respJP.get("data.employee_age"));












    }
}
