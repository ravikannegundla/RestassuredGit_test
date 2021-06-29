package Restassured;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;


public class GetandPostexamples 
{
	//@Test
	public void Test_Get()
	{

		baseURI="https://reqres.in/api";
		given().
		get("/users?page=2").
		then().
		statusCode(200).
		body("data[4].first_name",equalTo("George"));
		//body("data.first_name",hasitems("George","Racheal"));


	}

	@Test
	public void Test_POST()
	{

		Map <String,Object> map = new HashMap< String,Object>();
		//    map.put("name","ravi");
		//    map.put("job","teacher");
		//    System.out.println(map);
		JSONObject request=new JSONObject();
		request.put("name","ravi");
		request.put("Job","teacher");

		System.out.println(request.toJSONString());



		baseURI="https://reqres.in/api";
		given().
		header("content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
	    body(request.toJSONString()).
		when().
		post("/users").
		then().
		statusCode(201).
		log().all();


	}





}
