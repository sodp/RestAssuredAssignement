package Training.JiraAssignment;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.utility;

public class createuserTest extends utility {

	
	/// TEST TO CREATE NEW USER
			@Test
			public void newUser() {
			extentTest = extent.startTest("Create User Test");
			RestAssured.baseURI = prop.getProperty("url");
			File body = new File(prop.getProperty("newuser_body"));

			RequestSpecification httpRequest = RestAssured.given().auth().preemptive().basic(prop.getProperty("username"),
					prop.getProperty("password"));
			Response res = httpRequest.body(body).header("Content-Type", "application/json").when().post("/api/2/user/")
					.then().log().all().extract().response();
		
		
			JsonPath resp = res.jsonPath();
			String name = resp.get("name");
			key = resp.get("key");
			Assert.assertEquals(name, "sodp1223");
			
//			httpRequest = RestAssured.given().queryParam("key", key).auth().preemptive()
//					.basic(prop.getProperty("username"), prop.getProperty("password"));
//			res = httpRequest.body(body).header("Content-Type", "application/json").when().delete("/api/2/user/")
//					.then().log().all().extract().response();
//			Assert.assertEquals(res.getStatusCode(), 204);
			
		}
		

	

}
