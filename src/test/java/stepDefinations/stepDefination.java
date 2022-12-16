package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo.LocationForSerialisePojo;
import pojo.serializeGoogleMapsAddPlacePojoClass;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class stepDefination extends Utils {
	RequestSpecification resspec;
	ResponseSpecification responseobj;
	Response response;
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place Payload with {string} {string}")
	public void add_place_payload_with(String name, String adress) throws IOException {

		resspec = given().spec(RequestSpecification()).log().all().body(data.addPlacePayLoad(name, adress));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		// APIResources enum class constructor will be called with value of resource
		// which you pass
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		// Commenting because retriving status code states Hamcrast eroor
		// ResponseSpecification responseobj= new
		// ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		responseobj = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (method.equalsIgnoreCase("POST"))
			response = resspec.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = resspec.get(resourceAPI.getResource());

	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		assertEquals(response.getStatusCode(), 200);

	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		assertEquals(getJsonPath(response, keyValue), Expectedvalue);
	}
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		String place_id=getJsonPath(response, "place_id");
		resspec = given().spec(RequestSpecification()).queryParam("place_id", place_id);
		
		user_calls_with_http_request(resource, "GET");
		String actualName=getJsonPath(response,"name");
		assertEquals(expectedName, actualName);
		


	}

}
