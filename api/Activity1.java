package Activities;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
//import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Activity1 {
	final static String ROOT_URI = "https://petstore.swagger.io/v2/pet";

	@Test
	public void postPetDetails() {
		// Specify the base URL to the RESTful web service
		String reqBody="{ \"id\": 77232,"
				+ "  \"name\": \"Riley\","
				+ "  \"status\": \"alive\""
				+ "}";

		Response response = given().contentType(ContentType.JSON).body(reqBody).when().post(ROOT_URI);
		String resBody= response.getBody().asPrettyString();

		//System.out.println(reqBody);
		System.out.println(resBody);
		// response.then().statusCode(201);
		response.then().body("name", containsStringIgnoringCase("Riley"));


	}

	@Test
	public void getPetDetails() {
		Response response = 
				given().contentType(ContentType.JSON) // Set headers
				.when().pathParam("petId", "77232") // Set path parameter
				.get(ROOT_URI + "/{petId}"); // Send GET request

		// Assertion
		response.then().body("id", equalTo(77232));
		response.then().body("name", equalTo("Riley"));
		response.then().body("status", equalTo("alive"));

	}

	@Test
	public void deletePet() {
		Response response = 
				given().contentType(ContentType.JSON) // Set headers
				.when().pathParam("petId", "77232") // Set path parameter
				.delete(ROOT_URI + "/{petId}"); // Send DELETE request

		// Assertion
		response.then().body("code", equalTo(200));
		
	}}