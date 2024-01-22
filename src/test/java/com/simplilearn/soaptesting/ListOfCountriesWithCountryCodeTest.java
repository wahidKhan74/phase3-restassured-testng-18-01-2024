package com.simplilearn.soaptesting;

import static org.testng.Assert.assertFalse;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;

public class ListOfCountriesWithCountryCodeTest {

	private static final String BASE_URL = "http://webservices.oorsprong.org";
	private static final Logger logger = Logger.getLogger(ListOfCountriesWithCountryCodeTest.class);

	@Test(description = "Test List of Countries with country code")
	public void testListOfCountriesWithCountryCode() {
		logger.info("Start :: POST for Number conversion using XML");
		logger.info("POST : URL " + BASE_URL + "/websamples.countryinfo/CountryInfoService.wso");
		String response = null;

		try {
			// xml request body
			String soapRequest = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
					+ "<soap12:Envelope xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\r\n"
					+ "  <soap12:Body>\r\n"
					+ "    <ListOfCountryNamesByName xmlns=\"http://www.oorsprong.org/websamples.countryinfo\">\r\n"
					+ "    </ListOfCountryNamesByName>\r\n"
					+ "  </soap12:Body>\r\n"
					+ "</soap12:Envelope>";

			// create post call
			RestAssured.given().baseUri(BASE_URL).when().contentType("text/xml; charset=utf-8").body(soapRequest)
					.post("/websamples.countryinfo/CountryInfoService.wso").then().assertThat().statusCode(200);

			response = RestAssured.given().baseUri(BASE_URL).when().contentType("text/xml; charset=utf-8")
					.body(soapRequest).post("/websamples.countryinfo/CountryInfoService.wso").getBody().asString();
			XmlPath path = new XmlPath(response);

			assertFalse(path.getString("ListOfCountryNamesByNameResponse").isEmpty());
			
			// System.out.println(path.getString("ListOfCountryNamesByNameResponse"));
			logger.info("Amount in words :: " + path.getString("ListOfCountryNamesByNameResponse"));

		} catch (Exception e) {
			logger.error("Exception Object :: " + e.toString());
			logger.error("End Exception :: " + e.getLocalizedMessage());
		}

		logger.info("Response Object:: " + response);
		logger.info("End :: POST for Number conversion using XML");
	}

}
