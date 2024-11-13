package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    private static ExtentSparkReporter sparkReporter;
    private static ExtentSparkReporter fixedNameReporter;
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<>();

    private String repName;

    @Override
    public void onExecutionStart() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        // Dynamic and Fixed Reporter
        sparkReporter = new ExtentSparkReporter("." + File.separator + "reports" + File.separator + repName);
        sparkReporter.config().setDocumentTitle("NYX Automation Report");
        sparkReporter.config().setReportName("NYX Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        String reportPath = "." + File.separator + "reports" + File.separator + "Extent_Test_Report.html";
        fixedNameReporter = new ExtentSparkReporter(reportPath);
        fixedNameReporter.config().setDocumentTitle("NYX Automation Report");
        fixedNameReporter.config().setReportName("NYX Functional Testing");
        fixedNameReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter, fixedNameReporter);
        extent.setSystemInfo("Application", "NYX.today");
        extent.setSystemInfo("Module", "ImageCraft AI");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
    }

    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        threadLocalTest.set(test);
    }

    public void onTestSuccess(ITestResult result) {
        threadLocalTest.get().log(Status.PASS, result.getName() + " executed successfully");
    }

    public void onTestFailure(ITestResult result) {
        ExtentTest test = threadLocalTest.get();
        test.log(Status.FAIL, result.getName() + " failed");
        test.log(Status.INFO, result.getThrowable().getMessage());
        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        threadLocalTest.get().log(Status.SKIP, result.getName() + " was skipped");
    }

    public void onFinish(ITestContext testContext) {
        // No flush here, flush at the end of execution
    }

    @Override
    public void onExecutionFinish() {
        try {
            extent.flush();

            String pathOfDynamicReport = System.getProperty("user.dir") + File.separator + "reports" + File.separator + repName;
            String pathOfFixedReport = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "Extent_Test_Report.html";

            sendEmailWithAttachment(pathOfDynamicReport);
            sendEmailWithAttachment(pathOfFixedReport);

            File htmlFile = new File(pathOfDynamicReport);
            if (htmlFile.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } else {
                System.out.println("Report file does not exist or Desktop is not supported.");
            }
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
