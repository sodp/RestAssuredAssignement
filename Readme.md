###Automation JIRA API framework 
**URL** http://localhost:8080/rest/
#Tools Used:
 
- Rest Assured
- Java
- TestNG
- Maven 
- Extent report
- log4j
 
#Packages Description:
- src/tests/java
Contains all test cases with test scenarios

- utils
contains utility functions that are common or required in test are extended in test java files


# utils
- randomUser.java
- utility.java


# src/tests/java

- addcommentTest.java
- createGroupTest.java
- createuserTest.java
- deleteIssueTest.java
- deleteUserTest.java
- editissueTest.java
- getcommentTest.java
- updatecommentTest.java
  
 

#Tested functionalities:
 
- Create Issue
- Edit Issue
- Delete Issue
- Cart section
- Add Comment
- Get Comment
- Update Comment
- Create User
- Delete User
 

#Steps to Run (Eclipse IDE)-
 
Step:1--------------------------
Goto Eclipse and open the folder 
 
Step:2--------------------------
Click run as maven clean 
 
Step:3--------------------------
After step 2 run as maven install
 
Step:4--------------------------
@@@@@@@After step3@@@@@@@@@
- Open Reports Folder
- Click ExtentReports.html
- Open log folder
- Click log.html
 
#Steps to Run (cmd)-
 
- Step:1----------------
Goto folder ,in address bar type "cmd".
 
- Step:2----------------
type "mvn clean" in cmd and press Enter.
 
- Step:3----------------
type "mvn install" in cmd and press Enter.
 
- Step:4----------------
@@@@@@@After step3@@@@@@@@@
- Open Reports Folder
- Click ExtentReports.html
- Open log folder
- Click log.html

#Steps to Windows Batch File-
- Step 1:---------------------
Click on Runner Batch File 
- Step:2----------------
@@@@@@@After step3@@@@@@@@@
- Open Reports Folder
- Click ExtentReports.html
- Open log folder
- Click log.html
 
#Report
  -Report.html
  -log.html
   
# Note 1 : 
Add auth , username & password in resources\config.properties
# Note 2 : 
Change key value of project with the project created in localhost which is present in json files issue.json and updateissue.json
key: "SAM" --->> key:"YOUR PROJECT KEY"