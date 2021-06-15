package Utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


import GenericComponents.ReusableComponents;

public class Constant {
    public static int waitTimeout;
    public static String currentDateTime;
    public static String currentDate;
    public static String currentTime;
    public static String browserName;
    public static String reportConfigPath;
    public static final String currentDirectory = System.getProperty("user.dir");

    /// <summary>
    /// Sets values from configFile into variables
    /// </summary>
    /// <param name="file"></param>
    public static void SetConfig() throws Exception, Exception
    {
    	LocalDateTime datetime = LocalDateTime.now();
    	DateTimeFormatter datetimeformat = DateTimeFormatter.ofPattern(ConfigFile.GetAppConfig("dateTimeFormat").toString());
        currentDateTime = datetime.format(datetimeformat);
        LocalDate date = LocalDate.now(); 
    	DateTimeFormatter dateformat = DateTimeFormatter.ofPattern(ConfigFile.GetAppConfig("date").toString());
        currentDate = date.format(dateformat);
        	    LocalTime time = LocalTime.now();
        DateTimeFormatter timeformat = DateTimeFormatter.ofPattern(ConfigFile.GetAppConfig("time").toString());
        currentTime = time.format(timeformat);
        waitTimeout = Integer.parseInt(ConfigFile.GetAppConfig("timeOut"))*1000;
        reportConfigPath=ReusableComponents.GetAbsoluteFilePath("configs/extent-config.xml");
   


    }        
    
    
    
}
