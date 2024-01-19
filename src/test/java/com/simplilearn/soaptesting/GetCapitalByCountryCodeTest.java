package com.simplilearn.soaptesting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.simplilearn.resttesting.PutRequestTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetCapitalByCountryCodeTest {

	private static final String BASE_URL = "http://webservices.oorsprong.org";
	private static final Logger logger = Logger.getLogger(GetCapitalByCountryCodeTest.class);

	@DataProvider(name = "countryCodes")
	public Iterator<Object[]> countryCodes() {
		final List<Object[]> countryCodes = new ArrayList<Object[]>();
		countryCodes.add(new Object[] { "IN" });
		countryCodes.add(new Object[] { "US" });
		countryCodes.add(new Object[] { "CA" });
		countryCodes.add(new Object[] { "ML" });
		return countryCodes.iterator();
	}

	@Test(description = "Test POST for country capital using XML", dataProvider = "countryCodes")
	public void postXMLRequestTest(final String ctrCode) {

		logger.info("Start :: POST for country capital using XML using rest assured.");
		logger.info("GET : URL "+BASE_URL + "/websamples.countryinfo/CountryInfoService.wso" +  " for " + ctrCode +" country code.");
		
		String response = null;
		try {
			// xml request body
			String soapRequest = "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + "    <Body>\r\n"
					+ "        <CapitalCity xmlns=\"http://www.oorsprong.org/websamples.countryinfo\">\r\n"
					+ "            <sCountryISOCode>" + ctrCode + "</sCountryISOCode>\r\n"
					+ "        </CapitalCity>\r\n" + "    </Body>\r\n" + "</Envelope>";

			// create post call
			RestAssured.given().baseUri(BASE_URL).when().contentType("text/xml; charset=utf-8").body(soapRequest)
					.post("/websamples.countryinfo/CountryInfoService.wso").then().assertThat().statusCode(200);

			response = RestAssured.given().baseUri(BASE_URL).when().contentType("text/xml; charset=utf-8")
					.body(soapRequest).post("/websamples.countryinfo/CountryInfoService.wso").getBody()
					.asPrettyString();

		} catch (Exception e) {
			logger.error("Exception Object :: " + e.toString());
			logger.error("End Exception :: " + e.getLocalizedMessage());
		}

		logger.info("Response Object:: " + response);
		logger.info("End :: POST for country capital using XML using rest assured.");
	}
}
