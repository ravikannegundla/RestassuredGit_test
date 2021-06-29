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



public class DevSite_Datafromexcelsheet 


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


	//@Test
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


	//@Test(description="CreateHRISEmail")
	public void API_TC_201()
	{


		System.out.println("Case started");
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";


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

	


	


	//@Test(description="CreateHRISUseridwithpassword")
	public void API_TC_202()
	{

		System.out.println("Case started");
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";


		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(2,0);
		String Lastname=excel.getCellData(2,1);
		String Group=excel.getCellData(2,2);
		//String Email=excel.getCellData(1,3);
		String Password=excel.getCellData(2,4);
		String Phonenumber=excel.getCellData(2,5);
		String userid=excel.getCellData(2,6);

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		//request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid",userid);

		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}

	
	

	//@Test(description="CreateHRISUseridwithoutpassword")
	public void API_TC_203()
	{

		System.out.println("Case started");
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";


		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(3,0);
		String Lastname=excel.getCellData(3,1);
		String Group=excel.getCellData(3,2);
		//String Email=excel.getCellData(1,3);
		String Password=excel.getCellData(3,4);
		String Phonenumber=excel.getCellData(3,5);
		String userid=excel.getCellData(3,6);

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		//request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid",userid);

		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);

	}



	//@Test(description="CreateHRISEmailWithoutpassword")
	public void API_TC_204()
	{


		System.out.println("Case started");
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";


		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(4,0);
		String Lastname=excel.getCellData(4,1);
		String Group=excel.getCellData(4,2);
		String Email=excel.getCellData(4,3);
		String Password=excel.getCellData(4,4);
		String Phonenumber=excel.getCellData(4,5);

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


	
	//@Test(description="CreateHRISWrongEmailidFormate")
	public void API_TC_205()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(5,0);
		String Lastname=excel.getCellData(5,1);
		String Group=excel.getCellData(5,2);
		String Email=excel.getCellData(5,3);
		String Password=excel.getCellData(5,4);
		String Phonenumber=excel.getCellData(5,5);

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
		Assert.assertEquals(statuscode, 400);

	}


	//@Test(description="CreateHRISInvaildGroup")
	public void API_TC_206()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(6,0);
		String Lastname=excel.getCellData(6,1);
		String Group=excel.getCellData(6,2);
		String Email=excel.getCellData(6,3);
		String Password=excel.getCellData(6,4);
		String Phonenumber=excel.getCellData(6,5);

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
		Assert.assertEquals(statuscode, 400);

	}


	

	//@Test(description="CreateHRISWithoutgroup")
	public void API_TC_207()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(7,0);
		String Lastname=excel.getCellData(7,1);
		String Group=excel.getCellData(7,2);
		String Email=excel.getCellData(7,3);
		String Password=excel.getCellData(7,4);
		String Phonenumber=excel.getCellData(7,5);

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
		Assert.assertEquals(statuscode, 400);

	}



	
	//@Test(description="CreateHRISWithNoFristname")
	public void API_TC_208()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(8,0);
		String Lastname=excel.getCellData(8,1);
		String Group=excel.getCellData(8,2);
		String Email=excel.getCellData(8,3);
		String Password=excel.getCellData(8,4);
		String Phonenumber=excel.getCellData(8,5);

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
		Assert.assertEquals(statuscode, 400);

	}



	
	//@Test(description="CreateHRISWithNoLastName")
	public void API_TC_209()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(9,0);
		String Lastname=excel.getCellData(9,1);
		String Group=excel.getCellData(9,2);
		String Email=excel.getCellData(9,3);
		String Password=excel.getCellData(9,4);
		String Phonenumber=excel.getCellData(9,5);

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
		Assert.assertEquals(statuscode, 400);

	}

	


	//@Test(description="CreateHRISOptinalPhoneNumber")
	public void API_TC_210()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(10,0);
		String Lastname=excel.getCellData(10,1);
		String Group=excel.getCellData(10,2);
		String Email=excel.getCellData(10,3);
		String Password=excel.getCellData(10,4);
		String Phonenumber=excel.getCellData(10,5);

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

	

	//@Test(description="CreateHRISUserWithlocation")
	public void API_TC_211()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(11,0);
		String Lastname=excel.getCellData(11,1);
		String Group=excel.getCellData(11,2);
		String Email=excel.getCellData(11,3);
		String Password=excel.getCellData(11,4);
		String Phonenumber=excel.getCellData(11,5);
		String Location=excel.getCellData(11,7);

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid","");
		request.put("location",Location);

		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}

	
	

	//@Test(description="CreateHRISUserWithInvalidlocation")
	public void API_TC_212()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(12,0);
		String Lastname=excel.getCellData(12,1);
		String Group=excel.getCellData(12,2);
		String Email=excel.getCellData(12,3);
		String Password=excel.getCellData(12,4);
		String Phonenumber=excel.getCellData(12,5);
		String Location=excel.getCellData(12,7);

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid","");
		request.put("location",Location);

		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);

	}
	

	
	//@Test(description="CreateHRISUserWithAlternateID")
	public void API_TC_213()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(13,0);
		String Lastname=excel.getCellData(13,1);
		String Group=excel.getCellData(13,2);
		String Email=excel.getCellData(13,3);
		String Password=excel.getCellData(13,4);
		String Phonenumber=excel.getCellData(13,5);
		String Location=excel.getCellData(13,7);
		String Alternateid=excel.getCellData(13,8);
		

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid","");
		request.put("location",Location);
		request.put("AlternateID",Alternateid);

		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}


	
	//@Test(description="CreateHRISUserWithSamealternateid")
	public void API_TC_214()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(14,0);
		String Lastname=excel.getCellData(14,1);
		String Group=excel.getCellData(14,2);
		String Email=excel.getCellData(14,3);
		String Password=excel.getCellData(14,4);
		String Phonenumber=excel.getCellData(14,5);
		String Location=excel.getCellData(14,7);
		String Alternateid=excel.getCellData(14,8);

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid","");
		request.put("location",Location);
		request.put("AlternateID",Alternateid);
		
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}

	

	//@Test(description="CreateHRISUserWithNotificationEmailIdAsEmailId")
	public void API_TC_215()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(15,0);
		String Lastname=excel.getCellData(15,1);
		String Group=excel.getCellData(15,2);
		String Email=excel.getCellData(15,3);
		String Password=excel.getCellData(15,4);
		String Phonenumber=excel.getCellData(15,5);
		String Location=excel.getCellData(15,7);
		String Alternateid=excel.getCellData(15,8);
		String NotificationEmailId=excel.getCellData(15,9);

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid","");
		request.put("location",Location);
		request.put("AlternateID",Alternateid);
		request.put("NotificationEmailID",NotificationEmailId);
		
		
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}

	
	
	//@Test(description="CreateHRISUserWithNotificationEmailIdAsDiffrentEmailId")
	

	
	//@Test
	public void API_TC_216()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(16,0);
		String Lastname=excel.getCellData(16,1);
		String Group=excel.getCellData(16,2);
		String Email=excel.getCellData(16,3);
		String Password=excel.getCellData(16,4);
		String Phonenumber=excel.getCellData(16,5);
		String Location=excel.getCellData(16,7);
		String Alternateid=excel.getCellData(16,8);
		String NotificationEmailId=excel.getCellData(16,9);

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid","");
		request.put("location",Location);
		request.put("AlternateID",Alternateid);
		request.put("NotificationEmailID",NotificationEmailId);
		
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);

	}
	
	
	
	
	//@Test(description="CreateHRISUserWithNotificationEmailIdandUserId")
	public void API_TC_217()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(17,0);
		String Lastname=excel.getCellData(17,1);
		String Group=excel.getCellData(17,2);
		String Email=excel.getCellData(17,3);
		String Password=excel.getCellData(17,4);
		String Phonenumber=excel.getCellData(17,5);
		String userid=excel.getCellData(17,6);
		String Location=excel.getCellData(17,7);
		String Alternateid=excel.getCellData(17,8);
		String NotificationEmailId=excel.getCellData(17,9);

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid",userid);
		request.put("location",Location);
		request.put("AlternateID",Alternateid);
		request.put("NotificationEmailID",NotificationEmailId);
		
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}
	
	

	//@Test(description="CreateHRISUserWithSameNotificationEmailIdandDiffrentUserId")

	
	
	//@Test
	public void API_TC_218()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(18,0);
		String Lastname=excel.getCellData(18,1);
		String Group=excel.getCellData(18,2);
		String Email=excel.getCellData(18,3);
		String Password=excel.getCellData(18,4);
		String Phonenumber=excel.getCellData(18,5);
		String userid=excel.getCellData(18,6);
		String Location=excel.getCellData(18,7);
		String Alternateid=excel.getCellData(18,8);
		String NotificationEmailId=excel.getCellData(18,9);

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid",userid);
		request.put("location",Location);
		request.put("AlternateID",Alternateid);
		request.put("NotificationEmailID",NotificationEmailId);
		
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}
	
	
	//@Test(description="CreateHRISUserWithGroupRoleandState")

	
	
	//@Test
	public void API_TC_219()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(19,0);
		String Lastname=excel.getCellData(19,1);
		String Group=excel.getCellData(19,2);
		String Email=excel.getCellData(19,3);
		String Password=excel.getCellData(19,4);
		String Phonenumber=excel.getCellData(19,5);
		String userid=excel.getCellData(19,6);
		String Location=excel.getCellData(19,7);
		String Alternateid=excel.getCellData(19,8);
		String NotificationEmailId=excel.getCellData(19,9);

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid",userid);
		request.put("location",Location);
		request.put("AlternateID",Alternateid);
		request.put("NotificationEmailID",NotificationEmailId);
		
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}



		
	//@Test(description="CreateHRISUserWithOnlyRoleandState")
	public void API_TC_220()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(20,0);
		String Lastname=excel.getCellData(20,1);
		String Group=excel.getCellData(20,2);
		String Email=excel.getCellData(20,3);
		String Password=excel.getCellData(20,4);
		String Phonenumber=excel.getCellData(20,5);
		String userid=excel.getCellData(20,6);
		String Location=excel.getCellData(20,7);
		String Alternateid=excel.getCellData(20,8);
		String NotificationEmailId=excel.getCellData(20,9);

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid",userid);
		request.put("location",Location);
		request.put("AlternateID",Alternateid);
		request.put("NotificationEmailID",NotificationEmailId);
		
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

	}

	
	
	//@Test(description="CreateHRISUserWithInvalidRoleandState")
	public void API_TC_221()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(21,0);
		String Lastname=excel.getCellData(21,1);
		String Group=excel.getCellData(21,2);
		String Email=excel.getCellData(21,3);
		String Password=excel.getCellData(21,4);
		String Phonenumber=excel.getCellData(21,5);
		String userid=excel.getCellData(21,6);
		String Location=excel.getCellData(21,7);
		String Alternateid=excel.getCellData(21,8);
		String NotificationEmailId=excel.getCellData(21,9);

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid",userid);
		request.put("location",Location);
		request.put("AlternateID",Alternateid);
		request.put("NotificationEmailID",NotificationEmailId);
		
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);

	}
	

	
	//@Test(description="CreateHRISUserWithOnlyRole")
	public void API_TC_222()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(22,0);
		String Lastname=excel.getCellData(22,1);
		String Group=excel.getCellData(22,2);
		String Email=excel.getCellData(22,3);
		String Password=excel.getCellData(22,4);
		String Phonenumber=excel.getCellData(22,5);
		String userid=excel.getCellData(22,6);
		String Location=excel.getCellData(22,7);
		String Alternateid=excel.getCellData(22,8);
		String NotificationEmailId=excel.getCellData(22,9);

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid",userid);
		request.put("location",Location);
		request.put("AlternateID",Alternateid);
		request.put("NotificationEmailID",NotificationEmailId);
		
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);

	}
	
	
	//@Test(description="CreateHRISUserWithOnlyState")
	public void API_TC_223()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String firstname=excel.getCellData(23,0);
		String Lastname=excel.getCellData(23,1);
		String Group=excel.getCellData(23,2);
		String Email=excel.getCellData(23,3);
		String Password=excel.getCellData(23,4);
		String Phonenumber=excel.getCellData(23,5);
		String userid=excel.getCellData(23,6);
		String Location=excel.getCellData(23,7);
		String Alternateid=excel.getCellData(23,8);
		String NotificationEmailId=excel.getCellData(23,9);

		Map <String,Object> map = new HashMap< String,Object>();
		JSONObject request=new JSONObject();
		request.put("group",Group);
		request.put("emailid",Email);
		request.put("password",Password);
		request.put("firstName",firstname);
		request.put("lastName",Lastname);
		request.put("phoneNumber",Phonenumber);
		request.put("uid",userid);
		request.put("location",Location);
		request.put("AlternateID",Alternateid);
		request.put("NotificationEmailID",NotificationEmailId);
		
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);

	}
	
	

	//@Test(description="CreateHRISInvalidToken")
	public void API_TC_224()
	{


		System.out.println("Case started");
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="AddUserCases";

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
		request1.header("Authorization","");

		Response response=request1.body(request.toJSONString()).post("/CreateHRISUser");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 406);

	}

	
	

	//@Test(description="GetCourseStatusEmails")
	public void API_TC_301()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String email=excel.getCellData(1,0);
		
		String URL="/GetCourseStatus/"+email+ '/';  
		System.out.println(URL);
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.get(URL);
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

		
	}

	

	//@Test(description="GetCourseStatuswithUserid")
	public void API_TC_302()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String email=excel.getCellData(1,0);
		String userid=excel.getCellData(2,1);
		
		String URL="/GetCourseStatus/"+userid+ '/';  
		System.out.println(URL);
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.get(URL);
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);

		
	}
	
	
	
	//@Test(description="GetCourseStatuswithInvaildToken")
	public void API_TC_303()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String email=excel.getCellData(3,0);
		
		
		String URL="/GetCourseStatus/"+email+ '/';  
		System.out.println(URL);
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization","");

		Response response=request1.get(URL);
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode,406);

		
	}

	
	
	
	//@Test
	public void GetAllCourseStatusForTheCompanyWithInvaildToken()

	{
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization","");

		Response response=request1.get("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();

		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 406);

	}
	
	



	//@Test(description="GetTimeInSystemFromExcelData")
	public void API_TC_401()
	{
		
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(6,2);
		String enddate=excel.getCellData(6,3);
		String timezone=excel.getCellData(6,4);
		String timezonetype=excel.getCellData(6,5);
		String format=excel.getCellData(6,6);

	
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("TimeZone",timezone);
		request.put("TimeZoneType",timezonetype);
		request.put("Format",format);
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetTimeInSystem");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

	
	
	//@Test(description="GetTimeInSystemWithoutStartDate")
	public void API_TC_402()
	{
		
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(7,2);
		String enddate=excel.getCellData(7,3);
		String timezone=excel.getCellData(7,4);
		String timezonetype=excel.getCellData(7,5);
		String format=excel.getCellData(7,6);

	
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("TimeZone",timezone);
		request.put("TimeZoneType",timezonetype);
		request.put("Format",format);
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetTimeInSystem");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);
	}

	
	//@Test(description="GetTimeInSystemWithoutEndDate")
	public void API_TC_403()
	{
		
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(8,2);
		String enddate=excel.getCellData(8,3);
		String timezone=excel.getCellData(8,4);
		String timezonetype=excel.getCellData(8,5);
		String format=excel.getCellData(8,6);

	
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("TimeZone",timezone);
		request.put("TimeZoneType",timezonetype);
		request.put("Format",format);
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetTimeInSystem");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);
	}

	
	//@Test(description="GetTimeInSystemWithinvaildDate")
	public void API_TC_404()
	{
		
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(9,2);
		String enddate=excel.getCellData(9,3);
		String timezone=excel.getCellData(9,4);
		String timezonetype=excel.getCellData(9,5);
		String format=excel.getCellData(9,6);

	
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("TimeZone",timezone);
		request.put("TimeZoneType",timezonetype);
		request.put("Format",format);
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetTimeInSystem");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);
	}

	
	//@Test(description="GetTimeInSystemWithOtherTimeZone")
	public void API_TC_405()
	{
		
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(10,2);
		String enddate=excel.getCellData(10,3);
		String timezone=excel.getCellData(10,4);
		String timezonetype=excel.getCellData(10,5);
		String format=excel.getCellData(10,6);

	
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("TimeZone",timezone);
		request.put("TimeZoneType",timezonetype);
		request.put("Format",format);
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetTimeInSystem");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

	
	
	//@Test(description="GetTimeInSystemWithInvaildTimeZone")
	public void API_TC_406()
	{
		
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(11,2);
		String enddate=excel.getCellData(11,3);
		String timezone=excel.getCellData(11,4);
		String timezonetype=excel.getCellData(11,5);
		String format=excel.getCellData(11,6);

	
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("TimeZone",timezone);
		request.put("TimeZoneType",timezonetype);
		request.put("Format",format);
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetTimeInSystem");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);
	}

	
	

	

	//@Test(description="GetTimeInSystemWithTimeZoneTypetimecard")
	public void API_TC_407()
	{
		
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(12,2);
		String enddate=excel.getCellData(12,3);
		String timezone=excel.getCellData(12,4);
		String timezonetype=excel.getCellData(12,5);
		String format=excel.getCellData(12,6);

	
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("TimeZone",timezone);
		request.put("TimeZoneType",timezonetype);
		request.put("Format",format);
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetTimeInSystem");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

	
	
	//@Test(description="GetTimeInSystemWithinvaildTypetimecard")
	public void API_TC_408()
	{
		
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(13,2);
		String enddate=excel.getCellData(13,3);
		String timezone=excel.getCellData(13,4);
		String timezonetype=excel.getCellData(13,5);
		String format=excel.getCellData(13,6);

	
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("TimeZone",timezone);
		request.put("TimeZoneType",timezonetype);
		request.put("Format",format);
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetTimeInSystem");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);
	}

	

	
	//@Test(description="GetTimeInSystemWithFormatconverter")
	public void API_TC_409()
	{
		
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(14,2);
		String enddate=excel.getCellData(14,3);
		String timezone=excel.getCellData(14,4);
		String timezonetype=excel.getCellData(14,5);
		String format=excel.getCellData(14,6);

	
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("TimeZone",timezone);
		request.put("TimeZoneType",timezonetype);
		request.put("Format",format);
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetTimeInSystem");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}


	
	//@Test(description="GetTimeInSystemWithInvaildFormat")
	public void API_TC_410()
	{
		
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(15,2);
		String enddate=excel.getCellData(15,3);
		String timezone=excel.getCellData(15,4);
		String timezonetype=excel.getCellData(15,5);
		String format=excel.getCellData(15,6);

	
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("TimeZone",timezone);
		request.put("TimeZoneType",timezonetype);
		request.put("Format",format);
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetTimeInSystem");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);
	}


	
	//@Test(description="GetTimeInSystemWithInvaildGroup")
	public void API_TC_411()
	{
		
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(16,2);
		String enddate=excel.getCellData(16,3);
		String timezone=excel.getCellData(16,4);
		String timezonetype=excel.getCellData(16,5);
		String format=excel.getCellData(16,6);
		String Groupname=excel.getCellData(16,7);
		String location=excel.getCellData(16,8);


	
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("TimeZone",timezone);
		request.put("TimeZoneType",timezonetype);
		request.put("Format",format);
		request.put("Format",Groupname);
		request.put("Format",location);
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetTimeInSystem");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);
	}

	
	
	//@Test(description="GetTimeInSystemWithInvaildLoaction")
	public void API_TC_412()
	{
		
		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(17,2);
		String enddate=excel.getCellData(17,3);
		String timezone=excel.getCellData(17,4);
		String timezonetype=excel.getCellData(17,5);
		String format=excel.getCellData(17,6);
		String Groupname=excel.getCellData(17,7);
		String location=excel.getCellData(17,8);

	
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("TimeZone",timezone);
		request.put("TimeZoneType",timezonetype);
		request.put("Format",format);
		request.put("Format",Groupname);
		request.put("Format",location);
		
		System.out.println(request.toJSONString());

		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetTimeInSystem");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);
	}

	
	
	//@Test(description="GetTimeInSystemWithInvaildtoken")
	public void API_TC_413()
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
		request1.header("Authorization","");

		Response response=request1.body(request.toJSONString()).post("/GetTimeInSystem");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 406);
	}

	
	
	//@Test(description="Dashbaord")
	public void API_TC_501()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(27,0);
		String enddate=excel.getCellData(27,1);
		String status=excel.getCellData(27,2);
		String pagesize=excel.getCellData(27,3);
		String apidelivered=excel.getCellData(27,4);
		String coursename=excel.getCellData(27,5);
		String groupname=excel.getCellData(27,6);
		String location=excel.getCellData(27,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

	
	//@Test(description="GetTimeInSystemWithInvaildLoaction")
			//public void API_TC_412()
	
	
	//@Test(description="DashboardwithNoStartDate")
	public void API_TC_502()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(28,0);
		String enddate=excel.getCellData(28,1);
		String status=excel.getCellData(28,2);
		String pagesize=excel.getCellData(28,3);
		String apidelivered=excel.getCellData(28,4);
		String coursename=excel.getCellData(28,5);
		String groupname=excel.getCellData(28,6);
		String location=excel.getCellData(28,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}
	
	

	
	
	//@Test(description="GetTimeInSystemWithInvaildLoaction")
	public void API_TC_503()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(29,0);
		String enddate=excel.getCellData(29,1);
		String status=excel.getCellData(29,2);
		String pagesize=excel.getCellData(29,3);
		String apidelivered=excel.getCellData(29,4);
		String coursename=excel.getCellData(29,5);
		String groupname=excel.getCellData(29,6);
		String location=excel.getCellData(29,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);
	}

	
	
	
	//@Test(description="DashboardwithNoEndDate")
	public void API_TC_504()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(30,0);
		String enddate=excel.getCellData(30,1);
		String status=excel.getCellData(30,2);
		String pagesize=excel.getCellData(30,3);
		String apidelivered=excel.getCellData(30,4);
		String coursename=excel.getCellData(30,5);
		String groupname=excel.getCellData(30,6);
		String location=excel.getCellData(30,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}
	
	
	
	//@Test(description="DashboardwithInvaildEndDate")
	public void API_TC_505()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(31,0);
		String enddate=excel.getCellData(31,1);
		String status=excel.getCellData(31,2);
		String pagesize=excel.getCellData(31,3);
		String apidelivered=excel.getCellData(31,4);
		String coursename=excel.getCellData(31,5);
		String groupname=excel.getCellData(31,6);
		String location=excel.getCellData(31,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 400);
	}

	
	
	
	//@Test(description="DashboardwithAssignedDate")
	public void API_TC_506()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(32,0);
		String enddate=excel.getCellData(32,1);
		String status=excel.getCellData(32,2);
		String pagesize=excel.getCellData(32,3);
		String apidelivered=excel.getCellData(32,4);
		String coursename=excel.getCellData(32,5);
		String groupname=excel.getCellData(32,6);
		String location=excel.getCellData(32,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}
	
	
	
	
	//@Test(description="DashboardwithCurrentActivity")
	public void API_TC_507()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(33,0);
		String enddate=excel.getCellData(33,1);
		String status=excel.getCellData(33,2);
		String pagesize=excel.getCellData(33,3);
		String apidelivered=excel.getCellData(33,4);
		String coursename=excel.getCellData(33,5);
		String groupname=excel.getCellData(33,6);
		String location=excel.getCellData(33,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

	
	//@Test(description="DashboardwithPastDue")
	public void API_TC_508()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(34,0);
		String enddate=excel.getCellData(34,1);
		String status=excel.getCellData(34,2);
		String pagesize=excel.getCellData(34,3);
		String apidelivered=excel.getCellData(34,4);
		String coursename=excel.getCellData(34,5);
		String groupname=excel.getCellData(34,6);
		String location=excel.getCellData(34,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}
	
	
	//@Test(description="DashboardwithNotComplete")
	public void API_TC_509()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(35,0);
		String enddate=excel.getCellData(35,1);
		String status=excel.getCellData(35,2);
		String pagesize=excel.getCellData(35,3);
		String apidelivered=excel.getCellData(35,4);
		String coursename=excel.getCellData(35,5);
		String groupname=excel.getCellData(35,6);
		String location=excel.getCellData(35,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}
	
	
	
	@Test(description="Dashboardwithoutpagesize")
		public void API_TC_510()
		{

			String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
			String sheetName="Hrisothercases";
			ExcelData excel= new ExcelData(excelpath,sheetName);
			excel.getRowCount();
			String startdate=excel.getCellData(35,0);
			String enddate=excel.getCellData(35,1);
			String status=excel.getCellData(35,2);
			String pagesize=excel.getCellData(35,3);
			String apidelivered=excel.getCellData(35,4);
			String coursename=excel.getCellData(35,5);
			String groupname=excel.getCellData(35,6);
			String location=excel.getCellData(35,7);

			
			Map <String,Object> map = new HashMap< String,Object>();

			JSONObject request=new JSONObject();
			request.put("StartDate",startdate);
			request.put("EndDate",enddate);
			request.put("Status",status);
			request.put("PageSize",pagesize);
			request.put("APIDelivered",apidelivered);
			request.put("CourseName",coursename);
			request.put("GroupName",groupname);
			request.put("Location",location);
			System.out.println(request.toJSONString());
			
			RequestSpecification request1=given();
			request1.header("content-Type","application/json");
			request1.header("Authorization",tokenreceveid);

			Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
			response.prettyPrint();
			int statuscode=response.getStatusCode();
			Assert.assertEquals(statuscode, 200);
		}


	

	@Test(description="DashboardwithDifferentPagesize")
		public void API_TC_511()
		{

			String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
			String sheetName="Hrisothercases";
			ExcelData excel= new ExcelData(excelpath,sheetName);
			excel.getRowCount();
			String startdate=excel.getCellData(35,0);
			String enddate=excel.getCellData(35,1);
			String status=excel.getCellData(35,2);
			String pagesize=excel.getCellData(35,3);
			String apidelivered=excel.getCellData(35,4);
			String coursename=excel.getCellData(35,5);
			String groupname=excel.getCellData(35,6);
			String location=excel.getCellData(35,7);

			
			Map <String,Object> map = new HashMap< String,Object>();

			JSONObject request=new JSONObject();
			request.put("StartDate",startdate);
			request.put("EndDate",enddate);
			request.put("Status",status);
			request.put("PageSize",pagesize);
			request.put("APIDelivered",apidelivered);
			request.put("CourseName",coursename);
			request.put("GroupName",groupname);
			request.put("Location",location);
			System.out.println(request.toJSONString());
			
			RequestSpecification request1=given();
			request1.header("content-Type","application/json");
			request1.header("Authorization",tokenreceveid);

			Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
			response.prettyPrint();
			int statuscode=response.getStatusCode();
			Assert.assertEquals(statuscode, 200);
		}

	
	

	@Test(description="Dashboardwithstring")
		public void API_TC_512()
		{
		

			String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
			String sheetName="Hrisothercases";
			ExcelData excel= new ExcelData(excelpath,sheetName);
			excel.getRowCount();
			String startdate=excel.getCellData(35,0);
			String enddate=excel.getCellData(35,1);
			String status=excel.getCellData(35,2);
			String pagesize=excel.getCellData(35,3);
			String apidelivered=excel.getCellData(35,4);
			String coursename=excel.getCellData(35,5);
			String groupname=excel.getCellData(35,6);
			String location=excel.getCellData(35,7);

			
			Map <String,Object> map = new HashMap< String,Object>();

			JSONObject request=new JSONObject();
			request.put("StartDate",startdate);
			request.put("EndDate",enddate);
			request.put("Status",status);
			request.put("PageSize",pagesize);
			request.put("APIDelivered",apidelivered);
			request.put("CourseName",coursename);
			request.put("GroupName",groupname);
			request.put("Location",location);
			System.out.println(request.toJSONString());
			
			RequestSpecification request1=given();
			request1.header("content-Type","application/json");
			request1.header("Authorization",tokenreceveid);

			Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
			response.prettyPrint();
			int statuscode=response.getStatusCode();
			Assert.assertEquals(statuscode, 200);
		}

	


	@Test(description="DashboardwithAPIdelviredAsfalse")
		public void API_TC_513()
		{

			String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
			String sheetName="Hrisothercases";
			ExcelData excel= new ExcelData(excelpath,sheetName);
			excel.getRowCount();
			String startdate=excel.getCellData(35,0);
			String enddate=excel.getCellData(35,1);
			String status=excel.getCellData(35,2);
			String pagesize=excel.getCellData(35,3);
			String apidelivered=excel.getCellData(35,4);
			String coursename=excel.getCellData(35,5);
			String groupname=excel.getCellData(35,6);
			String location=excel.getCellData(35,7);

			
			Map <String,Object> map = new HashMap< String,Object>();

			JSONObject request=new JSONObject();
			request.put("StartDate",startdate);
			request.put("EndDate",enddate);
			request.put("Status",status);
			request.put("PageSize",pagesize);
			request.put("APIDelivered",apidelivered);
			request.put("CourseName",coursename);
			request.put("GroupName",groupname);
			request.put("Location",location);
			System.out.println(request.toJSONString());
			
			RequestSpecification request1=given();
			request1.header("content-Type","application/json");
			request1.header("Authorization",tokenreceveid);

			Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
			response.prettyPrint();
			int statuscode=response.getStatusCode();
			Assert.assertEquals(statuscode, 200);
		}


	
	@Test(description="DashboardwithoutAPIdelvired")
	public void API_TC_514()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(35,0);
		String enddate=excel.getCellData(35,1);
		String status=excel.getCellData(35,2);
		String pagesize=excel.getCellData(35,3);
		String apidelivered=excel.getCellData(35,4);
		String coursename=excel.getCellData(35,5);
		String groupname=excel.getCellData(35,6);
		String location=excel.getCellData(35,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}
	
	
	

	
	@Test(description="DashboardwithinvalidAPIdelivred")
	public void API_TC_515()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(35,0);
		String enddate=excel.getCellData(35,1);
		String status=excel.getCellData(35,2);
		String pagesize=excel.getCellData(35,3);
		String apidelivered=excel.getCellData(35,4);
		String coursename=excel.getCellData(35,5);
		String groupname=excel.getCellData(35,6);
		String location=excel.getCellData(35,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

	

	
	@Test(description="DashboardwithinputasNumber")
	public void API_TC_516()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(35,0);
		String enddate=excel.getCellData(35,1);
		String status=excel.getCellData(35,2);
		String pagesize=excel.getCellData(35,3);
		String apidelivered=excel.getCellData(35,4);
		String coursename=excel.getCellData(35,5);
		String groupname=excel.getCellData(35,6);
		String location=excel.getCellData(35,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

	
	

	
	@Test(description="DashboardwithCoursename")
	public void API_TC_517()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(35,0);
		String enddate=excel.getCellData(35,1);
		String status=excel.getCellData(35,2);
		String pagesize=excel.getCellData(35,3);
		String apidelivered=excel.getCellData(35,4);
		String coursename=excel.getCellData(35,5);
		String groupname=excel.getCellData(35,6);
		String location=excel.getCellData(35,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

	
	

	
	@Test(description="DashboardwithInvalidCoursename")
	public void API_TC_518()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(35,0);
		String enddate=excel.getCellData(35,1);
		String status=excel.getCellData(35,2);
		String pagesize=excel.getCellData(35,3);
		String apidelivered=excel.getCellData(35,4);
		String coursename=excel.getCellData(35,5);
		String groupname=excel.getCellData(35,6);
		String location=excel.getCellData(35,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

	
	

	
	@Test(description="DashboardwithGroupName")
	public void API_TC_519()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(35,0);
		String enddate=excel.getCellData(35,1);
		String status=excel.getCellData(35,2);
		String pagesize=excel.getCellData(35,3);
		String apidelivered=excel.getCellData(35,4);
		String coursename=excel.getCellData(35,5);
		String groupname=excel.getCellData(35,6);
		String location=excel.getCellData(35,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

	

	
	@Test(description="DashboardwithinvalidGroupName")
	public void API_TC_520()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(35,0);
		String enddate=excel.getCellData(35,1);
		String status=excel.getCellData(35,2);
		String pagesize=excel.getCellData(35,3);
		String apidelivered=excel.getCellData(35,4);
		String coursename=excel.getCellData(35,5);
		String groupname=excel.getCellData(35,6);
		String location=excel.getCellData(35,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

	
	
	

	
	@Test(description="Dashboardwithlocation")
	public void API_TC_521()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(35,0);
		String enddate=excel.getCellData(35,1);
		String status=excel.getCellData(35,2);
		String pagesize=excel.getCellData(35,3);
		String apidelivered=excel.getCellData(35,4);
		String coursename=excel.getCellData(35,5);
		String groupname=excel.getCellData(35,6);
		String location=excel.getCellData(35,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

	
	
	

	
	@Test(description="Dashboardwithinvalidlocation")
	public void API_TC_522()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(35,0);
		String enddate=excel.getCellData(35,1);
		String status=excel.getCellData(35,2);
		String pagesize=excel.getCellData(35,3);
		String apidelivered=excel.getCellData(35,4);
		String coursename=excel.getCellData(35,5);
		String groupname=excel.getCellData(35,6);
		String location=excel.getCellData(35,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}

	
	
	
	

	@Test(description="Dashboardwithemptylocation")
	public void API_TC_523()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(35,0);
		String enddate=excel.getCellData(35,1);
		String status=excel.getCellData(35,2);
		String pagesize=excel.getCellData(35,3);
		String apidelivered=excel.getCellData(35,4);
		String coursename=excel.getCellData(35,5);
		String groupname=excel.getCellData(35,6);
		String location=excel.getCellData(35,7);

		
		Map <String,Object> map = new HashMap< String,Object>();

		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
		request.put("PageSize",pagesize);
		request.put("APIDelivered",apidelivered);
		request.put("CourseName",coursename);
		request.put("GroupName",groupname);
		request.put("Location",location);
		System.out.println(request.toJSONString());
		
		RequestSpecification request1=given();
		request1.header("content-Type","application/json");
		request1.header("Authorization",tokenreceveid);

		Response response=request1.body(request.toJSONString()).post("/GetAllCourseStatusForTheCompany");
		response.prettyPrint();
		int statuscode=response.getStatusCode();
		Assert.assertEquals(statuscode, 200);
	}


	
	
	
	@Test(description="Newly added case for git purpose")
	public void API_TC_524()
	{

		String excelpath="C:\\Users\\ravi.kannegundla\\Desktop\\adduser001 - Copy.xlsx";
		String sheetName="Hrisothercases";
		ExcelData excel= new ExcelData(excelpath,sheetName);
		excel.getRowCount();
		String startdate=excel.getCellData(35,0);
		String enddate=excel.getCellData(35,1);
		String status=excel.getCellData(35,2);
		String pagesize=excel.getCellData(35,3);
		
		Map <String,Object> map = new HashMap< String,Object>();
// New line is added and few lines are updated
		JSONObject request=new JSONObject();
		request.put("StartDate",startdate);
		request.put("EndDate",enddate);
		request.put("Status",status);
	
		
	}

	
	
	
	
}


