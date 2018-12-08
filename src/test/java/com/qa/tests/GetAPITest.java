package com.qa.tests;

import com.qa.base.TestBase;
import com.qa.client.WebClient;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class GetAPITest extends TestBase {

    TestBase testBase;
    String serviceUrl;
    String apiUrl;
    String url;
    WebClient webClient;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() throws ClientProtocolException, IOException {
        testBase = new TestBase();
        serviceUrl = prop.getProperty("URL");
        apiUrl  = prop.getProperty("serviceURL");
        //https://reqres.in/api/users

        url = serviceUrl + apiUrl;

    }


    @Test
    public void getTest() throws ClientProtocolException, IOException, JSONException {
        webClient = new WebClient();
        closeableHttpResponse = webClient.get(url);

        //a. Status Code
        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code is: " + statusCode);


        //b. Json String
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response JSON from API is: " + responseJson);

        //c. All Headers
        Header[] headersArray = closeableHttpResponse.getAllHeaders();

        HashMap<String, String> allHeaders = new HashMap<String, String>();

        for(Header header : headersArray){
            allHeaders.put(header.getName(), header.getValue());
        }

        System.out.println("Headers Array: " + allHeaders);
    }
}
