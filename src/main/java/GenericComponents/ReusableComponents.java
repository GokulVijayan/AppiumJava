package GenericComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Stopwatch;
import Reports.TestReportSteps;
import Utilities.Constant;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ReusableComponents {

	/// <summary>
	/// Click the element specified
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="by"></param>
	/// <param name="selector"></param>
	public static void Click(AppiumDriver<MobileElement> driver, String by, String selector) throws Exception {
		MobileElement element = FindElement(driver, by, selector);
		element.click();
	}

	/// <summary>
	/// Click the element specified using javascript executor
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="by"></param>
	/// <param name="selector"></param>
	public static void JEClick(AppiumDriver<MobileElement> driver, String by, String selector) throws Exception {
		MobileElement element = FindElement(driver, by, selector);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	/// <summary>
	/// Find the element specified and send specified input testdata
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="by"></param>
	/// <param name="selector"></param>
	/// <param name="testData"></param>
	public static void SendKeys(AppiumDriver<MobileElement> driver, String by, String selector, String testData) throws Exception {
		MobileElement element = FindElement(driver, by, selector);
		element.sendKeys(testData);
	}

	/// <summary>
	/// Find the element using selector specified and clear the content
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="by"></param>
	/// <param name="selector"></param>
	public static void Clear(AppiumDriver<MobileElement> driver, String by, String selector) throws Exception {
		MobileElement element = FindElement(driver, by, selector);
		element.clear();
	}

	/// <summary>
	/// Compare two input strings
	/// </summary>
	/// <param name="firstText"></param>
	/// <param name="secondText"></param>
	/// <returns></returns>
	public static boolean CompareText(String firstText, String secondText) {
		if (firstText.equals(secondText))
			return true;
		else
			return false;
	}

	/// <summary>
	/// Check if first input text contains second input text specified
	/// </summary>
	/// <param name="firstText"></param>
	/// <param name="secondtext"></param>
	/// <returns></returns>
	public static boolean ContainsText(String firstText, String secondtext) {
		if (firstText.contains(secondtext))
			return true;
		else
			return false;
	}

	/// <summary>
	/// Move to an element not visible in the screen
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="element"></param>
	/// <returns></returns>
	public static Actions MoveToElement(AppiumDriver<MobileElement> driver, MobileElement element) {
		Actions builder = new Actions(driver);
		builder.moveToElement(element).build().perform();

		return builder;
	}

	/// <summary>
	/// Retrieve text from the selector specified
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="by"></param>
	/// <param name="selector"></param>
	/// <returns>Text retrieved from selector</returns>
	public static String RetrieveText(AppiumDriver<MobileElement> driver, String by, String selector) throws Exception {
		MobileElement element = null;
		element = FindElement(driver, by, selector);
		return element.getText();
	}

	/// <summary>
	/// Retrieve text from selector specified and compare with the inputText
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="By"></param>
	/// <param name="selector"></param>
	/// <param name="inputText"></param>
	/// <returns></returns>
	public static boolean RetrieveAndCompareText(AppiumDriver<MobileElement> driver, String By, String selector, String inputText)
			throws Exception {
		String selectorText = RetrieveText(driver, By, selector);

		if (selectorText.equals(inputText))
			return true;
		else
			return false;
	}

	/// <summary>
	/// Returns the element count inside an list
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="by"></param>
	/// <param name="selector"></param>
	/// <returns>Element Count</returns>
	public static int ElementsCount(AppiumDriver<MobileElement> driver, String by, String selector) throws Exception {
		List<MobileElement> element = FindElements(driver, by, selector);
		return element.size();

	}

	/// <summary>
	/// Find element by the selector specified and return element
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="by"></param>
	/// <param name="selector"></param>
	/// <returns></returns>
	public static MobileElement FindElement(AppiumDriver<MobileElement> driver, String by, String selector) throws Exception {
		MobileElement element = null;
		switch (by) {
		case "Id":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElement(By.id(selector));
			break;
		case "XPath":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElement(By.xpath(selector));
			break;
		case "ClassName":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElement(By.className(selector));
			break;
		case "Name":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElement(By.name(selector));
			break;
		case "LinkText":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElement(By.linkText(selector));
			break;
		case "CssSelector":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElement(By.cssSelector(selector));
			break;
		case "TagName":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElement(By.tagName(selector));
			break;
		case "PartialLinkText":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElement(By.partialLinkText(selector));
			break;
		default:
			System.out.println("Can't retrieve text");
			break;
		}
		return element;
	}

	/// <summary>
	/// Find list of elements by the selector specified and return element
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="by"></param>
	/// <param name="selector"></param>
	/// <returns></returns>
	public static List<MobileElement> FindElements(AppiumDriver<MobileElement> driver, String by, String selector) throws Exception {
		List<MobileElement> element = null;
		switch (by) {
		case "Id":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElements(By.id(selector));
			break;
		case "XPath":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElements(By.xpath(selector));
			break;
		case "ClassName":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElements(By.className(selector));
			break;
		case "Name":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElements(By.name(selector));
			break;
		case "LinkText":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElements(By.linkText(selector));
			break;
		case "CssSelector":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElements(By.cssSelector(selector));
			break;
		case "TagName":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElements(By.tagName(selector));
			break;
		case "PartialLinkText":
			WaitUntilElementVisible(driver, by, selector);
			element = driver.findElements(By.partialLinkText(selector));
			break;
		default:
			System.out.println("Can't find elements");
			break;
		}
		return element;
	}

	/// <summary>
	/// Wait until element visible
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="by"></param>
	/// <param name="selector"></param>
	public static void WaitUntilElementVisible(AppiumDriver<MobileElement> driver, String by, String selector) throws Exception {
		Stopwatch sw = Stopwatch.createStarted();
		boolean found = false;
		switch (by) {
		case "Id":
			do {
				try {
					driver.findElement(By.id(selector));
					found = true;
				} catch (Exception e) {
				}
				if (sw.elapsed(TimeUnit.MILLISECONDS) > Constant.waitTimeout) {
					System.out.println("Timed out : Element not visible in screen "+"Selector is :"+selector+" "+by);
					sw.reset();
					throw new Exception();
				}
			} while (!found);
			break;
		case "ClassName":
			do {
				try {
					driver.findElement(By.className(selector));
					found = true;
				} catch (Exception e) {
				}
				if (sw.elapsed(TimeUnit.MILLISECONDS) > Constant.waitTimeout) {
					System.out.println("Timed out : Element not visible in screen "+"Selector is :"+selector+" "+by);
					sw.reset();
					break;
				}
			} while (!found);
			break;
		case "XPath":
			do {
				try {
					driver.findElement(By.xpath(selector));
					found = true;
				} catch (Exception e) {
				}
				if (sw.elapsed(TimeUnit.MILLISECONDS) > Constant.waitTimeout) {
					System.out.println("Timed out : Element not visible in screen "+"Selector is :"+selector+" "+by);
					sw.reset();
					break;
				}
			} while (!found);
			break;
		case "CssSelector":
			do {
				try {
					driver.findElement(By.cssSelector(selector));
					found = true;
				} catch (Exception e) {
				}
				if (sw.elapsed(TimeUnit.MILLISECONDS) > Constant.waitTimeout) {
					System.out.println("Timed out : Element not visible in screen "+"Selector is :"+selector+" "+by);
					sw.reset();
					break;
				}
			} while (!found);
			break;
		}
		if (found == false) {
			throw new Exception();
		}
	}

	/// <summary>
	/// Wait until element invisible
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="by"></param>
	/// <param name="selector"></param>
	public static void WaitUntilElementInvisible(AppiumDriver<MobileElement> driver, String by, String selector) throws Exception {
		Stopwatch sw = Stopwatch.createStarted();
		boolean found = false;
		switch (by) {
		case "Id":
			do {
				try {
					driver.findElement(By.id(selector));
				} catch (Exception e) {
					found = true;
				}
				if (sw.elapsed(TimeUnit.MILLISECONDS) > Constant.waitTimeout) {
					System.out.println("Timed out : Element not visible in screen "+"Selector is :"+selector+" "+by);
					sw.reset();
					break;
				}
			} while (!found);
			break;
		case "ClassName":
			do {
				try {
					driver.findElement(By.className(selector));
				} catch (Exception e) {
					found = true;
					// Console.WriteLine(e);
				}
				if (sw.elapsed(TimeUnit.MILLISECONDS) > Constant.waitTimeout) {
					System.out.println("Timed out : Element not visible in screen "+"Selector is :"+selector+" "+by);
					sw.reset();
					break;
				}
			} while (!found);
			break;
		case "XPath":
			do {
				try {
					driver.findElement(By.xpath(selector));
				} catch (Exception e) {
					found = true;
					// Console.WriteLine(e);
				}
				if (sw.elapsed(TimeUnit.MILLISECONDS) > Constant.waitTimeout) {
					System.out.println("Timed out : Element not visible in screen "+"Selector is :"+selector+" "+by);
					sw.reset();
					break;
				}
			} while (!found);
			break;
		case "CssSelector":
			do {
				try {
					driver.findElement(By.cssSelector(selector));
				} catch (Exception e) {
					found = true;
					// Console.WriteLine(e);
				}
				if (sw.elapsed(TimeUnit.MILLISECONDS) > Constant.waitTimeout) {
					System.out.println("Timed out : Element not visible in screen "+"Selector is :"+selector+" "+by);
					sw.reset();
					break;
				}
			} while (!found);
			break;
		}
		if (found == false) {
			throw new Exception();
		}

	}

	/// <summary>
	/// Find element and retrieve value of the attribute specified
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="by"></param>
	/// <param name="selector"></param>
	public static String RetrieveAttributeValue(AppiumDriver<MobileElement> driver, String by, String elementSelector,
			String attributeSelector) throws Exception {
		MobileElement element = FindElement(driver, by, elementSelector);
		String attribute = element.getAttribute(attributeSelector);
		return attribute;
	}

	/// <summary>
	/// Finds if element exists.
	/// </summary>
	/// <param name="driver">The driver.</param>
	/// <param name="by">The by.</param>
	/// <param name="selector">The selector.</param>
	/// <returns></returns>
	public static boolean FindIfElementExists(AppiumDriver<MobileElement> driver, String by, String selector) {
		boolean isFound = false;
		switch (by) {
		case "Id":
			isFound = CheckElementVisible(driver, by, selector);
			break;

		case "XPath":
			isFound = CheckElementVisible(driver, by, selector);
			break;

		case "ClassName":
			isFound = CheckElementVisible(driver, by, selector);
			break;

		case "Name":
			isFound = CheckElementVisible(driver, by, selector);
			break;

		case "LinkText":
			isFound = CheckElementVisible(driver, by, selector);
			break;

		case "CssSelector":
			isFound = CheckElementVisible(driver, by, selector);
			break;

		case "TagName":
			isFound = CheckElementVisible(driver, by, selector);
			break;

		case "PartialLinkText":
			isFound = CheckElementVisible(driver, by, selector);
			break;

		default:
			System.out.println("Can't retrieve text");
			break;
		}
		return isFound;
	}

	/// <summary>
	/// Wait until element visible
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="by"></param>
	/// <param name="selector"></param>
	public static boolean CheckElementVisible(AppiumDriver<MobileElement> driver, String by, String selector) {
		Stopwatch sw = Stopwatch.createStarted();
		boolean found = false;
		switch (by) {
		case "Id":
			try {
				driver.findElement(By.id(selector));
				found = true;
			} catch (Exception e) {
				found = false;
			}
			if (sw.elapsed(TimeUnit.MILLISECONDS) > Constant.waitTimeout) {
				System.out.println("Timed out : Element not visible in screen "+"Selector is :"+selector+" "+by);
				sw.reset();
				found = false;
			}
			break;

		case "ClassName":
			try {
				driver.findElement(By.className(selector));
				found = true;
			} catch (Exception e) {
				found = false;
			}
			if (sw.elapsed(TimeUnit.MILLISECONDS) > Constant.waitTimeout) {
				System.out.println("Timed out : Element not visible in screen "+"Selector is :"+selector+" "+by);
				sw.reset();
				found = false;
				break;
			}
			break;

		case "XPath":
			try {
				driver.findElement(By.xpath(selector));
				found = true;
			} catch (Exception e) {
				found = false;
			}
			if (sw.elapsed(TimeUnit.MILLISECONDS) > Constant.waitTimeout) {
				System.out.println("Timed out : Element not visible in screen "+"Selector is :"+selector+" "+by);
				sw.reset();
				found = false;
				break;
			}
			break;

		case "CssSelector":
			try {
				driver.findElement(By.cssSelector(selector));
				found = true;
			} catch (Exception e) {
				found = false;
			}
			if (sw.elapsed(TimeUnit.MILLISECONDS) > Constant.waitTimeout) {
				System.out.println("Timed out : Element not visible in screen "+"Selector is :"+selector+" "+by);
				sw.reset();
				found = false;
				break;
			}
			break;
		}
		return found;
	}

	/// <summary>
	/// Retrieve text from selector specified and check whether input text is
	/// contained in the retrieved text
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="By"></param>
	/// <param name="selector"></param>
	/// <param name="inputText"></param>
	/// <returns></returns>
	public static boolean RetrieveAndContainsText(AppiumDriver<MobileElement> driver, String By, String selector, String inputText)
			throws Exception {
		String selectorText = RetrieveText(driver, By, selector);

		if (selectorText.contains(inputText))
			return true;
		else
			return false;
	}

	/// <summary>
	/// Find index of the element with value specified as inputValue
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="by"></param>
	/// <param name="selector1"></param>
	/// <param name="selector2"></param>
	/// <param name="inputValue"></param>
	/// <returns></returns>
	public static int FindElementIndex(AppiumDriver<MobileElement> driver, String by, String selector1, String selector2,
			String inputValue) throws Exception {
		int count, i;
		boolean isFound;
		count = ElementsCount(driver, "XPath", selector1);

		for (i = 1; i <= count; i++) {
			isFound = RetrieveAndContainsText(driver, by, selector1 + "[" + i + "]" + selector2, inputValue);
			if (isFound) {
				return i;
			}
		}

		return 0;
	}

	

	/// <summary>
	/// Retrieves number from a string
	/// </summary>
	/// <param name="Text"></param>
	/// <returns></returns>
	public static int GetNumber(String Text) {
		int val = 0;
		for (int i = 0; i < Text.length(); i++) {
			char c = Text.charAt(i);
			if (c >= '0' && c <= '9') {
				val *= 10;
				// (ASCII code reference)
				val += c - 48;
			}
		}
		return val;
	}

	/// <summary>
	/// Drags an element from source location to destination location
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="by"></param>
	/// <param name="sourceSelector"></param>
	/// <param name="destinationSelector"></param>
	public static void DragAndDrop(AppiumDriver<MobileElement> driver, String by, String sourceSelector, String destinationSelector)
			throws Exception {

		MobileElement source = FindElement(driver, by, sourceSelector);
		MobileElement destination = FindElement(driver, by, destinationSelector);
		String java_script = "var src=arguments[0],tgt=arguments[1];var dataTransfer={dropEffe"
				+ "ct:'',effectAllowed:'all',files:[],items:{},types:[],setData:fun"
				+ "ction(format,data){this.items[format]=data;this.types.push(for"
				+ "mat);},getData:function(format){return this.items[format];},clea"
				+ "rData:function(format){}};var emit=function(event,target){var ev"
				+ "t=document.createEvent('Event');evt.initEvent(event,true,false);"
				+ "evt.dataTransfer=dataTransfer;target.dispatchEvent(evt);};emit('"
				+ "dragstart',src);emit('dragenter',tgt);emit('dragover',tgt);emit("
				+ "'drop',tgt);emit('dragend',src);";

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(java_script, source, destination);

	}

	


	

	/// <summary>
	/// Generates random Number
	/// </summary>
	/// <param name="count"></param>
	/// <returns></returns>
	public static String getRandomString(int n) {

		String AlphaString = "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			int index = (int) (AlphaString.length() * Math.random());
			sb.append(AlphaString.charAt(index));
		}
		return sb.toString();
	}

	/// <summary>
	/// Generates random string
	/// </summary>
	/// <param name="count"></param>
	/// <returns></returns>
	public static String getRandomNumber(int n) {

		String NumericString = "123456789";

		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			int index = (int) (NumericString.length() * Math.random());
			sb.append(NumericString.charAt(index));
		}
		return sb.toString();
	}

	

	/// <summary>
	/// Retrieves Absolute Path of an File
	/// </summary>
	/// <param name="fileOrDirectoryName"></param>
	/// <returns></returns>
	public static String GetAbsoluteFilePath(String fileOrDirectoryName) {
		File f = new File(fileOrDirectoryName);
		String absolute = f.getAbsolutePath();
		return absolute;
	}

	

	/// <summary>
	/// Reads the excel file.
	/// </summary>
	/// <param name="fileName">Name of the file.</param>
	/// <returns></returns>
	public static Workbook ReadExcelFile(String filePath, String fileName) throws Exception {
		// Create an object of File class to open xlsx file
		File file = new File(filePath + "\\" + fileName);
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		// Find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			workbook = new XSSFWorkbook(inputStream);
		}
		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of HSSFWorkbook class
			workbook = new HSSFWorkbook(inputStream);
		}
		return workbook;
	}

	/// <summary>
	/// Reads the excel file.
	/// </summary>
	/// <param name="fileName">Name of the file.</param>
	/// <returns></returns>
	public static String ReadExcelFileByCell(String filePath, String fileName, String sheetName, int rowNumber,
			int columnNumber) throws Exception {
		// Create an object of File class to open xlsx file
		File file = new File(filePath + "\\" + fileName);
		// Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		// Find the file extension by splitting file name in substring and getting only
		// extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		// Check condition if the file is xlsx file
		if (fileExtensionName.equals(".xlsx")) {
			// If it is xlsx file then create object of XSSFWorkbook class
			workbook = new XSSFWorkbook(inputStream);
		}
		// Check condition if the file is xls file
		else if (fileExtensionName.equals(".xls")) {
			// If it is xls file then create object of HSSFWorkbook class
			workbook = new HSSFWorkbook(inputStream);
		}
		// Read sheet inside the workbook by its name
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNumber);
		String value = row.getCell(columnNumber).getStringCellValue();

		return value;
	}

	/// <summary>
	/// Reads the property file.
	/// </summary>
	/// <param name="fileName">Name of the file,property name.</param>
	/// <returns></returns>
	public static String readDataPropertyFile(String fileName, String strQuery) {
		Properties prop = new Properties();
		File file = new File(fileName);
		String strData = "";
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(fileInput);
			strData = prop.getProperty(strQuery);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strData;
	}
	
