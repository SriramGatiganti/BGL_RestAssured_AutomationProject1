package com.managebgl.qa.utilities;

import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String getCurrentDateTime() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.toString().substring(0, 19);

	}

	public static String getFirstName() {
		String generatedString = RandomStringUtils.randomAlphabetic(2);
		return ("John" + generatedString);
	}

	public static String getLastName() {
		String generatedString = RandomStringUtils.randomAlphabetic(2);
		return ("Kendey" + generatedString);
	}

	public static String getUserName() {
		String generatedString = RandomStringUtils.randomAlphabetic(2);
		return ("John" + generatedString);
	}

	public static String getPassword() {
		String generatedString = RandomStringUtils.randomAlphabetic(3);
		return ("John" + generatedString);
	}

	public static String getEmail() {
		String generatedString = RandomStringUtils.randomAlphabetic(2);
		return (generatedString + "test.com");
	}

	public static String getName() {
		String generatedString = RandomStringUtils.randomAlphabetic(2);
		return (generatedString);
	}

	public static String getValue() {
		//String generatedString = RandomStringUtils.randomNumeric(3);
		return ("200");
	}
	
	public static String getLogId() {
		//String generatedString = RandomStringUtils.randomNumeric(3);
		return ("4450768890353445");
	}

}
