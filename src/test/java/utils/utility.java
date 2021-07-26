package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class utility {
	
	//Global Variables
	public static String sessionId = null;
	public static String issueId = null;
	public static String commentId = null;
	public static String key = null;
	public static String groupName = null;
	
	// loading config.properties file :
		static File file = new File(".\\resources\\config.properties");
		static FileInputStream fis = null;
	//loading log4j.properties	
		 public static String LOG_FILE = ".\\Resources\\log4j.properties";
		static FileInputStream logfile = null;
		
		public static Properties prop = new Properties();

		static {
			// handling exceptions (if file is not found)
			try {
				fis = new FileInputStream(file);
				logfile = new FileInputStream(LOG_FILE);
				PropertyConfigurator.configure(logfile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				prop.load(fis);
				prop.load(logfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
		
		
		   public static Logger logger =  Logger.getLogger(utility.class.getName());
		
			public static ExtentReports extent; // set path where report would be generated
			public static ExtentTest extentTest;
			
			//path to save file reports
			@BeforeSuite
			public void setExtent() {
				extent = new ExtentReports(".\\Reports\\reports.html");
			}

			@AfterSuite
			public void endReport() {
				extent.flush();
				extent.close();
			}
			@AfterMethod
			public void endReportStatus(ITestResult result) {
				if (result.getStatus() == ITestResult.FAILURE) {
					extentTest.log(LogStatus.FAIL, "--------Valid Test case failed-------- ");
				} else if (result.getStatus() == ITestResult.SUCCESS) {
					extentTest.log(LogStatus.PASS, "-------Valid scenario test case passed--------");
				}
				extent.endTest(extentTest);
			}  
			
			@AfterMethod
			public void endLogStatus(ITestResult result) {
				if (result.getStatus() == ITestResult.FAILURE) {
				 logger.info(result.getName() + "------TEST CASE FAILED-------");
				} else if (result.getStatus() == ITestResult.SUCCESS) {
				 logger.info(result.getName() + "-------TEST CASE PASSED--------");
				 
				}
			}  
			
			
	// FUNCTION FOR GET SESSION
	@BeforeClass
	public static String getsession() {

		RestAssured.baseURI = prop.getProperty("url");
		File user = new File(prop.getProperty("user_body"));
		RequestSpecification httpRequest = RestAssured.given();
		Response res = httpRequest.body(user).header("Content-Type", "application/json").when().post("auth/1/session");
		JsonPath jsonpath = res.jsonPath();
		sessionId = jsonpath.get("session.value");
		System.out.println(sessionId);
		return sessionId;
	}
	
  //FUNCTION FOR CREATE ISSUE
	public  static String createIssue() {
		
		RestAssured.baseURI = prop.getProperty("url");
		File user = new File(prop.getProperty("issue_body"));
		RequestSpecification httpRequest = RestAssured.given();
		Response res = httpRequest.body(user).header("Content-Type", "application/json")
				.header("Cookie", "JSESSIONID=" + sessionId).when().post("/api/2/issue").then().log().all().extract()
				.response();

		JsonPath resp = res.jsonPath();
		issueId = resp.get("id");
		return issueId;

	}

	//FUNCTION TO ADD COMMENT
	public static String AddComment() {

		RestAssured.baseURI = prop.getProperty("url");
		File comm = new File(prop.getProperty("new_comment_body"));

		RequestSpecification httpRequest = RestAssured.given().auth().preemptive().basic(prop.getProperty("username"),
				prop.getProperty("password"));
		Response res = httpRequest.body(comm).header("Content-Type", "application/json").when()
				.post("/api/2/issue/" + issueId + "/comment").then().log().all().extract().response();

		JsonPath resp = res.jsonPath();
		commentId = resp.get("id");
		return commentId ;
	}

	// FUNCTION TO CREATE NEW USER
	public static String createnewuser() {		
		RestAssured.baseURI = prop.getProperty("url");
		File body = new File(prop.getProperty("newuser_body_del"));

		RequestSpecification httpRequest = RestAssured.given().auth().preemptive().basic(prop.getProperty("username"),
				prop.getProperty("password"));
		Response res = httpRequest.body(body).header("Content-Type", "application/json").when().post("/api/2/user/")
				.then().log().all().extract().response();

		JsonPath resp = res.jsonPath();		
		resp.prettyPrint();
		 key = resp.get("key");
		
	    return key;
	}

}