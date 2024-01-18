package com.simplilearn.resttesting;

import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PutRequestTest {

	private static final String BASE_URL = "https://reqres.in/api";

	@Test(description = "Test for executing PUT request using rest assured")
	public void putRequestTest() {
		// create user post data
		int userId = 7;
		PostData postData = new PostData("Mike SMith", "QA/Tester");

		RestAssured.given().baseUri(BASE_URL).when().contentType(ContentType.JSON).body(postData)
				.put("/users/" + userId).then().assertThat().statusCode(200).and().assertThat()
				.body("name", equalTo(postData.name)).and().assertThat().body("job", equalTo(postData.job));

		String response = RestAssured.given().baseUri(BASE_URL).when().contentType(ContentType.JSON).body(postData)
				.post("/users").getBody().asPrettyString();

		System.out.println(response);
	}
}
