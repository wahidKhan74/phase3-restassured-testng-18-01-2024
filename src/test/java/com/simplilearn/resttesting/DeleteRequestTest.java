package com.simplilearn.resttesting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteRequestTest {

	private static final String BASE_URL = "https://reqres.in/api";
	
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
		// create user post data				
		RestAssured.given().baseUri(BASE_URL).when()
		.contentType(ContentType.JSON)
		.delete("/users/"+userId).then()
		.assertThat().statusCode(204);
	}
}
