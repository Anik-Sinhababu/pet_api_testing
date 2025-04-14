package apiTest;

import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import apiEndpoint.UserEndpoint;
import apiPayload.User;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserTest {
    Faker faker;
    User userPayload;
    
    @BeforeClass
    public void setupData()
    {
        faker = new Faker();
        userPayload = new User();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5, 10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());
    }

    @BeforeMethod
    public void testLoginUser()
    {
        
    }

    @Test(priority = 1, enabled = false)
    public void testPostUser()
    {
        Response response;
        try {
            response = UserEndpoint.createUser(userPayload);
            response.then().log().all();
            System.out.println();
            Assert.assertEquals(response.getStatusCode(), 200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        

    }

    @Test(priority = 2, enabled = false)
    public void testGetUserByUsername()
    {
        System.out.println("[UserTest.java]: User name "+userPayload.getUsername());
        Response response = UserEndpoint.readUser(userPayload.getUsername());
        JsonPath jsonPath = response.jsonPath();
        response.then().log().all();
        System.out.println();
        Assert.assertEquals(response.getHeader("Content-Type"), "application/json");
        Assert.assertEquals(jsonPath.getInt("id"), userPayload.getId());
        Assert.assertEquals(jsonPath.getString("username"), userPayload.getUsername());
        Assert.assertEquals(jsonPath.getString("password"), userPayload.getPassword());
    }

    public void printDetails()
    {
        Response response = UserEndpoint.readUser(userPayload.getUsername());
        response.then().log().body();
    }

    @Test(priority = 3, enabled = false)
    public void testUpdateUserByUsername() {
        String option;
        Scanner sc = new Scanner(System.in);
        Faker faker = new Faker();
        Response response;
        String arr[] = {"a", "b", "c", "d", "e", "f", "q"};
        int i=0;

        do {
            System.out.println("________UPDATE INFO FOR USERNAME________: " + userPayload.getUsername() + "\n a) Update first-name\n b) Update last-name\n c) Update email-ID\n d) Update password\n e) Update Phone-no\n f) Update all info\n q) **** Press 'q' to QUIT ****");
            option = arr[i++]; // Trim to remove any leading/trailing whitespace

            switch (option) {
                case "a":
                    userPayload.setFirstName(faker.name().firstName());
                    response = UserEndpoint.updateUser(userPayload.getUsername(), userPayload);
                    response.then().log().body();
                    System.out.println("Updated response");
                    printDetails();
                    Assert.assertEquals(response.getStatusCode(), 200);
                    System.out.println("Updated first name to: " + userPayload.getFirstName());
                    System.out.print("\n\n\n");
                    break;
                case "b":
                    userPayload.setLastName(faker.name().lastName());
                    response = UserEndpoint.updateUser(userPayload.getUsername(), userPayload);
                    response.then().log().body();
                    System.out.println("Updated response");
                    printDetails();
                    System.out.println();
                    Assert.assertEquals(response.getStatusCode(), 200);
                    System.out.println("Updated last name to: " + userPayload.getLastName());
                    System.out.print("\n\n\n");
                    break;
                case "c":
                    userPayload.setEmail(faker.internet().safeEmailAddress());
                    response = UserEndpoint.updateUser(userPayload.getUsername(), userPayload);
                    response.then().log().body();
                    System.out.println("Updated response");
                    printDetails();
                    System.out.println();
                    Assert.assertEquals(response.getStatusCode(), 200);
                    System.out.println("Updated email ID to: " + userPayload.getEmail());
                    System.out.print("\n\n\n");
                    break;
                case "d":
                    userPayload.setPassword(faker.internet().password(5, 10));
                    response = UserEndpoint.updateUser(userPayload.getUsername(), userPayload);
                    response.then().log().body();
                    System.out.println("Updated response");
                    printDetails();
                    System.out.println();
                    Assert.assertEquals(response.getStatusCode(), 200);
                    System.out.println("Updated password to: " + userPayload.getPassword());
                    System.out.print("\n\n\n");
                    break;
                case "e":
                    userPayload.setPhone(faker.phoneNumber().cellPhone());
                    response = UserEndpoint.updateUser(userPayload.getUsername(), userPayload);
                    response.then().log().body();
                    System.out.println("Updated response");
                    printDetails();
                    System.out.println();
                    Assert.assertEquals(response.getStatusCode(), 200);
                    System.out.println("Updated phone number to: " + userPayload.getPhone());
                    System.out.print("\n\n\n");
                    break;
                case "f":
                    userPayload.setFirstName(faker.name().firstName());
                    userPayload.setLastName(faker.name().lastName());
                    userPayload.setEmail(faker.internet().safeEmailAddress());
                    userPayload.setPassword(faker.internet().password(5, 10));
                    userPayload.setPhone(faker.phoneNumber().cellPhone());
                    response = UserEndpoint.updateUser(userPayload.getUsername(), userPayload);
                    response.then().log().body();
                    System.out.println("Updated response");
                    printDetails();
                    System.out.println();
                    Assert.assertEquals(response.getStatusCode(), 200);
                    System.out.println("Updated all info:");
                    System.out.println("First name: " + userPayload.getFirstName());
                    System.out.println("Last name: " + userPayload.getLastName());
                    System.out.println("Email ID: " + userPayload.getEmail());
                    System.out.println("Password: " + userPayload.getPassword());
                    System.out.println("Phone number: " + userPayload.getPhone());
                    System.out.print("\n\n\n");
                    break;
                case "q":
                    System.out.println("Exiting update menu.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

        } while (!option.equals("q"));
        sc.close(); 
    }

    @Test(priority = 4, enabled = false)
    public void deleteUserByUsername()
    {
        Response response = UserEndpoint.deleteUser(userPayload.getUsername());
        response.then().log().body();
        System.out.println();
        Assert.assertEquals(response.statusCode(), 200);

    }
}
