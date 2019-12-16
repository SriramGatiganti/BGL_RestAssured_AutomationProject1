package com.managebgl.qa.testcases;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.managebgl.qa.utilities.RestUtils;
import com.managebgl.qa.utilities.XLUtils;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TC004_DeleteLog {

	String basePath = "/delete.json?";
	
	

	// *****************************************************************************************************************
	//
	// * Project : ManageBGL API Testing(Lendico Automation Challenge)
	// * TestCase1 : Verify success  response when predictbgl user Delete Log Entry
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
	public void VerifySucessResponseOnDeleteLog(String baseURI, String Email, String Password, String token_id,
			String log_id, String Value, String Success_message, String Failure_message, String Failure_message1,
			String Failure_message2, String Failure_message3, String Failure_message4, String Content_Type,
			String Status_Line,String Logout_Message) {

		//1.Define content type	
		given()
			.contentType(Content_Type)
			
		//2.Define request url with baseURI+basePath+Path Parameters
		.when()
			.get(baseURI + basePath + "token=" + token_id + "&log_id=" + log_id)
			
		//3.Validate status code and response body
		.then()
			.statusCode(200)
			.statusLine(Status_Line)
			.assertThat().body("result", equalTo(1))
			.assertThat().body("message", equalTo(Success_message))
			.log().body()
			.header("Content-Type", Content_Type);
	}
	
	// *****************************************************************************************************************
	//
	// * Project : ManageBGL API Testing(Lendico Automation Challenge)
	// * TestCase2 : Verify failure  response when predictbgl user Update-an entry with wrong Log id
	// * Author : Sriram Gatiganti
	//
	// *****************************************************************************************************************
	/*
	 * Test Steps Step 
	 * 1.Define content type
	 * 2.Define request url with baseURI+basePath+Path Parameters with wrong logid
	 * 3.Validate status code and response body
	 */ 

	@Test(priority = 1, dataProvider = "Data", enabled = true)
	public void VerifyFailureResponseOnDeleteLog(String baseURI, String Email, String Password, String token_id,
			String log_id, String Value, String Success_message, String Failure_message, String Failure_message1,
			String Failure_message2, String Failure_message3, String Failure_message4, String Content_Type,
			String Status_Line,String Logout_Message) {

		// 1.Define content type	
		given()
			.contentType(Content_Type)
			
		//2.Define request url with baseURI+basePath+Path Parameters with wrong logid
		.when()
			.get(baseURI + basePath + "token=" + token_id + "&value="+RestUtils.getLogId())
		
		// 3.Validate status code and response body
		.then()
			.statusCode(200)
			.statusLine(Status_Line)
			.assertThat().body("result", equalTo(3))
			.assertThat().body("message", equalTo(Failure_message4))
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
