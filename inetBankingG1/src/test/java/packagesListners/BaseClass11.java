package packagesListners;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass11 {
	public static WebDriver driver;
	public ExtentHtmlReporter htmlreporters;
	public ExtentReports reportss;
	public com.aventstack.extentreports.ExtentTest logger;
	
	
	@BeforeClass
	public void launchBrowser() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
		
		  htmlreporters = new ExtentHtmlReporter(System.getProperty("user.dir")+"./reports/extent11111111111111111.html");
		  //htmlreporters = new ExtentHtmlReporter("./test-output/extent111111111111.html");
		  htmlreporters.config().setEncoding("uft-8");
		  htmlreporters.config().setDocumentTitle("anand First Extent report");
		  htmlreporters.config().setReportName("TESTING EXTENT REPORT");
		  htmlreporters.config().setTheme(Theme.STANDARD);
		 
		
		reportss = new ExtentReports();
		reportss.setSystemInfo("Organization ", "Athena");
		reportss.setSystemInfo("Operatin system", "wINDOWS");
		reportss.attachReporter(htmlreporters);
	}
	@Test
	
	public void test1() {
		
		System.out.println("This is test case 1 pass");
		//Assert.assertEquals(true, true);
		logger = reportss.createTest("successful test");
		logger.log(Status.PASS, "Test case is pass");
	}

	@Test
	public void test2() {
		System.out.println("this is test case 2 fail");
		//Assert.assertEquals(false, true);
		logger = reportss.createTest("failed test cases");
		logger.log(Status.FAIL, "Test case is failed");
		Assert.fail();
		
	}

	@Test
public void test3() {
		System.out.println("This is test case 3 pass");
		//Assert.assertEquals(true, true); 
		logger = reportss.createTest("Skipped test");
		logger.log(Status.SKIP, "Test method is skipper");
		throw new SkipException("Executing the skipp method");
	}
	
	@AfterMethod
	
	public void screenShot_afterMethod(ITestResult result) {
		String Methodname = result.getMethod().getMethodName();
		
		if(result.getStatus() == ITestResult.FAILURE) {
			String exceptions11 = Arrays.toString(result.getThrowable().getStackTrace());
			logger.fail("<details> <summary><b> <font color=red> Exception occured, click here to see details:"+"</font></b></summary>"
			+exceptions11.replace(",", "<br> ")+"</details> \n");
			String path = takescreenshot(result.getMethod().getMethodName());
			try {
				logger.fail("<b> <font color =red>"+ "Failed screenshot"+"</font></b>", 
						MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}catch(IOException e) {
				logger.fail("Test failed can't attach");
			}
			String logtest="<b>Test method" + Methodname +"Failed </b>";
			Markup m = MarkupHelper.createLabel(logtest, ExtentColor.RED);
			logger.log(Status.FAIL, m);
		}else if (result.getStatus() == ITestResult.SUCCESS) {
			String logtest="<b>Test method" + Methodname +"Success </b>";
			Markup m = MarkupHelper.createLabel(logtest, ExtentColor.GREEN);
			logger.log(Status.PASS, m);
		}else if (result.getStatus() == ITestResult.SKIP) {
			String logtest="<b>Test method" + Methodname +"Skipped </b>";
			Markup m = MarkupHelper.createLabel(logtest, ExtentColor.YELLOW);
			logger.log(Status.SKIP, m);
		}
		
		
		
	}
	
	public String takescreenshot(String mathodName) {
		String methodName1 =mathodName;
		String filename = getScreenShotName(methodName1 );
		String directory = System.getProperty("user.dir")+"/ScreenShots/";
		new File(directory).mkdirs();
		String path = directory +filename;
		
		try {
			File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot,new File(path));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	public static String getScreenShotName(String methodName) {
		Date d = new Date();  //FOR TIME BEING COMMENTED IT IN REAL TIME WE HAVE TO USE IT.
		//String d ="123";
		String filename = methodName+"_"+d+".png";
		return filename;
		
		
	}
	
	
	@AfterClass
	
	public void teardown() {
		driver.quit();
		reportss.flush();
	}
}
