package com.simplilearn.soaptesting;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;

public class NumberToWordConversion {

	private static final String BASE_URL = "https://www.dataaccess.com";
	
	@Test(description = "Test POST for Number conversion using XML")
	public void postXMLRequestTest() { 
		// xml request body
		String soapRequest  = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToDollars xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <dNum>5078.099</dNum>\r\n"
				+ "    </NumberToDollars>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>";
		
		// create post call				
		RestAssured.given().baseUri(BASE_URL).when()
		.contentType("text/xml; charset=utf-8")
		.body(soapRequest )
		.post("/webservicesserver/NumberConversion.wso").then()
		.assertThat().statusCode(200);
		
		String response = RestAssured.given().baseUri(BASE_URL).when()
				.contentType("text/xml; charset=utf-8")
				.body(soapRequest)
				.post("/webservicesserver/NumberConversion.wso")
				.getBody().asString();
		XmlPath path = new XmlPath(response);
		
		assertEquals(path.getString("NumberToDollarsResult"), "five thousand seventy eight dollars and nine cents");
		System.out.println(path.getString("NumberToDollarsResult"));
	}
}
