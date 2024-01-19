package com.simplilearn.authtesting;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class SSLAuthenticationTest {

	private static final String BASE_URL = "https://dummyjson.com/auth";

	private static final String BASE_URL2 = "http://www.bupa.com.au";
	private static final Logger logger = Logger.getLogger(SSLAuthenticationTest.class);

	@Test(description = "Test SSL Certificate for HTTPs")
	public void testSSLCertificate1() {
		logger.info("Start :: Test SSL Certificate for HTTPs");
		logger.info("POST : URL " + BASE_URL + "/login");
		try {
			RestAssured.given().baseUri(BASE_URL).relaxedHTTPSValidation() // HTTPs validation check or error :
																			// SSLPeerUnverifiedException
					.when().get("/login").then().statusCode(403);

		} catch (Exception e) {
			logger.error("Exception Object :: " + e.toString());
			logger.error("End Exception :: " + e.getLocalizedMessage());
		}
		logger.info("End :: Test SSL Certificate for HTTPs");
	}

	@Test(description = "Test SSL Certificate ignore for HTTP")
	public void testSSLCertificate2() {
		logger.info("Start :: Test2 SSL Certificate ignore for HTTP");
		logger.info("POST : URL " + BASE_URL2 + "/");
		try {
			RestAssured.given().baseUri(BASE_URL2).relaxedHTTPSValidation() // HTTPs validation check or error :
																			// SSLPeerUnverifiedException
					.when().get("/").then().statusCode(200);
		} catch (Exception e) {
			logger.error("Exception Object :: " + e.toString());
			logger.error("End Exception :: " + e.getLocalizedMessage());
		}
		logger.info("End :: Test2 SSL Certificate ignore for HTTP");
	}
}
