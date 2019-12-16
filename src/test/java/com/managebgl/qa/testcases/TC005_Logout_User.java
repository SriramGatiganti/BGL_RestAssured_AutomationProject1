package com.managebgl.qa.testcases;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.managebgl.qa.utilities.XLUtils;

public class TC005_Logout_User {

	String basePath = "/logout.json?";
	
	// *****************************************************************************************************************
	//
	// * Project : Manage BGLAPI Testing(Lendico Automation Challenge)
	// * TestCase1 : Verify success response on valid predictbgl user logout
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
	public void VerifySucessResponseOnValidLogout(String baseURI, String Email, String Password, String token_id,
			String log_id, String Value, String Success_message, String Failure_message, String Failure_message1,
			String Failure_message2, String Failure_message3, String Failure_message4, String Content_Type,
			String Status_Line,String Logout_Message) {
			
			//1.Define content type
			given()
				.contentType(Content_Type)
				
			//2.Define request url with baseURI+basePath+Path Parameters
			.when()
				.get(baseURI + basePath + "token=" + token_id)
				
			//3.Validate status code and response body
			.then()
				.statusCode(200)
				.statusLine(Status_Line)
				.assertThat().body("result", equalTo(true))
				.assertThat().body("message", equalTo(Logout_Message))
				.log().body()
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
