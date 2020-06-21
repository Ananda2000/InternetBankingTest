package packagesListners;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManagers {
	
	private static ExtentReports reportss;
 
		public static ExtentReports create_Instance() {
			String filename = getScreenShotName1();
			String directory = System.getProperty("user.dir")+"/reports/";
			new File(directory).mkdirs();
			String path = directory+filename;
			ExtentHtmlReporter htmlreporters = new ExtentHtmlReporter(path);

			 // htmlreporters = new ExtentHtmlReporter(System.getProperty("user.dir")+"./reports/extent11111111111111111344.html");  
			  //htmlreporters = new ExtentHtmlReporter("./test-output/extent111111111111.html");
			  htmlreporters.config().setEncoding("uft-8");
			  htmlreporters.config().setDocumentTitle("anand First Extent report");
			  htmlreporters.config().setReportName("TESTING EXTENT REPORT");
			  htmlreporters.config().setTheme(Theme.STANDARD);
			 
			
			reportss = new ExtentReports();
			reportss.setSystemInfo("Organization ", "Athena");
			reportss.setSystemInfo("Operatin system", "wINDOWS");
			reportss.attachReporter(htmlreporters);
			return reportss;
		
		}
		
		public static String getScreenShotName1() {
			Date d = new Date();  //FOR TIME BEING COMMENTED IT IN REAL TIME WE HAVE TO USE IT.
			//String d ="1234";
			String filename = "Automation_report"+"_"+d+".html";
			return filename;
			
			
		}
}
