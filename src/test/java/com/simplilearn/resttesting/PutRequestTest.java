package com.simplilearn.resttesting;

import static org.hamcrest.CoreMatchers.equalTo;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PutRequestTest {

	private static final String BASE_URL = "https://reqres.in/api";
	private static final Logger logger = Logger.getLogger(PutRequestTest.class);

	@Test(description = "Test for executing PUT request using rest assured")
	public void putRequestTest() {
		logger.info("Start :: PUT request test using rest assured.");

		String response = null;
		try {
			// create user post data
			int userId = 7;
			
			PostData postData = new PostData("Mike SMith", "QA/Tester");
			logger.info("Request Object:: "+postData);
			
			RestAssured.given().baseUri(BASE_URL).when().contentType(ContentType.JSON).body(postData)
					.put("/users/" + userId).then().assertThat().statusCode(200).and().assertThat()
					.body("name", equalTo(postData.name)).and().assertThat().body("job", equalTo(postData.job));

			response = RestAssured.given().baseUri(BASE_URL).when().contentType(ContentType.JSON).body(postData)
					.post("/users").getBody().asString();
		} catch (Exception e) {
			logger.error("Exception Object :: " + e.toString());
			logger.error("End Exception :: " + e.getLocalizedMessage());
		}

		logger.info("Response :: " + response);
		logger.info("End :: PUT request test using rest assured.");
	}
}
