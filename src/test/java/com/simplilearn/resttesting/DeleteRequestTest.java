package com.simplilearn.resttesting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteRequestTest {

	private static final String BASE_URL = "https://reqres.in/api";
	private static final Logger logger = Logger.getLogger(DeleteRequestTest.class);
	
	@DataProvider(name = "deleteUserRestAssured")
	public Iterator<Object[]> deleteUserRestAssured() {
		final List<Object[]> deleteData = new ArrayList<Object[]> ();
		deleteData.add(new Object[] {2});
		deleteData.add(new Object[] {7});
		deleteData.add(new Object[] {11});
		return deleteData.iterator();
	}
	
	
	@Test(description = "Test for executing DELETE request using rest assured",
			dataProvider = "deleteUserRestAssured")
	public void putRequestTest(final int userId) { 
		logger.info("Start :: Delete user by {userId} test.");
		
		logger.info("DELTE : URL "+BASE_URL + "/users/="+userId);
		// create user post data				
		RestAssured.given().baseUri(BASE_URL).when()
		.contentType(ContentType.JSON)
		.delete("/users/"+userId).then()
		.assertThat().statusCode(204);
		
		logger.info("End :: Delete user test.");
	}
}
