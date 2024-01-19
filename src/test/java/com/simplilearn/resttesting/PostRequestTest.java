package com.simplilearn.resttesting;

import static org.hamcrest.CoreMatchers.equalTo;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostRequestTest {

	private static final String BASE_URL = "https://reqres.in/api";
	private static final Logger logger = Logger.getLogger(PostRequestTest.class);

	@Test(description = "Test for executing POST request using rest assured")
	public void postRequestTest() {
		logger.info("Start :: POST request test using rest assured.");

		String response = null;
		try {
			// create user post data
			PostData postData = new PostData("Mike SMith", "Dermotologist");
			logger.info("Request Object:: "+postData);
			
			RestAssured.given().baseUri(BASE_URL).when().contentType(ContentType.JSON).body(postData).post("/users")
					.then().assertThat().statusCode(201).and().assertThat().body("name", equalTo(postData.name)).and()
					.assertThat().body("job", equalTo(postData.job));

			response = RestAssured.given().baseUri(BASE_URL).when().contentType(ContentType.JSON).body(postData)
					.post("/users").getBody().asString();
		} catch (Exception e) {
			logger.error("Exception Object :: " + e.toString());
			logger.error("End Exception :: " + e.getLocalizedMessage());
		}
		
		logger.info("Response Object:: "+response);
		logger.info("End :: POST request test using rest assured.");
	}
}

class PostData {
	
	public final String name;
	public final String job;

	public PostData(String name, String job) {
		super();
		this.name = name;
		this.job = job;
	}

	@Override
	public String toString() {
		return "PostData [name=" + name + ", job=" + job + "]";
	}
}