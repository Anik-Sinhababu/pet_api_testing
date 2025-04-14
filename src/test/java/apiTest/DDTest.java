package apiTest;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import apiEndpoint.PetEndpoints;
import apiEndpoint.UserEndpoint;
import apiPayload.Pet;
import apiPayload.User;
import apiUtilities.DataProviders;
import apiUtilities.Reporter;
import apiUtilities.XLReader;
import io.restassured.response.Response;

public class DDTest {
    private static final Logger logger = LogManager.getLogger(DDTest.class);

    User payload;
    Pet petPayload;
    int row = 1;
    XLReader xl = new XLReader(System.getProperty("user.dir") + "/userdata.xlsx");
    ExtentReports reports, petReports;

    ExtentTest postTest, getUserTest, deleteUserTest, updateUserTest, postPetTest, getPetDetailsTest;

    @BeforeClass
    public void createReport() {
        // User Test 
        reports = Reporter.generateReports("User");
        postTest = reports.createTest("Post Method TestCases");
        getUserTest = reports.createTest("Get-User Method TestCases");
        deleteUserTest = reports.createTest("Delete-User TestCases");
        updateUserTest = reports.createTest("Update-User TestCases");

        // Pet Test
        petReports = Reporter.generateReports("Pet");
        postPetTest = petReports.createTest("Upload Pet Image");
        getPetDetailsTest = petReports.createTest("Get Pet Details");
    }

    @Test(dataProvider = "Data", dataProviderClass = DataProviders.class, priority = 1, enabled = true)
    public void testPostUser(String id, String username, String firstname, String lastname, String email, String password, String phoneno) throws InterruptedException, IOException {
        logger.info("*********************************************************************************************************************************************************");
        logger.info("Starting testPostUser with username: {}", username);
        payload = new User();
        int Id = Integer.parseInt(id);
        payload.setId(Id);
        payload.setUsername(username);
        payload.setFirstName(firstname);
        payload.setLastName(lastname);
        payload.setEmail(email);
        payload.setPassword(password);
        payload.setPhone(phoneno);
        Response response = UserEndpoint.createUser(payload);
        response.then().log().body();
        logger.info("Response: {}", response.asString());
        if (response.statusCode() == 200) {
            xl.setCellData("Status", row, 3, "Pass");
            xl.fillGreenColor("Status", row++, 3);
            postTest.log(Status.PASS, "User creation successful. Username: " + username + ", Email: " + email + ". Status code: " + response.statusCode());
        } else {
            xl.setCellData("Status", row, 3, "Fail");
            xl.fillRedColor("Status", row++, 3);
            postTest.log(Status.FAIL, "User creation failed with username: " + username + ", Email: " + email + ". Status code: " + response.statusCode());
            logger.error("User creation failed with username: {}, Email: {}. Status code: {}", username, email, response.statusCode());
        }
        Assert.assertEquals(response.statusCode(), 200);
    }

    int row2 = 1;

    @Test(dataProvider = "UserNames", dataProviderClass = DataProviders.class, priority = 2, enabled = true)
    public void testReadUser(String username) throws IOException {
        logger.info("*********************************************************************************************************************************************************");
        logger.info("Starting testReadUser with username: {}", username);
        Response response = UserEndpoint.readUser(username);
        response.then().log().body();
        logger.info("Response: {}", response.asString());
        if (response.statusCode() == 200) {
            xl.setCellData("Status", row2, 1, "Pass");
            xl.fillGreenColor("Status", row2++, 1);
            getUserTest.log(Status.PASS, "User read successful. Username: " + username + ". Status code: " + response.statusCode());
        } else {
            xl.setCellData("Status", row2, 1, "Fail");
            xl.fillRedColor("Status", row2++, 1);
            getUserTest.log(Status.FAIL, "User read failed. Username: " + username + ". Status code: " + response.statusCode());
            logger.error("User read failed. Username: {}. Status code: {}", username, response.statusCode());
        }
        Assert.assertEquals(username, response.body().jsonPath().get("username"));
    }

    int row3 = 1;

