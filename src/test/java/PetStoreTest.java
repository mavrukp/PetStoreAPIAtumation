import api.PetStoreAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PetStoreTest {

    private static Logger logger = Logger.getLogger(PetStoreTest.class);

    PetStoreAPI petStoreAPI = new PetStoreAPI();

    @Test (priority = 1)
    public void createUserPostRequest(){
        Response response = petStoreAPI.createUser();
        try {
            Assert.assertEquals(response.jsonPath().getInt("code"), 200);
            Assert.assertEquals(response.jsonPath().getString("message"), "68858015");
            Assert.assertEquals(response.statusCode(), 200);
        }catch(NullPointerException e){
            logger.error("NullPointerException Exception in createUserPostRequest ");
            e.printStackTrace();
        }
    }

    @Test (dependsOnMethods = { "createUserPostRequest" })
    public void readUserGetRequest(){
        Response response = petStoreAPI.readUser();
        try {
            Assert.assertEquals(response.jsonPath().getInt("id"), 68858015);
            Assert.assertEquals(response.jsonPath().getString("username"), "usrnm123");
            Assert.assertEquals(response.jsonPath().getString("firstName"), "testfirstname");
            Assert.assertEquals(response.jsonPath().getString("lastName"), "testlastname");
            Assert.assertEquals(response.jsonPath().getString("email"), "testemail");
            Assert.assertEquals(response.jsonPath().getString("password"), "testpassword");
            Assert.assertEquals(response.jsonPath().getString("phone"), "2221115566");
            Assert.assertEquals(response.jsonPath().getInt("userStatus"), 68858015);
            Assert.assertEquals(response.statusCode(), 200);
            response.then().assertThat().body(matchesJsonSchemaInClasspath("getResponseSchema.json"));
        }catch(NullPointerException e){
            logger.error("NullPointerException Exception in readUserGetRequest ");
            e.printStackTrace();
        }


    }

    @Test (dependsOnMethods = { "readUserGetRequest" })
    public void updateUserPutRequest(){
        Response response = petStoreAPI.updateUser();
        try {
            Assert.assertEquals(response.jsonPath().getInt("code"), 200);
            Assert.assertEquals(response.jsonPath().getString("message"), "68858015");
            Assert.assertEquals(response.statusCode(), 200);
        }catch(NullPointerException e){
            logger.error("NullPointerException Exception in updateUserPutRequest ");
            e.printStackTrace();
        }
    }

    @Test (dependsOnMethods = { "updateUserPutRequest" })
    public void deleteUserDeleteRequest(){
        Response response = petStoreAPI.deleteUser();
        try {
            Assert.assertEquals(response.jsonPath().getInt("code"), 200);
            Assert.assertEquals(response.jsonPath().getString("message"), "usrnm123");
            Assert.assertEquals(response.statusCode(), 200);
        }catch(NullPointerException e){
            logger.error("NullPointerException Exception in deleteUserDeleteRequest ");
            e.printStackTrace();
        }
    }

    @Test
    public void readNotExistingUserGetRequest(){
        Response response = petStoreAPI.readUser();
        try {
            response.then().assertThat().statusCode(404);
            response.then().assertThat().body("message",equalTo("User not found"));
        }catch(NullPointerException e){
            logger.error("NullPointerException Exception in readUserGetRequest ",e);
            e.printStackTrace();
        }
    }
}
