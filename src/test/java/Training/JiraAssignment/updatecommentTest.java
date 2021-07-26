package Training.JiraAssignment;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.utility;

public class updatecommentTest extends utility {

	// CREATING SESSION,ISSUE & COMMENT USING HELPER CLASS 
	// UPDATING COMMENT TEST
	String sessionId = utility.getsession();
	String issueId = utility.createIssue();
	String commentId = utility.AddComment();
	
	@Test
	public void updateComment() {
		extentTest = extent.startTest("Update Comment Test");
		RestAssured.baseURI = prop.getProperty("url");
		File comment = new File(prop.getProperty("update_comment_body"));

		RequestSpecification httpRequest = RestAssured.given().body(comment).
				header("Content-Type", "application/json").
				header("Cookie", "JSESSIONID="+sessionId);    

        Response res = httpRequest.put("/api/2/issue/"+issueId+"/comment/"+commentId);

		Assert.assertEquals(res.getStatusCode(), 200);

	}
	

}

