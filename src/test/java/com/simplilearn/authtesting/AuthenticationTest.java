package com.simplilearn.authtesting;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class AuthenticationTest {

	private static final String BASE_URL = "https://reqres.in/api";
	private static final Logger logger = Logger.getLogger(AuthenticationTest.class);
	
	@Test(description = "Test Authentication with rest assured")
	public void testAuthenticationToken() { 

		logger.info("Start :: Authentication with rest assured.");
		
		logger.info("POST : URL "+BASE_URL + "/register");
		
		// create user post data
		User user = new User("eve.holt@reqres.in" ,"pistol");
		logger.info("Request Object :: "+user);
		
		RestAssured.given().baseUri(BASE_URL).when()
		.contentType(ContentType.JSON)
		.body(user)
		.log().uri()  // request logs
		.post("/register").then()
		.log().body()  // response logs
		.assertThat().statusCode(200).and()
		.assertThat().body ("id", notNullValue()).and()
		.assertThat().body ("token", notNullValue());
		
		String response = RestAssured.given().baseUri(BASE_URL).when()
		.contentType(ContentType.JSON)
		.body(user)
		.post("/register").getBody().asString();		
		/**  System.out.println(response);  **/
		
		logger.info("Response Object :: "+response);
		logger.info("End :: Authentication with rest assured.");
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

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + "]";
	}
	
}
