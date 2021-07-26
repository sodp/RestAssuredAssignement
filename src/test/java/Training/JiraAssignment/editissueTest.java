package Training.JiraAssignment;
import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.utility;

public class editissueTest extends utility {

	// CREATING ISSUE AND SESSION FROM UTILITY.JAVA & EDITING ISSUE TEST 
	String sessionId = utility.getsession();
	String issueId = utility.createIssue();
	
	@Test()
	public void editIssue() {
		// creating issue using helper function class
		extentTest = extent.startTest("Edit issue Test");
		RestAssured.baseURI = prop.getProperty("url");
		File body = new File(prop.getProperty("update_issue"));

		RequestSpecification httpRequest = RestAssured.given().auth().preemptive().basic(prop.getProperty("username"),
				prop.getProperty("password"));

		Response res = httpRequest.body(body).header("Content-Type", "application/json").when()
				.put("/api/2/issue/" + issueId).then().log().all().extract().response();
		
		Assert.assertEquals(res.getStatusCode(), 204);
	}

}
