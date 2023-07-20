package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import testData.TestDataDummy;

public class DummyBaseURL extends TestDataDummy {
    protected RequestSpecification specDummy;

    @Before
    public void setUp(){

        specDummy = new RequestSpecBuilder()
                .setBaseUri("http://dummy.restapiexample.com")
                .build();

    }

}
