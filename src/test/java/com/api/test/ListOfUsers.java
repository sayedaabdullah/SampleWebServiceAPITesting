/**
 * 
 */
package com.api.test;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * @author reaz
 *
 */
public class ListOfUsers {
	
	String baseUrl = "https://infixtech.herokuapp.com";
	Response response;
	JsonPath jp;
	@Test
	public void testListOfUsers() {
		response = RestAssured.get(baseUrl+"/users");
		System.out.println(response.body().asString());
		assertEquals(200, response.getStatusCode());
		
		String json = response.asString();
		jp = new JsonPath(json);

		assertEquals("SUCCESS", jp.get("status"));
		assertEquals("List of Users", jp.get("message"));
		

	}
	@Test
	public void testSingleUser() {
		response = RestAssured.get(baseUrl + "/users/32");
		System.out.println(response.body().asString());
		assertEquals(200, response.getStatusCode());
		
		String json = response.asString();
		jp = new JsonPath(json);

		assertEquals("SUCCESS", jp.get("status"));
		assertEquals("Single User By ID", jp.get("message"));
		
	}
	@Test
	public void testSingleUserNegative() {
		response = RestAssured.get(baseUrl + "/users/30");
		System.out.println(response.body().asString());
		assertEquals(response.getStatusCode(), 404);
	}


}
