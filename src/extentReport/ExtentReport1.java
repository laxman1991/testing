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
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport1 {
	
	ExtentReports report;
	ExtentTest logger;
	WebDriver driver;
	
  @Test
  public void verifyBlogTitle() {
	  
	report=new ExtentReports("D://JarfilesandLibraryanddrivers//ExtentReportFile//extendRunss.html");
	logger=report.startTest("checkTitle");
	System.setProperty("webdriver.chrome.driver","D:\\JarfilesandLibraryanddrivers\\chromedriver_win32\\chromedriver\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	logger.log(LogStatus.INFO, "Browser started ");
	driver.get("http://newtours.demoaut.com");
	logger.log(LogStatus.INFO, "Site Launch successfully");
	String title=driver.getTitle();
	Assert.assertTrue(title.contains("google"));
	logger.log(LogStatus.INFO, "title tracking done"); 
  }
  @AfterTest
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
	  driver.get("D:\\JarfilesandLibraryanddrivers\\ExtentReportFile\\extendRun.html");
	  }
}
