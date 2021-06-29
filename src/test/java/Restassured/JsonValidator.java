package Restassured;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;



import org.testng.annotations.Test;

public class JsonValidator 


{


	@Test
	public void test_two()
	{
	baseURI="https://reqres.in/api";
		given().
		get("/users?page=2").
		then().
		assertThat().
		body(matchesJsonSchemaInClasspath("sample.json")).
		statusCode(200);
		
		
	}
	


}
