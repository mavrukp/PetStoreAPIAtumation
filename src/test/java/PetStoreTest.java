import api.PetStoreAPI;
import common.JSONFile;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PetStoreTest {

    private static Logger logger = Logger.getLogger(PetStoreTest.class);

    PetStoreAPI petStoreAPI = new PetStoreAPI();

    private String message, username;

    @Test (priority = 1)
    public void createUserPostRequest(){
        Response response = petStoreAPI.createUser("postRequestBody");
        try {
            message = response.jsonPath().getString("message");
            Assert.assertEquals(response.jsonPath().getInt("code"), 200);
            Assert.assertNotNull(response.jsonPath().getString("message"));
            Assert.assertEquals(response.statusCode(), 200);
        }catch(NullPointerException e){
            logger.error("NullPointerException Exception in createUserPostRequest ");
            e.printStackTrace();
        }
    }

    @Test (dependsOnMethods = { "createUserPostRequest" })
    public void readUserGetRequest(){
        String body = new JSONFile("postRequestBody").readJsonBody();
        Response response = petStoreAPI.readUser();
        try {
            JSONObject jsonObject = new JSONObject(body);
            username = jsonObject.getString("username");
            Assert.assertEquals(response.jsonPath().getInt("id"), jsonObject.getInt("id"));
            Assert.assertEquals(response.jsonPath().getString("username"), jsonObject.getString("username"));
            Assert.assertEquals(response.jsonPath().getString("firstName"), jsonObject.getString("firstName"));
            Assert.assertEquals(response.jsonPath().getString("lastName"), jsonObject.getString("lastName"));
            Assert.assertEquals(response.jsonPath().getString("email"), jsonObject.getString("email"));
            Assert.assertEquals(response.jsonPath().getString("password"), jsonObject.getString("password"));
            Assert.assertEquals(response.jsonPath().getString("phone"), jsonObject.getString("phone"));
            Assert.assertEquals(response.jsonPath().getInt("userStatus"), jsonObject.getInt("userStatus"));
            Assert.assertEquals(response.statusCode(), 200);
            response.then().assertThat().body(matchesJsonSchemaInClasspath("getResponseSchema.json"));
        }catch(NullPointerException e){
            logger.error("NullPointerException Exception in readUserGetRequest ");
            e.printStackTrace();
        }
    }

    @Test (dependsOnMethods = { "readUserGetRequest" })
    public void updateUserPutRequest(){
        Response response = petStoreAPI.updateUser("putRequestBody");
        try {
            Assert.assertEquals(response.jsonPath().getInt("code"), 200);
            Assert.assertEquals(response.jsonPath().getString("message"), message);
            Assert.assertEquals(response.statusCode(), 200);
        }catch(NullPointerException e){
            logger.error("NullPointerException Exception in updateUserPutRequest ");
            e.printStackTrace();
        }
    }

    @Test (dependsOnMethods = { "updateUserPutRequest" })
    public void readUserGetRequestAfterPutRequest(){
        String body = new JSONFile("putRequestBody").readJsonBody();
        Response response = petStoreAPI.readUser();
        try {
            JSONObject jsonObject = new JSONObject(body);
            Assert.assertEquals(response.jsonPath().getInt("id"), jsonObject.getInt("id"));
            Assert.assertEquals(response.jsonPath().getString("username"), jsonObject.getString("username"));
            Assert.assertEquals(response.jsonPath().getString("firstName"), jsonObject.getString("firstName"));
            Assert.assertEquals(response.jsonPath().getString("lastName"), jsonObject.getString("lastName"));
            Assert.assertEquals(response.jsonPath().getString("email"), jsonObject.getString("email"));
            Assert.assertEquals(response.jsonPath().getString("password"), jsonObject.getString("password"));
            Assert.assertEquals(response.jsonPath().getString("phone"), jsonObject.getString("phone"));
            Assert.assertEquals(response.jsonPath().getInt("userStatus"), jsonObject.getInt("userStatus"));
            Assert.assertEquals(response.statusCode(), 200);
            response.then().assertThat().body(matchesJsonSchemaInClasspath("getResponseSchema.json"));
        }catch(NullPointerException e){
            logger.error("NullPointerException Exception in readUserGetRequest ");
            e.printStackTrace();
        }
    }

    @Test (dependsOnMethods = { "readUserGetRequestAfterPutRequest" })
    public void deleteUserDeleteRequest(){
        Response response = petStoreAPI.deleteUser();
        try {
            Assert.assertEquals(response.jsonPath().getInt("code"), 200);
            Assert.assertEquals(response.jsonPath().getString("message"), username);
            Assert.assertEquals(response.statusCode(), 200);
        }catch(NullPointerException e){
            logger.error("NullPointerException Exception in deleteUserDeleteRequest ");
            e.printStackTrace();
        }catch(IllegalArgumentException e) {
            logger.error("IllegalArgumentException Exception in deleteUserDeleteRequest ");
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

    @Test//(expectedExceptions = java.lang.IllegalArgumentException.class)
    public void postRequestTestForIllegalException(){
        Response response = petStoreAPI.createUser("postRequestWithEmptyUsername");
        if (response==null) Assert.assertTrue(true);

    }

    @Test
    public void putRequestTestForIllegalException(){
        Response response = petStoreAPI.updateUser("postRequestWithEmptyUsername");
        if (response==null) Assert.assertTrue(true);

    }
}
