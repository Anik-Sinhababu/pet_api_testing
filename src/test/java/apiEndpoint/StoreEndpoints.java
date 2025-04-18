package apiEndpoint;

import static io.restassured.RestAssured.given;

import apiPayload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndpoints {

    private static final String API_KEY = "123456"; // Your API key

    public static Response getStoreDetails() {
        Response response = 
        given()
            .accept(ContentType.JSON)
            .header("api_key", API_KEY)
        .when()
            .get(Routes.get_Store_details);

        return response;
    }

    public static Response post_order(Store payload) {
        Response response = 
            given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("api_key", API_KEY)
                .body(payload)
            .when()
                .post(Routes.post_order);

            return response;
    }
}
