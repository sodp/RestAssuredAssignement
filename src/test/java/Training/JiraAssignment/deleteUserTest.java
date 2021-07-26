package Training.JiraAssignment;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.utility;

public class deleteUserTest extends utility {
	
	
    //TEST TO DELETE USER
	//CREATE USER IS CALLED FROM UTILITY.JAVA FILE WHICH CREATES NEW USER
	static String key = utility.createnewuser();
	
	@Test
	public void deleteuser() {
		extentTest = extent.startTest("Delete User Test");
		RestAssured.baseURI = prop.getProperty("url");
		File body = new File(prop.getProperty("newuser_body"));

		RequestSpecification httpRequest = RestAssured.given().queryParam("key", key).auth().preemptive()
				.basic(prop.getProperty("username"), prop.getProperty("password"));
		Response res = httpRequest.body(body).header("Content-Type", "application/json").when().delete("/api/2/user/")
				.then().log().all().extract().response();

		Assert.assertEquals(res.getStatusCode(), 204);
	}
	
}
