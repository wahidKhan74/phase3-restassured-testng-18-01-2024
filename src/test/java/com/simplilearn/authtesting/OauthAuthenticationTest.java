package com.simplilearn.authtesting;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.simplilearn.data.model.UserInfo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class OauthAuthenticationTest {

	private static final String BASE_URL = "https://dummyjson.com/auth";
	private static final Logger logger = Logger.getLogger(OauthAuthenticationTest.class);

	@Test(description = "Test Login user and get token")
	public void testAuthenticationBasedOnCredsToken() {

		logger.info("Start :: Authentication Test Login user and get token.");

		logger.info("POST : URL " + BASE_URL + "/login");

		// create user post data
		UserInfo user = new UserInfo("kminchelle", "0lelplR");
		logger.info("Request Object :: " + user);

		RestAssured.given().baseUri(BASE_URL).when().contentType(ContentType.JSON).body(user).log().uri() // request
																											// logs
				.post("/login").then().log().body() // response logs
				.assertThat().statusCode(200).and().assertThat().body("id", notNullValue()).and().assertThat()
				.body("token", notNullValue());

		String response = RestAssured.given().baseUri(BASE_URL).when().contentType(ContentType.JSON).body(user)
				.post("/login").getBody().asString();
		logger.info("Response Object :: " + response);
		logger.info("End :: Authentication Test Login user and get token.");
	}

	@Test(description = "Get current auth user from token")
	public void testAuthenticationToken() {
		
		logger.info("Start :: Get current auth user from token.");
		logger.info("POST : URL " + BASE_URL + "/me");
		String response = null;
		try {

			// create user post data
			UserInfo user = new UserInfo("kminchelle", "0lelplR");

			response = RestAssured.given().baseUri(BASE_URL).when().contentType(ContentType.JSON).body(user)
					.post("/login").getBody().asPrettyString();

			JsonPath jsonObj = new JsonPath(response);
			String token = jsonObj.getString("token");

			logger.info("Request Token :: " + token);

			RestAssured.given().baseUri(BASE_URL).when().contentType(ContentType.JSON)
					.header("Authorization", "Bearer " + token).log().uri() // request logs
					.get("/me").then().log().body() // response logs
					.assertThat().statusCode(200).and().assertThat().body("id", notNullValue()).and().assertThat()
					.body("username", notNullValue()).and().assertThat().body("username", equalTo("kminchelle")).and()
					.assertThat().body("email", equalTo("kminchelle@qq.com"));
		} catch (Exception e) {
			logger.error("Exception Object :: " + e.toString());
			logger.error("End Exception :: "+e.getLocalizedMessage());
		}

		logger.info("Response Object :: " + response);
		logger.info("End :: Get current auth user from token.");

	}
}
