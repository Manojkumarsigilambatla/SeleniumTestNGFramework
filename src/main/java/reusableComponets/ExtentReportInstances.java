package reusableComponets;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportInstances {

	
	public static ExtentReports getReportInstance()
	{
		String path = System.getProperty("user.dir")+"//Reports//index.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path);
		sparkReporter.config().setDocumentTitle("FrameWork report");
		sparkReporter.config().setReportName("Ecommerce Test");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Tester", "Manoj Kumar");
		
		return extent;
	}
}
