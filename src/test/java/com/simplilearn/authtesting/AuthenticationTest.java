package com.simplilearn.authtesting;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AuthenticationTest {

	private static final String BASE_URL = "https://reqres.in/api";
	
	@Test(description = "Test Authentication with rest assured")
	public void testAuthenticationToken() { 
		// create user post data
		User user = new User("eve.holt@reqres.in" ,"pistol");
		
		RestAssured.given().baseUri(BASE_URL).when()
		.contentType(ContentType.JSON)
		.body(user)
		.log().uri()  // request logs
		.post("/register").then()
		.log().body()  // response logs
		.assertThat().statusCode(200).and()
		.assertThat().body ("id", notNullValue()).and()
		.assertThat().body ("token", notNullValue());
		
		/** String response = RestAssured.given().baseUri(BASE_URL).when()
		.contentType(ContentType.JSON)
		.body(user)
		.post("/register").getBody().asPrettyString();		
		System.out.println(response);  **/
	}
}

class User {
	
	public String email;
	public String password;
	
	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
}
