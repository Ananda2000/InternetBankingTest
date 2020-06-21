package packagesListners;

import java.util.Date;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ebanking.TestCases.TestBase;

public class ListnersTesting extends TestBase implements ITestListener{
	public ExtentHtmlReporter htmlreporters;
	public ExtentReports reportss;
	public com.aventstack.extentreports.ExtentTest loggerss;
	

	public void onTestStart(ITestResult result) {
			}
	

	public void onStart(ITestContext context) {
		Date d = new Date();
		String filename1 = d.toString().replace(" ", "_");
		String filename = filename1.replace(":", "_");
		//SimpleDateFormat s = new SimpleDateFormat("MMdd");
		//String filename =s.format(d);
		//System.out.println("****************************************"+filename);
		htmlreporters = new ExtentHtmlReporter(System.getProperty("user.dir")+"./reports/"+filename+".html");
		reportss = new ExtentReports();
		htmlreporters.config().setEncoding("uft-8");
		  htmlreporters.config().setDocumentTitle("anand First Extent report");
		  htmlreporters.config().setReportName("TESTING EXTENT REPORT");
		  htmlreporters.config().setTheme(Theme.STANDARD);
		 
		
		reportss = new ExtentReports();
		reportss.setSystemInfo("Organization ", "Athena");
		reportss.setSystemInfo("Operatin system", "wINDOWS");
		reportss.attachReporter(htmlreporters);
	}
	  
		public void onTestFailure(ITestResult result) {
//		 System.out.println("*********************************************************************123");
//		  String logtest="<b>Test method" + result.getMethod().getMethodName() +"Failed </b>";
//		  Markup m = MarkupHelper.createLabel(logtest,ExtentColor.RED); 
//		   loggerss.log(Status.FAIL, m);
//		  
			
			String exceptions11 =  Arrays.toString(result.getThrowable().getStackTrace());
				String classname = result.getClass().getName();
				String methodname = result.getMethod().getMethodName();
			 loggerss = reportss.createTest(result.getMethod().getMethodName());
			 loggerss.log(Status.FAIL,"CLASS NAME = "+classname +" METHOD NAME = "+ methodname+"  is failed ****************** THE EXCEPTION IS"+exceptions11.replace(",", "<br>"));
			
			 
			 
//			String exceptions11 =  Arrays.toString(result.getThrowable().getStackTrace());
//			logger.fail("<details> <summary><b> <font color=red> Exception occured, click here to see details:"
//		  +"</font></b></summary>" +exceptions11.replace(",",  "<br> ")+"</details> \n");
//			System.out.println( "*********************************************************************");
//		  System.out.println(exceptions11);
		  
		
		 }

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		/*
		 * String logtest="<b>Test method" + result.getMethod().getMethodName()
		 * +"Skip </b>"; Markup m = MarkupHelper.createLabel(logtest,
		 * ExtentColor.YELLOW); logger.log(Status.SKIP, m);
		 */
		System.out.println(result.getMethod().getMethodName() + "Test case is Skipped");
		String classname = result.getClass().getName();
		String methodname = result.getMethod().getMethodName();
	 loggerss = reportss.createTest(result.getMethod().getMethodName());
	 loggerss.log(Status.SKIP, "CLASS NAME = "+classname+" METHOD NAME = "+ methodname+"  IS SKIPPED");
	
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
//		  String logtest="<b>Test method" + result.getMethod().getMethodName()+"Success </b>"; 
//		  System.out.println(logtest);
//		  Markup m = MarkupHelper.createLabel(logtest,ExtentColor.GREEN);
//		  logger.log(Status.PASS,m);
//		  
		
		String screenshotpath =System.getProperty("user.dir")+"\\Screenshots\\test2_123.png"; 
		System.out.println(result.getMethod().getMethodName() + "Test case is passed");
		String classname = result.getClass().getName();
			String methodname = result.getMethod().getMethodName();
		 loggerss = reportss.createTest(result.getMethod().getMethodName());
		 loggerss.log(Status.PASS,"CLASS NAME = "+classname+" METHOD NAME = "+ methodname+  "IS SUCCESSFUL");
		 
		 try {
			loggerss.pass("screen shot is below"+ loggerss.addScreenCaptureFromPath(screenshotpath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	}

	
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	
		reportss.flush();		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}
	
	public String taking_Screenshot(ITestResult result) {
		
			//using ITestResult.FAILURE is equals to result.getStatus then it enter into if condition
				if(ITestResult.FAILURE==result.getStatus()){
					try{
						// To create reference of TakesScreenshot
						TakesScreenshot screenshot=(TakesScreenshot)driver;
						// Call method to capture screenshot
						File src=screenshot.getScreenshotAs(OutputType.FILE);
						// Copy files to specific location 
						// result.getName() will return name of test case so that screenshot name will be same as test case name
						//FileUtils.copyFile(src, new File("D:\\"+result.getName()+".png"));
						//htmlreporters = new ExtentHtmlReporter(System.getProperty("user.dir")+"./reports/"+filename+".html");
						String screeshotname=result.getMethod().getMethodName();
						File dest = new File(System.getProperty("user.dir")+"./ScreenShots/"+screeshotname+".png");
						FileUtils.copyFile(src,dest );
						System.out.println("Successfully captured a screenshot");
						//return screeshotname;
					}catch (Exception e){
						System.out.println("Exception while taking screenshot "+e.getMessage());
					} 
				
			}
				return password1;
				
			}
	}
	// To create reference of TakesScreenshot
				// TakesScreenshot screenshot=(TakesScreenshot)driver;
				
