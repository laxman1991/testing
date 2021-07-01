package extentReport;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
 
public class ExtentReport2 
{
ExtentReports report;
ExtentTest logger; 
WebDriver driver;
 
@Test
public void verTitle()
{
System.setProperty("webdriver.chrome.driver","D:\\JarfilesandLibraryanddrivers\\chromedriver_win32\\chromedriver\\chromedriver.exe");
report=new ExtentReports("D://JarfilesandLibraryanddrivers//ExtentReportFile//extendRun.html");
logger=report.startTest("TitleCheck");
driver=new ChromeDriver();
 System.out.println(logger.getTest());
driver.manage().window().maximize();
 
logger.log(LogStatus.INFO, "Browser started ");
 
driver.get("http://newtours.demoaut.com");
 
logger.log(LogStatus.INFO, "Site Launch successfully");
 
String title=driver.getTitle();
 
Assert.assertTrue(title.contains("Google")); 
 
logger.log(LogStatus.INFO, "Title Checking done");
}
 
 
@AfterMethod
public void tearDown(ITestResult result) throws IOException
{
if(result.getStatus()==ITestResult.FAILURE)
{
TakesScreenshot screnshot=((TakesScreenshot)driver);
File  src=  screnshot.getScreenshotAs(OutputType.FILE);
	  //File desc=new File("D:\\Screenshotabc\\abc.png");
FileUtils.copyFile(src,new File("D://Screenshot//"+result.getName()+".png"));
logger.log(LogStatus.FAIL, "Title verification failed");
}
report.endTest(logger);
report.flush();
driver.get("D://JarfilesandLibraryanddrivers//ExtentReportFile//extendRun.html");
}
}