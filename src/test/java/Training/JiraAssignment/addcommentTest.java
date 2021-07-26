package Training.JiraAssignment;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.utility;

public class addcommentTest extends utility {
	
//// GETTING SESSION ID FROM THE UTILS PACKAGE
//// CREATING ISSUE & FETECHING ISSUE ID FROM UTILS PACKAGE
	
	static String sessionId = utility.getsession();
	static String issueId = utility.createIssue();
	@Test
	public void addComment() {
		extentTest = extent.startTest("Add Comment Test");

		RestAssured.baseURI = prop.getProperty("url");
		File comm = new File(prop.getProperty("comment_body"));

		RequestSpecification httpRequest = RestAssured.given().auth().preemptive().basic(prop.getProperty("username"),
				prop.getProperty("password"));
		Response res = httpRequest.body(comm).header("Content-Type", "application/json").when()
				.post("/api/2/issue/" + issueId + "/comment").then().log().all().extract().response();

		JsonPath resp = res.jsonPath();
		commentId = resp.get("id");
		Assert.assertEquals(res.getStatusCode(), 201);
	}
}

