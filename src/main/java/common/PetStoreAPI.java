package common;

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

    public int createUser() {

        JSONFile jsonFile = new JSONFile("postRequestBody");

        String body = jsonFile.readJsonBody();

        Response response = null;
        try {
            response = RestAssured.given()
                    .header("Content-Type","application/json")
                    .baseUri(BASE_PATH)
                    .basePath("v2/user")
                    .body(body)
                    .post();
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException Exception in getRequestStatusCode ");
            e.printStackTrace();

        }
        return response.statusCode();
    }

    public int readUser() {

        Response response = null;
        try {
            response = RestAssured.given()
                    .header("Accept","application/json")
                    .baseUri(BASE_PATH)
                    .basePath("v2/user")
                    .get(BASE_PATH+"/v2/user/test");
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException Exception in getRequestStatusCode ");
            e.printStackTrace();
        }

        return response.statusCode();
    }

    public int updateUser() {
        JSONFile jsonFile = new JSONFile("putRequestBody");

        String body = jsonFile.readJsonBody();

        Response response = null;
        try {
            response = RestAssured.given()
                    .header("Content-Type","application/json")
                    .baseUri(BASE_PATH)
                    .basePath("v2/user")
                    .body(body)
                    .put("test");
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException Exception in getRequestStatusCode ");
            e.printStackTrace();
        }

        return response.statusCode();
    }

    public int deleteUser() {

        Response response = null;
        try {
            response = RestAssured.given()
                    .baseUri(BASE_PATH)
                    .basePath("v2/user")
                    .delete("test");
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException Exception in getRequestStatusCode ");
            e.printStackTrace();
        }

        return response.statusCode();
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