//	 Perform page refresh
//	 @param driver
//	 @param wait
	public static void RefreshApp(AppiumDriver<MobileElement> driver, WebDriverWait wait) {
		try {
			driver.getPageSource();
		} catch (Exception e) {
			System.out.println("Refreshed app");
			throw (e);
		}
	}
	
	
			/// </summary>
			/// <param name="actionItemValue"></param>
			/// <param name="message"></param>
			/// <param name="testObjective"></param>
			/// <param name="stepCount"></param>
			/// <returns></returns>
			public static TestReportSteps GenerateReportSteps(String actionItemValue, String message, String testObjective,
					int stepCount) {
				String replaceText;
				TestReportSteps addReport = new TestReportSteps();
				if (stepCount == 0) {
					addReport.testObjective = testObjective;
				} else {
					addReport.testObjective = "";
				}
				if (!(actionItemValue).toString().isEmpty()) {
					// Set report
					addReport.stepName = Integer.toString(stepCount+1);
					addReport.stepDescription = (actionItemValue);
					if(actionItemValue.toLowerCase().contains("screenshot"))
					{
						replaceText=actionItemValue.toLowerCase().replace("capture screenshot.","");
						addReport.expectedResult = ("User is able to " + replaceText.toLowerCase());
						addReport.actualResultPass = ("User was able to " + replaceText.toLowerCase());
						addReport.actualResultFail = ("User was not able to " + replaceText.toLowerCase());
						
					}
					else
					{			
					addReport.expectedResult = ("User is able to " + actionItemValue.toLowerCase());
					addReport.actualResultPass = ("User was able to " + actionItemValue.toLowerCase());
					addReport.actualResultFail = ("User was not able to " + actionItemValue.toLowerCase());
					}
					
				} else {
					addReport.stepName = Integer.toString(stepCount+1);
					addReport.stepDescription = (message);
					addReport.expectedResult = ("");
					addReport.actualResultPass = ("");
					addReport.actualResultFail = ("");

				}
				return addReport;
			}
    
