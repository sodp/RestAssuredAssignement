package Training.JiraAssignment;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.utility;


public class getcommentTest extends utility{
	
	// CREATING SESSION AND ISSUE USING HELPER CLASS AFTER THAT CREATING COMMENT AND GET THAT COMMENT AS RESPONSE
	
	String issueId = utility.createIssue();
	static String sessionId = utility.getsession();
	
	@Test
	public void getComments() {
		
		extentTest = extent.startTest("Get Comment Test");
		RestAssured.baseURI = prop.getProperty("url");
		File comm = new File(prop.getProperty("comment_body"));
		
        // CREATING COMMENT
		RequestSpecification httpRequest = RestAssured.given().auth().preemptive().basic(prop.getProperty("username"),
				prop.getProperty("password"));
		Response res = httpRequest.body(comm).header("Content-Type", "application/json").when()
				.post("/api/2/issue/" + issueId + "/comment").then().log().all().extract().response();
		
	   // GETTING COMMENT
       httpRequest = RestAssured.given().header("Cookie", "JSESSIONID=" + sessionId);
       res = httpRequest.body(comm).header("Content-Type", "application/json").when()
				.get("/api/2/issue/" + issueId + "/comment").then().log().all().extract().response();

		JsonPath resp = res.jsonPath();
		Assert.assertEquals(resp.get("comments.body[0]"), "This is a comment by siddhant panda.");
	}


}
