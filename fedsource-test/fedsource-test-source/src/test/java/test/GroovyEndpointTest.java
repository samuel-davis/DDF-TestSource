package test;

import com.jayway.jsonpath.JsonPath;

import org.junit.Test;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.RestAssuredConfig.newConfig;
import static org.junit.Assert.assertEquals;

/**
 * This software was created for the Open Architecture Distributed Common Ground System
 * Modernization All rights to this software belong to  appropriate licenses and restrictions apply.
 * Created by Samuel Davis on 3/25/16.
 */

public class GroovyEndpointTest extends BaseRestTest {
    protected final static String GROOVY_ENDPOINT_ADDRESS = "https://localhost:8993/services/test/getGroovyResults";


    @Test
    public void testGroovyEndpointAmount() {
        if (!serverUp) {
            return;
        }
        //Create the workspace and add two comments to it.

        String response = given()
                .config(newConfig().sslConfig(sslConfig))
                .contentType(MediaType.APPLICATION_JSON)
                .when()
                .get(GROOVY_ENDPOINT_ADDRESS+"?amount=2")
                .body()
                .asString();
        ArrayList<String> results = JsonPath.read(response, "data");
        assertEquals(results.size(),2);
       /* assertEquals(resultList.get(0),MGRS_RESPONSE);
        assertEquals(resultList.get(1),DD_RESPONSE);
        assertEquals(resultList.get(2),DM_RESPONSE);
        assertEquals(resultList.get(3),DMS_RESPONSE);*/
    }


}
