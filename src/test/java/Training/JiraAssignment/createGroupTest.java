package Training.JiraAssignment;

import java.util.ArrayList;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.randomUser;
import utils.utility;

public class createGroupTest extends utility {
	  
	static String sessionId = utility.getsession();
	
	@Test
	public void createGroup() {
		/// GETTING RANDOM STRING FUNCTION FROM UTILITY
		/// USING OF PAYLOAD TO GENERATE NEW STRING RANDOM FOR GROUP CREATION AS IT IS UNIQUE 
		/// UNCOMMENTING THE PROP FILE CODE WILL FETCH THE BODY FROM group.json
		   extentTest = extent.startTest("Create Group Test");
	        RestAssured.baseURI = prop.getProperty("url");
	        Map<String,String> word =(Map<String, String>) randomUser.RandomString();
	      
	       //// File body = new File(prop.getProperty("group_body"));
          ///// RequestSpecification httpRequest = RestAssured.given().auth().preemptive().basic(prop.getProperty("username"),prop.getProperty("password"));
	        RequestSpecification httpRequest = RestAssured.given().auth().preemptive().basic(prop.getProperty("username"),
	                prop.getProperty("password"));
	        Response res = httpRequest.body(word).header("Cookie", "JSESSIONID=" + sessionId)
	                .header("Content-Type", "application/json").when().post("/api/2/group").then().log().all().extract()
	                .response();
	        
	        JsonPath resp = res.jsonPath();
	        // for each loop for each time random group name geneartaee
	        groupName = resp.get("name");
	        for (String tab : word.values()) {
	            // do something with tab
	        	 Assert.assertEquals(groupName,tab);
	        }
	       
	        
	    }

}
