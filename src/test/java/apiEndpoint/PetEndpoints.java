package apiEndpoint;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.File;

import apiPayload.Pet;

public class PetEndpoints {
    
    public static Response uploadPetImage(Pet petPayload)
    {
        Response response = 
        given()
            .contentType(ContentType.MULTIPART)
            .multiPart("file", petPayload.getImageFile())
            .multiPart("additionalMetadata", petPayload.getAdditionalMetadata())
            .pathParam("petId", petPayload.getPetID())
        .when()
            .post(Routes.post_pet_utl);
        return response;
    }

    public static Response get_pet_Method(int petID)
    {
        Response response = 
        given()
            .accept(ContentType.JSON)
            .pathParam("petId", petID)
        .when()
            .get(Routes.get_pet_url);
        
            return response;
    }
}
