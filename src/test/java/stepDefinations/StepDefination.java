package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	ResponseSpecification respSpec;
	RequestSpecification req1;
	Response res;
	TestDataBuild data = new TestDataBuild();
	static String placeId;
	JsonPath js;

	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		req1 = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		APIResources resourceAPI = APIResources.valueOf(resource);

		respSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		if(method.equalsIgnoreCase("POST")) {
			res = req1.when().post(resourceAPI.getResource());
		}
		else if(method.equalsIgnoreCase("GET")){
			res = req1.when().get(resourceAPI.getResource());
		}
		else if(method.equalsIgnoreCase("delete")){
			res = req1.when().delete(resourceAPI.getResource());
		}

		
	}

	@Then("the api call got sucess with status code {int}")
	public void the_api_call_got_sucess_with_status_code(int int1) {
		assertEquals(res.getStatusCode(), int1);
	}

	@Then("{string} in responce body is {string}")
	public void in_responce_body_is(String keyValue, String expectedValue) {
		
		assertEquals(getJsonPath(res,keyValue), expectedValue);

	}
	
	@Then("Verify PlaceId created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		placeId=getJsonPath(res,"place_id");
		req1 = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_request(resource,"GET");
		String actualName=getJsonPath(res,"name");
		assertEquals(actualName,expectedName);
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		req1 =given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	}

}
