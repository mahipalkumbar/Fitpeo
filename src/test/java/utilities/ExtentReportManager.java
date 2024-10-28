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
    public ExtentSparkReporter fixedNameReporter;  // Add another reporter for the fixed name report
    public static ExtentReports extent;  // Ensure the extent object is static
    public static ExtentTest test;

    String repName;

    // Create and configure Extent report when the entire suite starts
    @Override
    public void onExecutionStart() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        // Reporter for the dynamic name (Test-Report + timestamp)
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
        sparkReporter.config().setDocumentTitle("NYX Automation Report");
        sparkReporter.config().setReportName("NYX Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        // Reporter for the fixed name (Extent_Test_Report.html)
        fixedNameReporter = new ExtentSparkReporter(".\\reports\\Extent_Test_Report.html");
        fixedNameReporter.config().setDocumentTitle("NYX Automation Report");
        fixedNameReporter.config().setReportName("NYX Functional Testing");
        fixedNameReporter.config().setTheme(Theme.DARK);

        // Create a new ExtentReports object and attach both reporters
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter, fixedNameReporter);  // Attach both reporters
        extent.setSystemInfo("Application", "NYX.today");
        extent.setSystemInfo("Module", "ImageCraft AI");
        extent.setSystemInfo("Sub Module", "ImageCraft Text-to-Image");

        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "Dev");
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
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

    public void onFinish(ITestContext testContext) {
        // Optionally flush here if needed per test context
    }

    @Override
    public void onExecutionFinish() {
        extent.flush();  // Ensure everything is written before opening both reports

        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI()); // Open the dynamic report in browser
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Optionally, you can also open the fixed-name report in the browser
        File fixedReport = new File(System.getProperty("user.dir") + "\\reports\\Extent_Test_Report.html");
        try {
            Desktop.getDesktop().browse(fixedReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Send the email with the dynamic report
        try {
            sendEmailWithAttachment(pathOfExtentReport);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendEmailWithAttachment(String reportPath) throws Exception {
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(reportPath);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("Extent Report");
        attachment.setName("TestReport.html");

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("nyx.alert@gmail.com", "fkcdbgjywlwnarot"));
        email.setStartTLSEnabled(true);
        email.setFrom("nyx.alert@gmail.com", "NYX Automation Report");
        email.setSubject("Test Suite Execution Report");
        email.setMsg("Please find the attached report for the test suite execution.");
        email.addTo("mahipal.k@nyx.today");

        email.attach(attachment);

        email.send();

        System.out.println("Email sent successfully with the report attached.");
    }
}
