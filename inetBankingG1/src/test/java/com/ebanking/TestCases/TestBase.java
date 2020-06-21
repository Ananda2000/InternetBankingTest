	package com.ebanking.TestCases;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.beust.jcommander.Parameter;
import com.ebanking.Utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	ReadConfig readconfig = new ReadConfig();
	public String url =readconfig.baseUrl();
	public String username1 =readconfig.username();
	public  String password1 = readconfig.pwd();
	public String userName2 = readconfig.userName1();
	public String password2 = readconfig.passWord2();
	public  WebDriver driver;
	//public static Logger loggers;
//	public ExtentHtmlReporter htmlreporters;
//	public ExtentReports reportss;
//	public com.aventstack.extentreports.ExtentTest logger;
	
//	@BeforeClass
//	
//	public void beforeclass() {
//
//		  htmlreporters = new ExtentHtmlReporter(System.getProperty("user.dir")+"./reports/extent11111111.html");
//		  //htmlreporters = new ExtentHtmlReporter("./test-output/extent111111111111.html");
//		  htmlreporters.config().setEncoding("uft-8");
//		  htmlreporters.config().setDocumentTitle("anand First Extent report");
//		  htmlreporters.config().setReportName("TESTING EXTENT REPORT");
//		  htmlreporters.config().setTheme(Theme.STANDARD);
//		 
//		
//		reportss = new ExtentReports();
//		reportss.setSystemInfo("Organization ", "Athena");
//		reportss.setSystemInfo("Operatin system", "wINDOWS");
//		reportss.attachReporter(htmlreporters);
//	
//	}
	
	
	
	
	@Parameters("browser")
	@BeforeMethod
	
	public void setup(String br) {
		//String br="chrome";
		
		//loggers = Logger.getLogger("ebanking");
		//PropertyConfigurator.configure("Log4j.properties");
		if(br.equals("chrome")) {
			System.out.println("launching the chrome browser.*********************************************");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		}
		if(br.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		if(br.equals("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		driver.get(url);
		
		

	
	}
	
	@AfterMethod
	public void teardown() {
		System.out.println("Driver is quiting***********************************************");
		
		driver.quit();
		
	}
	
	/*@AfterClass
	public void flusshing() {
		reportss.flush();
	}*/
}
