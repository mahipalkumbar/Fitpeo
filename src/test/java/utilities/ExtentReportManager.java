package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener, IExecutionListener {

    public ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;  // Ensure the extent object is static
    public static ExtentTest test;

    String repName;

    // Create and configure Extent report when the entire suite starts
    @Override
    public void onExecutionStart() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
        sparkReporter.config().setDocumentTitle("NYX Automation Report");
        sparkReporter.config().setReportName("NYX Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "NYX.today");
        extent.setSystemInfo("Module", "ImageCraft AI");
        extent.setSystemInfo("Sub Module", "ImageCraft Text-to-Image");

        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "Dev");
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName()); // Logs the test method name
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " executed successfully");
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " was skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    // Flush after each test context (optional, in case you want intermediate flushing)
    public void onFinish(ITestContext testContext) {
        // Optionally, you can comment this out to flush only once after the suite
    }

    // Flush the report, send email with the report, and open the HTML file in the browser when the entire suite finishes
    @Override
    public void onExecutionFinish() {
        extent.flush();  // Ensure everything is written before opening

        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI()); // Opens the report in the browser
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Send the email with the attached report
        try {
            sendEmailWithAttachment(pathOfExtentReport);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Function to send email with the report as an attachment
    private void sendEmailWithAttachment(String reportPath) throws Exception {
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(reportPath);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Extent Report");
        attachment.setName("TestReport.html");

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");         // Use Gmail's SMTP server
        email.setSmtpPort(587);                      // Port 587 for TLS
        email.setAuthenticator(new DefaultAuthenticator("nyx.alert@gmail.com", "fkcdbgjywlwnarot"));  // Gmail email and app-specific password
        email.setStartTLSEnabled(true);              // Enable TLS
        email.setFrom("nyx.alert@gmail.com", "NYX Automation Report");
        email.setSubject("Test Suite Execution Report");
        email.setMsg("Please find the attached report for the test suite execution.");
        email.addTo("mahipal.k@nyx.today");          // Receiver's email

        // Attach the report
        email.attach(attachment);

        // Send the email
        email.send();

        System.out.println("Email sent successfully with the report attached.");
    }

}