//	 Tap the element specified 
//	 @param driver - Appium driver
//	 @param wait - Wait timeout in seconds
//	 @param by - identifier
//	 @param clsname - eg.//android.widget.TextView
//	 @param texttobeclicked - the text on which the click should happen
//	 @throws Exception 
	 
	public static void clickText(AppiumDriver<MobileElement> driver, WebDriverWait wait,String by,String clsname, String texttobeclicked) throws Exception
	{
		try
		{
			System.out.println(clsname+ "[@text='"+texttobeclicked+"']");
			FindElement(driver, by, clsname+ "[@text='"+texttobeclicked+"']").click();
		}
		catch(Exception e)			{
			System.out.println("Click operation failed");
			throw(e);
		}
	}
	
//	  Swipe from specified first element to second element
//	  @param driver
//	  @param elementLocator1-Locator string of first element
//	  @param elementLocator2-Locator string of second element
//	  @throws InterruptedException
	public static void swipeFromElementToElement(AppiumDriver<MobileElement> driver, String elementLocator1, String elementLocator2) throws InterruptedException 
	{
		try 
		{
			MobileElement element1 = driver.findElement(By.xpath(elementLocator1));
			MobileElement element2;
	
			element2 = driver.findElement(By.xpath(elementLocator2));
		
			@SuppressWarnings("rawtypes")
			TouchAction action = new TouchAction(driver);
			int x1 = element1.getLocation().getX();
			int y1 = element1.getLocation().getY();
		
			int x2 = element2.getLocation().getX();
			int y2 = element2.getLocation().getY();


			action.press(PointOption.point(x1, y1)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2))).moveTo(PointOption.point(x2, y2)).release().perform();
		} 
		catch (Exception e) 
		{
			System.out.println("Swipe operation failed");
			throw (e);
		}
	}
	
//	  Format the date time in specified format
//	  @param format
//	  @return
	public static String retieveFormattedDateTime(String format)
	{
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			Date dateObject = new Date();
			String formattedCurrentDateTime = dateFormat.format(dateObject);
			return formattedCurrentDateTime;
		}
		catch(Exception e)
		{
			System.out.println("Formatting failed");
			throw(e);
		}
	}
	
	
	/// <summary>
	/// Scroll to element specified using selector
	/// </summary>
	/// <param name="driver"></param>
	/// <param name="by"></param>
	/// <param name="selector"></param>
	public static MobileElement ScrollToElement(AppiumDriver<MobileElement> driver,String className,String Text)
	{
		 MobileElement element = ((AndroidDriver<MobileElement>) driver).findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\""+className+"\")).scrollIntoView("
  				+ "new UiSelector().text(\""+Text+"\"))");
		return element;
		 
	}
	
	
}
