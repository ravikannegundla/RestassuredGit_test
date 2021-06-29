package Restassured;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DevSite_Hris 

{


	String tokenreceveid;

	@BeforeClass
	public void AccessToken()
	{

		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("username","testclientadmin000101@g.com");
		request.put("password","kantola001");

		System.out.println(request.toJSONString());

		baseURI="https://dev.kantola.com/AdminApi/api/HRIS";
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");

		Response Tokenvalue=request1.body(request.toJSONString()).post("/GetAccessToken");
		Tokenvalue.prettyPrint();
		String jsonstring=Tokenvalue.getBody().asString();
		tokenreceveid =JsonPath.from(jsonstring).get("Data");
		System.out.println(tokenreceveid);
		int statuscode=Tokenvalue.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}



	//@Test
	public void GetCourseStatus()

	{

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.get("/GetCourseStatus/3012Dashboardarchive@ggk.com/");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}




	//@Test
	public void GetAllCourseStatusForTheCompany()

	{
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.get("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();

		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}




	//@Test
	public void GetTimeInSystem()
	{

		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate","03-01-2021");
		request.put("EndDate","05-30-2022");
		request.put("TimeZone","pdt");
		request.put("TimeZoneType","selector");
		request.put("Format","summary");
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetTimeInSystem");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}


	//@Test
	public void PostGetAllCourseStatusForTheCompany()
	{

		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate","03-01-2021");
		request.put("EndDate","05-30-2022");
		request.put("Status","complete");
		request.put("PageSize","100");
		request.put("APIDelivered","true");
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}


	//@Test
	public void UpdateUserGroups()
	{

		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("unassigngroups","test");
		request.put("assigngroups","groupp0101");
		request.put("emailid","3015Dashboardarchive@ggk.com");
		request.put("uid","");
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/UpdateUserGroups");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}




	//@Test
	public void ArchiveUserActivity()
	{

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("uid","");
		request.put("emailid","3015Dashboardarchive@ggk.com");
		request.put("RetainUser","true");
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/ArchiveUserActivity");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}



	
	
	@Test
	public void CreateHRISUser()
	{

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group","test");
		request.put("emailid","4413Dashboardarchive@ggk.com");
		request.put("password","kantola001");
		request.put("firstName","Test");
		request.put("lastName","UserHRIS");
		request.put("phoneNumber","46646464654");
		request.put("uid","");
		
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}


	
	
	

	@Test
	public void CreateHRISUserFromExcelFile()
	{

		
		System.out.println("Case started");
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="adduser001 - Copy";
		
		
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(1,0);
		String Lastname=excel.getCellData(1,1);
		String Group=excel.getCellData(1,2);
		String Email=excel.getCellData(1,3);
		String Password=excel.getCellData(1,4);
		String Phonenumber=excel.getCellData(1,5);
		
		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid","");
		
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	
		
		

	}

	
	



}
