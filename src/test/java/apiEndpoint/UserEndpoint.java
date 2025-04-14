package apiEndpoint;
import static io.restassured.RestAssured.given;

import apiPayload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoint {

    public static Response loginUser(String username, String password)
    {
        System.out.println("Logging user with username: " + username);
        Response response = given()
        .queryParam("username", username)
        .queryParam("password", password)

        .when()
            .get(Routes.login_url);

        return response;
    }

    public static Response createUser(User payload) throws InterruptedException
    {
        System.out.println("POST User name is :"+payload.getUsername());

        Response response = 
        given()
            .contentType(ContentType .JSON)
            .accept(ContentType.JSON)
            .body(payload)

        .when()
            .post(Routes.post_url);
            Thread.sleep(1000);
        return response;
    }

    public static Response readUser(String userName)
    {

        Response response = given()
            .pathParam("username", userName)
        .when()
            .get(Routes.get_url);
        return response;
    }

    public static Response updateUser(String userName, User Payload)
    {
        Response response = 
            given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(Payload)
            .when()
                .put(Routes.update_url);

            return response;
    }

    public static Response deleteUser(String userName)
    {
        Response response = given()
            .pathParam("username", userName)

        .when()
            .delete(Routes.delete_url);
        
        return response;
    }


}
