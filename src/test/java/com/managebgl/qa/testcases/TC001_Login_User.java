package com.managebgl.qa.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.managebgl.qa.utilities.RestUtils;
import com.managebgl.qa.utilities.XLUtils;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.IOException;

public class TC001_Login_User {

	//Base path for Login
	String basePath = "/login.json?";
	
	
	// *****************************************************************************************************************
	//
	// * Project : ManageBGL API Testing(Lendico Automation Challenge)
	// * TestCase1 : Verify success response on valid predictbgl user login
	// * Author : Sriram Gatiganti
	//
	// *****************************************************************************************************************
	/*
	 * Test Steps Step 
	 * 1.Define content type
	 * 2.Define request url with baseURI+basePath+Path Parameters
	 * 3.Validate status code and response body
	 */

	@Test(priority = 0, dataProvider = "Data", enabled = true)
	public void VerifySucessResponseOnValidLogin(String baseURI, String Email, String Password, String token_id,
			String log_id, String Value, String Success_message, String Failure_message, String Failure_message1,
			String Failure_message2, String Failure_message3, String Failure_message4, String Content_Type,
			String Status_Line,String Logout_Message) {
				
			//1.Define content type
			given()			 
				.contentType(Content_Type)
			
			//2.Define request url with baseURI+basePath+Path Parameters
			.when()
				.get(baseURI + basePath + "email=" + Email + "&password=" + 12345678)
			
			//3.Validate status code and response body
			.then()				
				.statusCode(200)
				.statusLine(Status_Line)
				.assertThat()
				.body("result", equalTo(true))
				.assertThat().body("token", equalTo(token_id))
				.assertThat().body("user_id", equalTo(30978))
				.assertThat().body("message", equalTo(Success_message))
				.log().body()
				.header("Content-Type", Content_Type);

	}
	
	
	// *****************************************************************************************************************
	//
	// * Project : ManageBGL API Testing(Lendico Automation Challenge)
	// * TestCase2 : Verify failure response when predictbgl user try login with invalid Email
	// * Author : Sriram Gatiganti
	//
	// *****************************************************************************************************************
	/*
	 * Test Steps Step 
	 * 1.Define content type
	 * 2.Define request url with baseURI+basePath+Path Parameters with invalid Email
	 * 3.Validate status code and response body
	 */
	

	@Test(priority = 1, dataProvider = "Data", enabled = true)
	public void VerifyFailureResponseOnInvalidEmail(String baseURI, String Email, String Password, String token_id,
			String log_id, String Value, String Success_message, String Failure_message, String Failure_message1,
			String Failure_message2, String Failure_message3, String Failure_message4, String Content_Type,
			String Status_Line,String Logout_Message) {
			
			//1.Define content type
			given()
				.contentType(Content_Type)
				
			//2.Define request url with baseURI+basePath+Path Parameters with invalid Email
			.when()
				.get(baseURI + basePath + "email="+RestUtils.getEmail()+"&password=" + Password)
				
			//3.Validate status code and response body
			.then()
				.statusCode(200)
				.statusLine(Status_Line)
				.assertThat().body("result", equalTo(false))
				.assertThat()
				.body("message", equalTo(Failure_message))
				.log().body()
				.header("Content-Type", Content_Type);

	}
	
	// *****************************************************************************************************************
	//
	// * Project : ManageBGL API Testing(Lendico Automation Challenge)
	// * TestCase3 : Verify failure response when predictbgl user try login with invalid password
	// * Author : Sriram Gatiganti
	//
	// *****************************************************************************************************************
	/*
	 * Test Steps Step 
	 * 1.Define content type
	 * 2.Define request url with baseURI+basePath+Path Parameters with invalid Email
	 * 3.Validate status code and response body
	 */

	@Test(priority = 1, dataProvider = "Data", enabled = false)
	public void VerifyFailureResponseOnInvalidPassoword(String Email, String Password, String token_id, String log_id,
			String Value, String Success_message, String Failure_message, String Failure_message1,
			String Failure_message2, String Failure_message3, String Failure_message4, String Content_Type,
			String Status_Line,String Logout_Message) {

		//1.Define content type
		given()
			.contentType(Content_Type)
			
		// 2.Define request url with baseURI+basePath+Path Parameters with invalid Email
		.when()
			.get(baseURI + basePath + Email + "&password="+RestUtils.getPassword())
			
		//3.Validate status code and response body
		.then()
			.statusCode(200)
			.statusLine(Status_Line)
			.assertThat().body("result", equalTo(false))
			.assertThat()
			.body("message", equalTo(Failure_message1))
			.header("Content-Type", Content_Type);
	}
	
	
	//Data fetching from excel sheet
	@DataProvider(name = "Data")
	public String[][] getData() throws IOException {
			
			String path = System.getProperty("user.dir") + "/ManagebglUserAPIData.xlsx";
	
			int rownum = XLUtils.getRowCount(path, "Sheet1");
			int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
	
			String apidata[][] = new String[rownum][colcount];
	
			for (int i = 1; i <= rownum; i++) {
				for (int j = 0; j < colcount; j++) {
					apidata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
				}
			}
			return apidata;
		}

}
