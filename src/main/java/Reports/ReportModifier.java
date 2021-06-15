package Reports;

import java.io.IOException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.testng.ITestContext;
import org.testng.TestListenerAdapter;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Utilities.Constant;

public class ReportModifier extends TestListenerAdapter {
	public static int count;
	public static long startTime, endTime;
	public static long finalTime;
	public static int scriptCount;

	@Override
	public void onStart(ITestContext testContext) {

		super.onStart(testContext);
		count = testContext.getAllTestMethods().length;
		System.out.println("Number of Test Methods: " + testContext.getAllTestMethods().length);
	}

	public static void CheckTestCount() {
		count--;
	}

	
	public static void setUpConfiguration() {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		Document doc = null;
		DocumentBuilder docBuilder;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.parse(Constant.currentDirectory+"\\testng.xml"); // path to your testng.xml
			NodeList parameterNodes = doc.getElementsByTagName("test");
			scriptCount=parameterNodes.getLength();
			System.out.println("Total Number of tests to be executed are : " + parameterNodes.getLength());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Exception while reading counting tests in testng.xml");
		}
	}
	
	

}
