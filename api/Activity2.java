package Activities;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
//import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Activity2 {
	final static String ROOT_URI = "https://petstore.swagger.io/v2/user";

	@Test
	public void postPetDetails() throws Exception {
		// Specify the base URL to the RESTful web service
		FileInputStream inputJSON = new FileInputStream("src/test/java/Activities/user.json");
        // Read JSON file as String
        String reqBody = new String(inputJSON.readAllBytes());
        System.out.println(reqBody);

		Response response = given().contentType(ContentType.JSON).body(reqBody).when().post(ROOT_URI);
		inputJSON.close();
		
		String resBody= response.getBody().asPrettyString();
		//System.out.println(reqBody);
		System.out.println(resBody);
		 response.then().body("code", equalTo(200));
	        response.then().body("message", equalTo("1111"));


	}

	@Test
	public void getPetDetails() {
		Response response = 
				given().contentType(ContentType.JSON) // Set headers
				.when().pathParam("username", "Rits") // Set path parameter
				.get(ROOT_URI + "/{username}"); // Send GET request

		// Assertion
		response.then().body("username", equalTo("Rits"));
		

	}

	@Test
	public void deletePet() {
		Response response = 
				given().contentType(ContentType.JSON) // Set headers
				.when().pathParam("username", "Rits") // Set path parameter
				.delete(ROOT_URI + "/{username}"); // Send DELETE request

		// Assertion
		response.then().body("code", equalTo(200));
		//response.then().body("message", equalTo("Rits"));
	}
	}