package Training.JiraAssignment;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.utility;

public class deleteIssueInvalid  extends utility{

	// TEST TO DELETE ISSUE
	String sessionId = utility.getsession();
	String issueId =  "ÄBSBSBBASBASBABS" ;
	
	@Test
	public void deleteIssue() {
		extentTest = extent.startTest("Delete issue test");

		RestAssured.baseURI = prop.getProperty("url");
		
		RequestSpecification httpRequest = RestAssured.given();
		Response res = httpRequest.header("Content-Type", "application/json").queryParam("deleteSubtasks", "true")
				.header("Cookie", "JSESSIONID=" + sessionId).when().delete("/api/2/issue/" + issueId).then().log().all()
				.extract().response();
		
		///// ERROR 404 NO SUCH ISSUE ID EXIST
		Assert.assertEquals(res.getStatusCode(), 404);
		
	}
	
}
