package com.simplilearn.resttesting;

import static org.hamcrest.CoreMatchers.equalTo;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class GetRequestTest {

	private static final String BASE_URL = "https://reqres.in/api";
	private static final Logger logger = Logger.getLogger(GetRequestTest.class);
	
	@Test(description = "Test for executing GET request using rest assured")
	public void getRequestTest() {
		logger.info("Start :: GET All users test.");
		// paginated response params
		int page = 2 , total_pages =2, per_page=6,  total=12;
		
		logger.info("GET : URL "+BASE_URL + "/users?page="+page);
		
		RestAssured.given().baseUri(BASE_URL)
		.when().get("/users?page="+page).then().statusCode(200)
		.and()
		.assertThat()
		.body("page", equalTo(page)).and()
		.body("total", equalTo(total)).and()
		.body("per_page", equalTo(per_page)).and()
		.body("total_pages", equalTo(total_pages)).and()
		.body("data[0].first_name", equalTo("Michael"));
		
		String response = RestAssured.given().baseUri(BASE_URL).queryParam ("page",page)
		.when().get("/users?page="+page).getBody().asString();
		
		logger.info("Reponse :: "+response);
		logger.info("End :: GET All users test.");
	}
	
	@Test(description = "Test for executing GET request using query parms")
	public void getRequestWithQueryParmsTest() {
		logger.info("Start :: GET All users test with query parms.");
		// paginated response params
		int page = 2 , total_pages =2, per_page=6,  total=12;
		
		 String response = RestAssured.given().baseUri(BASE_URL).queryParam ("page",page)
		.when().get("/users").getBody().asString();
		 
		 /** System.out.println(response);  **/
		logger.info("GET : URL "+BASE_URL + "/users?page="+page);
		
		RestAssured.given().baseUri(BASE_URL).queryParam ("page",page)
		.when().get("/users").then().statusCode(200)
		.and()
		.assertThat()
		.body("page", equalTo(page)).and()
		.body("total", equalTo(total)).and()
		.body("per_page", equalTo(per_page)).and()
		.body("total_pages", equalTo(total_pages)).and()
		.body("data[0].first_name", equalTo("Michael"))
		.body("data[0].last_name", equalTo("Lawson"))
		.body("data[0].email", equalTo("michael.lawson@reqres.in"));
		
		logger.info("Reponse :: "+response);
		logger.info("End :: GET All users test with query parms.");
	}
	
	@Test(description = "Test for executing GET /users request with userId")
	public void getRequestWithUserIdTest() {
		logger.info("Start :: GET user by {userId} test.");
		// paginated response params
		int userId = 7 ;
		
		String response = RestAssured.given().baseUri(BASE_URL)
		.when().get("/users/"+userId).getBody().asString();
		/** System.out.println(response);  **/
		
		logger.info("GET : URL "+BASE_URL + "/users/"+userId);
		
		RestAssured.given().baseUri(BASE_URL)
		.when().get("/users/"+userId).then().statusCode(200)
		.and()
		.assertThat()
		.body("data.first_name", equalTo("Michael")).and()
		.body("data.last_name", equalTo("Lawson")).and()
		.body("data.email", equalTo("michael.lawson@reqres.in"));
		
		logger.info("Reponse :: "+response);
		logger.info("End :: GET user by {userId} test.");
	}
	
}
