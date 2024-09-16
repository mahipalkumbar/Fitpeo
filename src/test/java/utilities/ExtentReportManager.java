package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.*;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;


public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		String timeStamp=new SimpleDateFormat("yyyy.MM.HH.mm.ss").format(new Date());
		repName="Test-Report-"+timeStamp+".html";
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
		sparkReporter.config().setDocumentTitle("NYX Automation Report");
		sparkReporter.config().setReportName("NYX Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "NYX");
		extent.setSystemInfo("Module","ImageCraft AI");
		extent.setSystemInfo("Sub Module","ImageCraft Text-to-Image");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment","Dev");
		String os=testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System",os);
		
		String browser=testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser",browser);
		
		List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	  }
	
	public void onTestSuccess(ITestResult result) {
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+"got successfully executed");
	  }
	
	
	 public void onTestFailure(ITestResult result) {
		 
		 test=extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			
			
			test.log(Status.FAIL, result.getName()+"got failed");
			test.log(Status.INFO, result.getThrowable().getMessage());
			try {
				String imgpath=new BaseClass().captureScreen(result.getName());
				test.addScreenCaptureFromPath(imgpath);
			}catch(IOException e1) {
				e1.printStackTrace();
			}
		  }
	 
	 
	 public void onTestSkipped(ITestResult result) {
		    test=extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			
			
			test.log(Status.SKIP, result.getName()+"got skipped");
			test.log(Status.INFO, result.getThrowable().getMessage());

		 
		  }
	 public void onFinish(ITestContext testContext) {
		 extent.flush();
		 String pathOfEtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		 File extentReport=new File(pathOfEtentReport);
		 
		 try {
			 Desktop.getDesktop().browse(extentReport.toURI());
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		/* try {
			    // Correct URL creation with forward slashes and triple slash for the 'file' protocol
			    String filePath = System.getProperty("user.dir") + "/reports/" + repName;
			    URL url = new File(filePath).toURI().toURL(); // Converts the file path to a proper URL
			    
			    ImageHtmlEmail email = new ImageHtmlEmail();
			    email.setDataSourceResolver(new DataSourceUrlResolver(url));
			    email.setHostName("smtp.gmail.com");
			    email.setSmtpPort(465);
			    email.setAuthenticator(new DefaultAuthenticator("mahipal.k@nyx.today", "Mahi@1234567890"));
			    email.setSSLOnConnect(true);
			    email.setFrom("mahipal.k@nyx.today");
			    email.setSubject("Test Result");
			    email.setMsg("Please find attached report....");
			    email.addTo("mahipal.k@nyx.today"); // Receiver
			    
			    // Attach the file (ensure the correct file path)
			    email.attach(url, "extent report", "please check report...");
			    
			    // Send the email
			    email.send();
			    
			    System.out.println("Email sent successfully with the report.");
			    
			} catch (Exception e) {
			    e.printStackTrace();
			}*/

		 
		  }
}
