package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private ExtentTest test;
    private String repName;

    @Override
    public void onExecutionStart() {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";

        // Ensure the reports directory exists
        String reportsDirPath = "." + File.separator + "reports";
        File reportsDir = new File(reportsDirPath);
        if (!reportsDir.exists()) {
            boolean dirCreated = reportsDir.mkdirs();
            if (dirCreated) {
                System.out.println("Created reports directory: " + reportsDirPath);
            } else {
                System.out.println("Failed to create reports directory: " + reportsDirPath);
            }
        }

        // Initialize ExtentSparkReporter with dynamic report name
        sparkReporter = new ExtentSparkReporter(reportsDirPath + File.separator + repName);
        sparkReporter.config().setDocumentTitle("Fitpeo Automation Report");
        sparkReporter.config().setReportName("Fitpeo Assignment Test");
        sparkReporter.config().setTheme(Theme.DARK);

        // Initialize fixed report with fixed name
        String reportPath = reportsDirPath + File.separator + "Extent_Test_Report.html";
        fixedNameReporter = new ExtentSparkReporter(reportPath);
        fixedNameReporter.config().setDocumentTitle("Fitpeo Automation Report");
        fixedNameReporter.config().setReportName("Fitpeo Assignment Test");
        fixedNameReporter.config().setTheme(Theme.DARK);

        // Initialize ExtentReports and attach both reporters
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter, fixedNameReporter);
        extent.setSystemInfo("Application", "Fitpeo");
        extent.setSystemInfo("Module", "Revenue Calculator");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, result.getName() + " executed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, result.getName() + " failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            // Capture screenshot and get the relative path
            String imgPath = new BaseClass().captureScreen(result.getName());

            // Add the screenshot to the Extent report using the relative path
            if (imgPath != null) {
                test.addScreenCaptureFromPath(imgPath); // Image added in Extent Report
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, result.getName() + " was skipped");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        // Do nothing here; flush in onExecutionFinish
    }

    @Override
    public void onExecutionFinish() {
        try {
            // Flush the Extent Reports instance to save the generated report
            extent.flush();

            // Define the paths for the dynamic and fixed reports
            String pathOfDynamicReport = System.getProperty("user.dir") + File.separator + "reports" + File.separator + repName;
            String pathOfFixedReport = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "Extent_Test_Report.html";

            // Open the dynamic report in the default browser
            openReportInBrowser(pathOfDynamicReport);
            
            // Optionally, you can open the fixed report as well (if required)
            // openReportInBrowser(pathOfFixedReport);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to open the report in the default browser
    private void openReportInBrowser(String reportPath) {
        File htmlFile = new File(reportPath);
        if (htmlFile.exists() && Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(htmlFile.toURI());
                System.out.println("Opened report: " + htmlFile.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Error opening the report: " + e.getMessage());
            }
        } else {
            System.out.println("Report file does not exist or Desktop is not supported.");
        }
    }
}
