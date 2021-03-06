package httpBin.dynamicData;

import baseUrls.BaseHttpBin;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class C09_Links__GenerateAPageContainignNLinkstoOtherPagesWhichTodoSAme extends BaseHttpBin {
    /*
    Curl
Generate a page containing n links to other pages which do the same.
curl -X GET "http://httpbin.org/links/4/5" -H "accept: text/html"
Request URL
http://httpbin.org/links/4/5
Server response
Code	Details
200
Response body
Download
<html><head><title>Links</title></head><body><a href='/links/4/0'>0</a> <a href='/links/4/1'>1</a> <a href='/links/4/2'>2</a> <a href='/links/4/3'>3</a> </body></html>
Response headers
 access-control-allow-credentials: true
 access-control-allow-origin: *
 connection: keep-alive
 content-length: 167
 content-type: text/html; charset=utf-8
 date: Fri, 29 Jul 2022 13:01:12 GMT
 server: gunicorn/19.9.0
Responses
Code	Description
200
     */

    @Test
    public void http() throws IOException {
        URL url = new URL("http://httpbin.org/links/4/5");
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();
    }
    @Test
    public void req() {
        /*
            Curl
Generate a page containing n links to other pages which do the same.
curl -X GET "http://httpbin.org/links/4/5" -H "accept: text/html"
Request URL
http://httpbin.org/links/4/5
         */
        RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://httpbin.org/links/4/5")
                .setAccept("text/html").build();
        Response response=given()
                .spec(req)
                .when()
                .get();
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("text/html");
    }
    @Test
    public void res() {
                /*
            Curl
Generate a page containing n links to other pages which do the same.
curl -X GET "http://httpbin.org/links/4/5" -H "accept: text/html"
Request URL
http://httpbin.org/links/4/5
         */
        specHttpbin.pathParams("pp1","links","pp2",4,"pp3",5);
        Response response=given()
                .spec(specHttpbin)
                .accept("text/html")
                .when()
                .get("/{pp1}/{pp2}/{pp3}");
        response.prettyPrint();
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType("text/html");
    }
}