    @Test(dataProvider = "UserNames", dataProviderClass = DataProviders.class, priority = 3, enabled = true)
    public void testDeleteUser(String username) throws IOException {
        logger.info("*********************************************************************************************************************************************************");
        logger.info("Starting testDeleteUser with username: {}", username);
        Response response = UserEndpoint.deleteUser(username);
        response.then().log().body(true);
        logger.info("Response: {}", response.asString());
        if (response.statusCode() == 200) {
            xl.setCellData("Status", row3, 4, "Pass");
            xl.fillGreenColor("Status", row3++, 4);
            deleteUserTest.log(Status.PASS, "User deletion successful. Username: " + username + ". Status code: " + response.statusCode());
        } else {
            xl.setCellData("Status", row3, 4, "Fail");
            xl.fillRedColor("Status", row3++, 4);
            deleteUserTest.log(Status.FAIL, "User deletion failed. Username: " + username + ". Status code: " + response.statusCode());
            logger.error("User deletion failed. Username: {}. Status code: {}", username, response.statusCode());
        }
        Assert.assertEquals(response.statusCode(), 200);
    }

    int row4 = 1;

    @Test(dataProvider = "UpdatedData", dataProviderClass = DataProviders.class, priority = 4, enabled = true)
    public void testUpdateUser(String id, String username, String firstname, String lastname, String email, String password, String phoneno) throws IOException {
        logger.info("*********************************************************************************************************************************************************");
        logger.info("Starting testUpdateUser with username: {}", username);
        payload = new User();
        int Id = Integer.parseInt(id);
        payload.setId(Id);
        payload.setUsername(username);
        payload.setLastName(lastname);
        payload.setPassword(password);
        payload.setEmail(email);
        payload.setFirstName(firstname);
        payload.setPhone(phoneno);
        Response response = UserEndpoint.updateUser(username, payload);
        response.then().log().everything();
        logger.info("Response: {}", response.asString());
        if (response.statusCode() == 200) {
            xl.setCellData("Status", row4, 2, "Pass");
            xl.fillGreenColor("Status", row4++, 2);
            updateUserTest.log(Status.PASS, "User update successful. Username: " + username + ", Email: " + email + ". Status code: " + response.statusCode());
        } else {
            xl.setCellData("Status", row4, 2, "Fail");
            xl.fillRedColor("Status", row4++, 2);
            updateUserTest.log(Status.FAIL, "User update failed with username: " + username + ", Email: " + email + ". Status code: " + response.statusCode());
            logger.error("User update failed with username: {}, Email: {}. Status code: {}", username, email, response.statusCode());
        }
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(dataProvider = "petimage", dataProviderClass = DataProviders.class, priority = 5, enabled = true)
    public void testPostPet(String petID, String imageFile, String additionalMetadata) {
        logger.info("*********************************************************************************************************************************************************");
        logger.info("Starting testPostPet with petID: {}", petID);
        petPayload = new Pet();
        int petid = Integer.parseInt(petID);
        petPayload.setPetID(petid);
        File image = new File(System.getProperty("user.dir") + imageFile);
        petPayload.setImageFile(image);
        petPayload.setAdditionalMetadata(additionalMetadata);

        Response response = PetEndpoints.uploadPetImage(petPayload);
        response.then().log().all();
        logger.info("Response: {}", response.asString());
        String message = response.jsonPath().getString("message");
        String expectedMessage = "additionalMetadata: " + additionalMetadata;
        if (response.statusCode() == 200 && message.contains(expectedMessage)) {
            postPetTest.log(Status.INFO, "Successfully uploaded: " + additionalMetadata + ", with Status-Code: " + response.statusCode());
        } else {
            postPetTest.log(Status.WARNING, "Could not upload: " + additionalMetadata + ", with Status-Code: " + response.statusCode());
            logger.error("Could not upload: {}. Status code: {}", additionalMetadata, response.statusCode());
        }
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(message.contains(expectedMessage));
    }
    @Test(dataProvider = "PetIdProvider", dataProviderClass = DataProviders.class, enabled = true, priority = 6)
    public void getPetDetails(String petID) {
        logger.info("*********************************************************************************************************************************************************");
        logger.info("Starting getPetDetails with petID: {}", petID);
        int petid = Integer.parseInt(petID);
        Response response = PetEndpoints.get_pet_Method(petid);
        response.then().log().all();
        logger.info("Response: {}", response.asString());

        if (response.statusCode() == 200) {
            getPetDetailsTest.log(Status.INFO, "Successfully retrieved data from the API. Pet ID: " + petID + ". Status code: " + response.statusCode());
        } else {
            getPetDetailsTest.log(Status.FAIL, "Could not retrieve data from API. Pet ID: " + petID + ". Status code: " + response.statusCode());
            logger.error("Could not retrieve data from API. Pet ID: {}. Status code: {}", petID, response.statusCode());
        }
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @AfterClass
    public void closeReport() {
        logger.info("Flushing reports");
        reports.flush();
        petReports.flush();
    }
}