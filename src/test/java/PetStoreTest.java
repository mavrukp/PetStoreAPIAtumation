import api.PetStoreAPI;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetStoreTest {

    PetStoreAPI petStoreAPI = new PetStoreAPI();

    @Test (priority = 1)
    public void createUserPostRequest(){
        Response response = petStoreAPI.createUser();
        try {
            Assert.assertEquals(response.jsonPath().getInt("code"), 200);
            Assert.assertEquals(response.jsonPath().getString("message"), "68858015");
            Assert.assertEquals(response.statusCode(), 200);
        }catch(NullPointerException e){
            System.out.println("NullPointerException Exception in createUserPostRequest ");
            e.printStackTrace();
        }
    }

    @Test (priority = 2)
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
        }catch(NullPointerException e){
            System.out.println("NullPointerException Exception in readUserGetRequest ");
            e.printStackTrace();
        }
    }

    @Test (priority = 3)
    public void updateUserPutRequest(){
        Response response = petStoreAPI.updateUser();
        try {
            Assert.assertEquals(response.jsonPath().getInt("code"), 200);
            Assert.assertEquals(response.jsonPath().getString("message"), "68858015");
            Assert.assertEquals(response.statusCode(), 200);
        }catch(NullPointerException e){
            System.out.println("NullPointerException Exception in updateUserPutRequest ");
            e.printStackTrace();
        }
    }

    @Test (priority = 4)
    public void deleteUserDeleteRequest(){
        Response response = petStoreAPI.deleteUser();
        try {
            Assert.assertEquals(response.jsonPath().getInt("code"), 200);
            Assert.assertEquals(response.jsonPath().getString("message"), "usrnm123");
            Assert.assertEquals(response.statusCode(), 200);
        }catch(NullPointerException e){
            System.out.println("NullPointerException Exception in deleteUserDeleteRequest ");
            e.printStackTrace();
        }
    }
}
