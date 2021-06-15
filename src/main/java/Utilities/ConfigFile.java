package Utilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ConfigFile {

	static String browserName,driverPath,url;
    private static AppiumDriver<MobileElement> driver;

	
    /// <summary>
    /// To retrieve testdata array input in json array format for the input file specified
    /// </summary>
    /// <param name="fileName">Input testdata file name</param>
	public static JSONArray RetrieveTestData(String fileName) throws Exception
    {
		String path1 = "src\\test\\java\\resource\\testdata";
		File file = new File(path1);
		String path = file.getAbsolutePath()+"\\"+fileName;
		String data = new String(Files.readAllBytes(Paths.get(path))); 
        JSONObject obj = new JSONObject(data);
        JSONArray testData = obj.getJSONArray("TestData");
        return testData;
    }
	
	  /// <summary>
    /// Returns UiMap object
    /// </summary>
    /// <param name="fileName"></param>
    /// <returns>JObject</returns>
	public static JSONObject RetrieveUIMap(String fileName) throws IOException, JSONException
    {
		String path1 = "src\\test\\java\\resource\\uiselectors";
		File file = new File(path1);
		String path = file.getAbsolutePath()+"\\"+fileName;
		String data = new String(Files.readAllBytes(Paths.get(path))); 
        JSONObject obj = new JSONObject(data);
        return obj;
    }
	
	
    /// <summary>
    /// Retrieve current date time in format 'MM-dd-yyyy HH-mm-ss'
    /// </summary>
    /// <returns></returns>
    public static String GetCurrentDateTime() throws Exception
    {
    	LocalDateTime datetime = LocalDateTime.now();
    	DateTimeFormatter datetimeformat = DateTimeFormatter.ofPattern(ConfigFile.GetAppConfig("dateTimeFormat").toString());
        return datetime.format(datetimeformat);
    }
	
	
	/// <summary>
    /// Returns configuration settings
    /// </summary>
    /// <returns>Configuration</returns>
    public static String GetAppConfig(String settings) throws Exception
    {
    	String path1 = "src\\main\\java\\projectConfig\\AppSettings.json";
		File file = new File(path1);
		String path = file.getAbsolutePath();
		String data = new String(Files.readAllBytes(Paths.get(path))); 
        JSONObject obj = new JSONObject(data);
        return obj.get(settings).toString();
    }
	
    /**
	 * Retrieve pax app apk specified in constant file
	 * @return application apk if apk ! = null else returns runtime exception
     * @throws Exception 
	 */
	public static String GetApk() throws Exception {
		String apk = Constant.currentDirectory+"\\src\\test\\java\\resource\\apkfiles\\"+ConfigFile.GetAppConfig("appName");
		return apk;
	}
	
	  /// <summary>
    /// Initialize driver for the capabilities specified in appSettings and navigate to the URL specified in appSettings 
    /// </summary>
    /// <returns>driver - Initialized driver</returns>
    public static AppiumDriver<MobileElement> Init() throws Exception
    {
       
    	
    	DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", ConfigFile.GetAppConfig("deviceName").toString());
		capabilities.setCapability("udid", ConfigFile.GetAppConfig("udid").toString());
		capabilities.setCapability("platformName", ConfigFile.GetAppConfig("platformName").toString());
		capabilities.setCapability("platformVersion", ConfigFile.GetAppConfig("platformVersion").toString()); 
		capabilities.setCapability("app", ConfigFile.GetApk());
		capabilities.setCapability("appPackage", ConfigFile.GetAppConfig("appPackage").toString());
		capabilities.setCapability("appActivity", ConfigFile.GetAppConfig("appActivity").toString());
		capabilities.setCapability("newCommandTimeout", ConfigFile.GetAppConfig("newCommandTimeout").toString());
		capabilities.setCapability("disableWindowAnimation", true);
		capabilities.setCapability("noReset", true);
		capabilities.setCapability("fullReset", false);
		capabilities.setCapability("autoGrantPermissions",true);
		
		try {
			driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return driver;
    }
	
	
}
