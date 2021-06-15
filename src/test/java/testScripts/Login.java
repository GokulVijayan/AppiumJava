package testScripts;




import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Reports.ReportModifier;
import Reports.TestReportSteps;
import Utilities.ConfigFile;
import pages.LoginPage;
import projectConfig.Setup;

@Listeners({ ReportModifier.class })
public class Login extends Setup{

	public static JSONArray inputjson;
	LoginPage loginPage;
	public JSONObject listOfInputs;

	public Login() throws Exception {
		testObjective = "To Verify that user is able to login to iroads application";		
		scriptName="IR001_LoginToApplication";
		loginPage=new LoginPage();	
		inputjson=ConfigFile.RetrieveTestData("LoginTest.json");
	}
	

	@Test
	public void login() throws Exception {

		List<TestReportSteps> report = null;

		for (int i = 0; i < inputjson.length(); i++) {
			listOfInputs = inputjson.getJSONObject(i);
		}	
		report = loginPage.LoginToApplication(driver, listOfInputs);
		finalreport.addAll(report);		
		for (String s : loginPage.getFilePath()) {
			screenshotList.add(s);
		}
		report.clear();
		report = loginPage.LoginToApplication(driver, listOfInputs);
		finalreport.addAll(report);		
		for (String s1 : loginPage.getFilePath()) {
			screenshotList.add(s1);
		}
		report.clear();
	}

	
	


}
