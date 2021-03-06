package restfulTest;

import baseUrls.BaseUrlRestful;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;
import utilities.ReusableMethods;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class C03_ReturnSpecificBooking extends BaseUrlRestful {
    @Test
    public void returnspecificUser() {
        //https://restful-booker.herokuapp.com/booking/:id
        specRestful.pathParams("pp1","booking","pp2",61);
        //specRestful.pathParam("pp1","booking");
        Response response=
                given()
                        .spec(specRestful)
                        .when()
                        .get("/{pp1}/{pp2}");
        response.prettyPrint();
        // 1 -Request url ve body'sini hazirlamak
        // 2- Expected Data'yi hazirla
        // 3- Response'u kaydet
        /*
        {
    "firstname": "Samantha",
    "lastname": "Garcia",
    "totalprice": 167,
    "depositpaid": false,
    "bookingdates": {
        "checkin": "2022-07-19",
        "checkout": "2022-07-27"
    },
    "additionalneeds": "breakfast"
}
         */

        // 4- Assertion'lari yap
        response.then()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname", equalTo("Joshua")
                ,"lastname",equalTo("Robinson")
                ,"totalprice",equalTo(138)
                ,"depositpaid",equalTo(false)
                ,"bookingdates.checkin",equalTo("2022-10-10")
                ,"bookingdates.checkout",equalTo("2022-10-20")
                ,"additionalneeds",equalTo("early checkin"));
    }
}
