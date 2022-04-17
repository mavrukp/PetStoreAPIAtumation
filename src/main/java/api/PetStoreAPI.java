package api;

import common.JSONFile;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.testng.log4testng.Logger;

public class PetStoreAPI {

    private static final String BASEPATH = "https://petstore.swagger.io";
    private static final String CONTENTTYPE = "application/json";
    private static Logger logger = Logger.getLogger(PetStoreAPI.class);
    private String username;

    public PetStoreAPI(){
        RestAssured.config= RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.socket.timeout",10000)
                        .setParam("http.connection.timeout", 10000));
        RestAssured.useRelaxedHTTPSValidation();
        username = new JSONFile("postRequestBody").getUserNameFromBodyFile();
    }

    public Response createUser() {

        JSONFile jsonFile = new JSONFile("postRequestBody");

        String body = jsonFile.readJsonBody();

        Response response = null;
        try {
            response = RestAssured.given()
                    .header("Content-Type",CONTENTTYPE)
                    .header("Apikey","Apikeyvalue")
                    .baseUri(BASEPATH)
                    .basePath("v2/user")
                    .body(body)
                    .post();
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException Exception in post request ");
            e.printStackTrace();

        }
        return response;
    }

    public Response readUser() {

        Response response = null;
        try {
            response = RestAssured.given()
                    .header("Accept",CONTENTTYPE)
                    .header("Apikey","Apikeyvalue")
                    .baseUri(BASEPATH)
                    .basePath("v2/user")
                    .pathParam("username",username)
                    .get(BASEPATH+"/v2/user/{username}");
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException Exception in getRequest ");
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
                    .header("Content-Type",CONTENTTYPE)
                    .header("Apikey","Apikeyvalue")
                    .basePath("v2/user/{username}")
                    .baseUri(BASEPATH)
                    .pathParam("username",username)
                    .body(body)
                    .put();
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException Exception in put request ");
            e.printStackTrace();
        }

        return response;
    }

    public Response deleteUser() {

        Response response = null;
        try {
            response = RestAssured.given()
                    .header("Apikey","Apikeyvalue")
                    .pathParam("username",username)
                    .baseUri(BASEPATH)
                    .basePath("v2/user/{username}")
                    .delete();
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgumentException Exception in delete request ");
            e.printStackTrace();
        }

        return response;
    }
}
