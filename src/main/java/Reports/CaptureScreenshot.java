package Reports;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import GenericComponents.ReusableComponents;


public class CaptureScreenshot {
	
	static boolean IsScreenshotCaptured = true;

	  /// <summary>
    /// Take screenshot of current browser window
    /// </summary>
    /// <param name="driver"></param>
    /// <param name="fileName"></param>
    public static String TakeSingleSnapShot(WebDriver driver, String fileName) throws Exception
    {
        String fileLocation="";
        if (driver.toString().contains("(null)"))
			IsScreenshotCaptured = false;
		else if (fileName.isEmpty())
            IsScreenshotCaptured = false;
        else
        {
        	   fileLocation= fileName + ".png";
               fileName = ReusableComponents.GetAbsoluteFilePath("Results\\Screenshots") +"\\"+ fileLocation;
               TakesScreenshot image =((TakesScreenshot)driver);
               File SrcFile=image.getScreenshotAs(OutputType.FILE);
               //Move image file to new destination
               System.out.println(fileName);
               File DestFile=new File(fileName);

               //Copy file at destination

               FileUtils.copyFile(SrcFile, DestFile);
               //Save the screenshot
          //     image.SaveAsFile(fileName, );
        }
        return "Screenshots\\"+fileLocation;
    }
}
