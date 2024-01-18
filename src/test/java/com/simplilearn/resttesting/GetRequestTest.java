package com.simplilearn.resttesting;

import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetRequestTest {

	private static final String BASE_URL = "https://reqres.in/api";
	
	@Test(description = "Test for executing GET request using rest assured")
	public void getRequestTest() {
		// paginated response params
		int page = 2 , total_pages =2, per_page=6,  total=12;
		
		RestAssured.given().baseUri(BASE_URL)
		.when().get("/users?page="+page).then().statusCode(200)
		.and()
		.assertThat()
		.body("page", equalTo(page)).and()
		.body("total", equalTo(total)).and()
		.body("per_page", equalTo(per_page)).and()
		.body("total_pages", equalTo(total_pages)).and()
		.body("data[0].first_name", equalTo("Michael"));
	}
	
	@Test(description = "Test for executing GET request using query parms")
	public void getRequestWithQueryParmsTest() {
		// paginated response params
		int page = 2 , total_pages =2, per_page=6,  total=12;
		
		/** String response = RestAssured.given().baseUri(BASE_URL).queryParam ("page",page)
		.when().get("/users").getBody().asPrettyString();
		System.out.println(response);  **/
		
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
	}
	
	@Test(description = "Test for executing GET /users request with userId")
	public void getRequestWithUserIdTest() {
		// paginated response params
		int userId = 7 ;
		
		/** String response = RestAssured.given().baseUri(BASE_URL)
		.when().get("/users/"+userId).getBody().asPrettyString();
		System.out.println(response);  **/
		
		RestAssured.given().baseUri(BASE_URL)
		.when().get("/users/"+userId).then().statusCode(200)
		.and()
		.assertThat()
		.body("data.first_name", equalTo("Michael")).and()
		.body("data.last_name", equalTo("Lawson")).and()
		.body("data.email", equalTo("michael.lawson@reqres.in"));
	}
	
}
