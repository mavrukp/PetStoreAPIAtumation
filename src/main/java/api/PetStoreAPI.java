package api;

import common.JSONFile;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.json.JSONObject;

public class PetStoreAPI {

    String BASE_PATH = "https://petstore.swagger.io";

    public PetStoreAPI(){
        RestAssured.config= RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.socket.timeout",10000)
                        .setParam("http.connection.timeout", 10000));
        RestAssured.useRelaxedHTTPSValidation();
    }

    public Response createUser() {

        JSONFile jsonFile = new JSONFile("postRequestBody");

        String body = jsonFile.readJsonBody();

        Response response = null;
        try {
            response = RestAssured.given()
                    .header("Content-Type","application/json")
                    .header("Apikey","Apikeyvalue")
                    .baseUri(BASE_PATH)
                    .basePath("v2/user")
                    .body(body)
                    .post();
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException Exception in post request ");
            e.printStackTrace();

        }
        return response;
    }

    public Response readUser() {

        Response response = null;
        try {
            response = RestAssured.given()
                    .header("Accept","application/json")
                    .header("Apikey","Apikeyvalue")
                    .baseUri(BASE_PATH)
                    .basePath("v2/user")
                    .pathParam("username","usrnm123")
                    .get(BASE_PATH+"/v2/user/{username}");
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException Exception in getRequest ");
            e.printStackTrace();
        }

        return response;
    }

    public Response updateUser() {
        JSONFile jsonFile = new JSONFile("putRequestBody");

        String body = jsonFile.readJsonBody();

        Response response = null;
        try {
            response = RestAssured.given()
                    .header("Content-Type","application/json")
                    .header("Apikey","Apikeyvalue")
                    .basePath("v2/user/{username}")
                    .baseUri(BASE_PATH)
                    .pathParam("username","usrnm123")
                    .body(body)
                    .put();
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException Exception in put request ");
            e.printStackTrace();
        }

        return response;
    }

    public Response deleteUser() {

        Response response = null;
        try {
            response = RestAssured.given()
                    .header("Apikey","Apikeyvalue")
                    .pathParam("username","usrnm123")
                    .baseUri(BASE_PATH)
                    .basePath("v2/user/{username}")
                    .delete();
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException Exception in delete request ");
            e.printStackTrace();
        }

        return response;
    }

/*    String createJsonBody(){
        JSONObject jo = new JSONObject();
        jo.put( "id", 68858015);
        jo.put( "username", "test");
        jo.put( "firstName", "testfirstname");
        jo.put( "lastName", "testlastname");
        jo.put(  "email", "testemail");
        jo.put( "password", "testpassword");
        jo.put( "phone", "2221115566");
        jo.put( "userStatus", 68858015);
        return jo.toString();
    }*/
}
