package Reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;

import GenericComponents.ReusableComponents;
import Utilities.Constant;

public class Report {
	 public static String htmlFilepath;
     public static int failedSteps=0;
     public static int passedSteps=0,scriptCount=0;
     public static int passedScripts,failedScripts,count;
     public static String status,getStatus;

     /**
  	 * Generate html report
  	 * @param items - report steps
  	 * @param screenshotPath - array of screenshot path
  	 * @param getCurrentDateTime
  	 * @param getCurrentTime
  	 * @param count - 0 append to single report , else multiple report file
  	 */
  	
  	public static void WriteResultToHtml(WebDriver driver, List<TestReportSteps> items, List<String> screenshotPath, String testObjective,String scriptName) {
  		try {
  			if(scriptCount==0)
  			scriptCount=ReportModifier.scriptCount;
  			 
  			String browserName = Constant.browserName;
  			//String getCurrentDate = Constant.currentDate;
  			//String getCurrentTime = Constant.currentTime;
  			
  			DateFormat df = new SimpleDateFormat("dd/MM/yy, HH:mm:ss");
  			Date dateobj = new Date();
  			String currentDateTime = df.format(dateobj);
  			int index = 0;
  			String filePath = null;
  			
  			StringBuilder color = new StringBuilder();
  			StringBuilder status = new StringBuilder();
  			// define a HTML String Builder
  			StringBuilder actualResult = new StringBuilder();
  			StringBuilder htmlStringBuilder = new StringBuilder();
  			// append html header and title
  			htmlStringBuilder.append(
  					"<html><head><link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\">\r\n"
  							+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script><title>Selenium Test </title></head>");
  			// append body
  			htmlStringBuilder.append("<body>");
  			// append table
  			
  				
  			  htmlStringBuilder.append("<table class=\"table table-striped table-bordered\">");
               htmlStringBuilder.append("<tr><th style=\"background-color:#a6a6a6\">Date Time</th><td>"
                       + currentDateTime
                       + "</td><th style=\"background-color:#a6a6a6\">Environment Tested</th><td>QC</td></tr><tr><th style=\"background-color:#a6a6a6\">OS</th><td>Windows</td><th style=\"background-color:#a6a6a6\">Browser</th><td>" + browserName + "</td></tr><tr><th style=\"background-color:#a6a6a6\">Script Name</th><td>"+ scriptName+"</td><th style=\"background-color:#a6a6a6\">Version</th><td>1.0</td></tr><tr><th style=\"background-color:#a6a6a6\">Objective</th><td colspan=\""
                       + 3 + "\">" + testObjective + "</td><tr><tr></table>");
  			
  			// append row
  			htmlStringBuilder.append("<table class=\"table table-striped\">");
  			htmlStringBuilder.append(
  					"<thead style=\"background-color:#007acc\"><tr><th><b>TestObjective</b></th><th><b>StepName</b></th><th><b>StepDescription</b></th><th><b>ExpectedResult</b></th><th><b>ActualResult</b></th><th><b>Status</b></th><th><b>Screenshot</b></th></tr></thead><tbody>");
  			// append row
  			for (TestReportSteps a : items) {
  				if (!(a.GetActualResultFail().isEmpty())) {
  					status.append("Fail");
  					color.append("red");
  					failedSteps++;
  					 actualResult.append(a.GetActualResultFail());

  				} else {
  					status.append("Pass");
  					color.append("green");
  					passedSteps++;
  					 actualResult.append(a.GetActualResultPass());
  				}
  				if (a.GetStepDescription().toLowerCase().contains("screenshot")) 
  				{
  					try {
  						filePath = screenshotPath.get(index++);
  						filePath=Constant.currentDirectory+"/Results/"+filePath;
  					
  						htmlStringBuilder.append("<tr><td style=\"font-weight:bold\">" + a.GetTestObjective() + "</td><td>" + a.GetStepName()
  								+ "</td><td>" + a.GetStepDescription() + "</td><td>" + a.GetExpectedResult() + "</td><td>"
  								+ actualResult + "</td><td style=\"color:" + color + ";font-weight:bolder;\">" + status
  								+ "</td><td><a href=\"" +filePath + "\">Click here</a></td></tr>");
  					}
  					catch(Exception e)
  					{
  						htmlStringBuilder.append("<tr><td style=\"font-weight:bold\">" + a.GetTestObjective() + "</td><td>" + a.GetStepName()
  						+ "</td><td>" + a.GetStepDescription() + "</td><td>" + a.GetExpectedResult() + "</td><td>"
  						+ actualResult + "</td><td style=\"color:" + color + ";font-weight:bolder;\">" + status
  						+ "</td><td><a href=\"" +filePath + "\">Click here</a></td></tr>");
  					}
  				} 
  			
  				else 
  				{
  					htmlStringBuilder.append("<tr><td style=\"font-weight:bold\">" + a.GetTestObjective() + "</td><td>"
  							+ a.GetStepName() + "</td><td>" + a.GetStepDescription() + "</td><td>"
  							+ a.GetExpectedResult() + "</td><td>" + actualResult + "</td><td style=\"color:" + color
  							+ ";font-weight:bolder;\">" + status + "</td><td></td></tr>");
  				}
  				actualResult.delete(0, actualResult.length());
  				color.delete(0, color.length());
  				status.delete(0, status.length());
  			}
  			getStatus=getTestStatus(failedSteps,passedSteps);
  			if(getStatus=="Fail")
  			{
  			htmlStringBuilder.append("<table class=\"table table-striped table-bordered\">");
  			htmlStringBuilder.append("<tr><th style=\"background-color:#a6a6a6\">Script Execution Status</th><td style=\"color:red;font-weight:bolder;\">"
  						+ getStatus
  						+"<th style=\"background-color:#a6a6a6\">Passed Steps</th><td>"
  								+ passedSteps
  								+  "</td><th style=\"background-color:#a6a6a6\">Failed Steps</th><td>"+failedSteps+"</td></tr></table>");
  			}
  			else
  			{
  				htmlStringBuilder.append("<table class=\"table table-striped table-bordered\">");
  				htmlStringBuilder.append("<tr><th style=\"background-color:#a6a6a6\">Script Execution Status</th><td style=\"color:green;font-weight:bolder;\">"
  							+ getStatus
  							+"<th style=\"background-color:#a6a6a6\">Passed Steps</th><td>"
  									+ passedSteps
  									+  "</td><th style=\"background-color:#a6a6a6\">Failed Steps</th><td>"+failedSteps+"</td></tr></table>");
  			}
  			
  			setValues();
  			setTestScriptCount();
  			count=getTestScriptCount();
  			if(count==0)
  			{
  				if(failedScripts>0)
  				{
  				htmlStringBuilder.append("<table class=\"table table-striped table-bordered\">");
  				htmlStringBuilder.append("<tr><th style=\"background-color:#a6a6a6;width: 482;\">Bulk Script Execution Status</th>"+"<td style=\"color:red;font-weight:bolder;\">Fail</td>"+"<th style=\"background-color:#a6a6a6\">Passed Scrips</th><td>"
  										+ passedScripts
  										+  "</td><th style=\"background-color:#a6a6a6\">Failed Scrips</th><td>"+failedScripts+"</td></tr></table>");
  				}
  				
  				else
  				{
  				htmlStringBuilder.append("<table class=\"table table-striped table-bordered\">");
  				htmlStringBuilder.append("<tr><th style=\"background-color:#a6a6a6 ;width: 482;\">Bulk Script Execution Status</th>"+"<td style=\"color:green;font-weight:bolder;\">Pass</td>"+"<th style=\"background-color:#a6a6a6\">Passed Scrips</th><td>"
  										+ passedScripts
  										+  "</td><th style=\"background-color:#a6a6a6\">Failed Scrips</th><td>"+failedScripts+"</td></tr></table>");
  					
  					
  				}	
  			}
  			// close html file
  			htmlStringBuilder.append("</tbody></table></body></html>");

  			// write html string content to a file
  			String htmlFilepath = "";

  			  htmlFilepath = ReusableComponents.GetAbsoluteFilePath("Results\\Report\\Test Report - ") + Constant.currentDate  + ".html";

  			WriteToFile(htmlStringBuilder.toString(), htmlFilepath);

  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  	}

    /**
   	* Write html report to file
   	*/	
   	public static void WriteToFile(String fileContent, String fileName) throws IOException, FileNotFoundException {

   		File file = new File(fileName);
   		file.getParentFile().mkdirs();

   		PrintWriter out = null;
   		if (file.exists() && !file.isDirectory()) {
   			out = new PrintWriter(new FileOutputStream(new File(fileName), true));
   			out.append(fileContent);
   			out.close();
   		} else {
   			// write to file with OutputStreamWriter
   			OutputStream outputStream = new FileOutputStream(file.getAbsoluteFile(), false);
   			Writer writer = new OutputStreamWriter(outputStream);
   			writer.write(fileContent);
   			writer.close();
   		}
   	}
   	
   	public static String getTestStatus(int failedSteps,int passedSteps)
  	{
  		if(failedSteps>0)
  		{
  			status="Fail";
  			failedScripts++;
  		}
  		else
  		{
  			status="Pass";
  			passedScripts++;
  		}
  		return status;
  	}
  	public static void setValues()
  	{
  		failedSteps=0;
  		passedSteps=0;
  	}
  	public static int getTestScriptCount()
  	{
  		return scriptCount;
  		
  	}
  	public static void setTestScriptCount()
  	{
  		scriptCount--;
  	}
}
