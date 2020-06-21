package packagesListners;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.ebanking.TestCases.TestBase;

public class TestListners   implements ITestListener{
	
	
	private static ExtentReports extent= ExtentManagers.create_Instance();
	
	private static ThreadLocal< ExtentTest> extenttest= new ThreadLocal<ExtentTest>(); 

	

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ExtentTest test1 = extent.createTest(result.getClass().getName()+"::"+result.getMethod().getMethodName());
		extenttest.set(test1);
	}


	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String logtest="<b>Test method" + result.getMethod().getMethodName() +"Success </b>";
		Markup m = MarkupHelper.createLabel(logtest, ExtentColor.GREEN);
		extenttest.get().log(Status.PASS, m);
	}

	
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		String Methodname = result.getMethod().getMethodName();
			String exceptions11 = Arrays.toString(result.getThrowable().getStackTrace());
			extenttest.get().fail("<details> <summary><b> <font color=red> Exception occured, click here to see details:"+"</font></b></summary>"
			+exceptions11.replace(",", "<br> ")+"</details> \n");
			WebDriver driver = ((TestBase)result.getInstance()).driver;
			String path = takescreenshot(driver,result.getMethod().getMethodName());
			try {
				extenttest.get().fail("<b> <font color =red>"+ "Failed screenshot"+"</font></b>", 
						MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}catch(IOException e) {
				extenttest.get().fail("Test failed can't attach");
			}
			String logtest="<b>Test method" + Methodname +"Failed </b>";
			Markup m = MarkupHelper.createLabel(logtest, ExtentColor.RED);
			extenttest.get().log(Status.FAIL, m);
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		String logtest="<b>Test method" + result.getMethod().getMethodName() +"Success </b>";
		Markup m = MarkupHelper.createLabel(logtest, ExtentColor.YELLOW);
		extenttest.get().log(Status.SKIP, m);

		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		if(extent!=null) {
		extent.flush();
		}
	}

	
	
	public String takescreenshot(WebDriver driver,String mathodName) {
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
		Date d = new Date(); 
		//String d ="1234";
		String filename = methodName+"_"+d+".png";
		return filename;
	}


	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}


	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	
	

}
