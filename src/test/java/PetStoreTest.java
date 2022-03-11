import common.PetStoreAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetStoreTest {

    PetStoreAPI petStoreAPI = new PetStoreAPI();

    @Test (priority = 1)
    public void createUserPostRequest(){
        int statusCode = 0;
        statusCode = petStoreAPI.createUser();
        Assert.assertEquals(statusCode,200);
    }

    @Test (priority = 2)
    public void readUserGetRequest(){
        int statusCode = 0;
        statusCode = petStoreAPI.readUser();
        Assert.assertEquals(statusCode,200);
    }

    @Test (priority = 3)
    public void updateUserPutRequest(){
        int statusCode = 0;
        statusCode = petStoreAPI.updateUser();
        Assert.assertEquals(statusCode,200);
    }

    @Test (priority = 4)
    public void deleteUserDeleteRequest(){
        int statusCode = 0;
        statusCode = petStoreAPI.deleteUser();
        Assert.assertEquals(statusCode,200);
    }
}
