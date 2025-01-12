package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportUtility  implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public void onStart(ITestContext testContext) {
		/*
		SimpleDateFormat df = new SimpleDateFormat("yyyy:MM:dd.HH.mm.ss");
		Date dt = new Date();
		String currentdateTimeStampString = df.format(dt);
		*/

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //store the time stamp and dynamically genrated.

        repName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); //specify location of the

        sparkReporter.config().setDocumentTitle("TutorialsNinja Automation Report"); //Title of the report
        sparkReporter.config().setReportName("Tutorials Functional Testing "); // name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "TutorialsNinja");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testContext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System ", os);

        String browser = testContext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser);

        List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
        if (!includeGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includeGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); // to display groups in report
        test.log(Status.PASS, result.getName() + " got successfully executed");
    }


    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());

        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();

        String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
        File extentReport = new File(pathOfExtentReport);

        try {
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }

		/*
		//This will automatically send an email
		try {
			URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\" + repName);

		//Create the email message
	    ImageHtmlEMail emai = new ImageHtmlEMail();
		email.setDataSourceResolver(new DataSourceUrlResolver(url));
		email.setHostName("smpt.google.com");
		email.setSmptPort(465);
		email.setAuthenticator(new DefaultAuthenticator("email@gmail.com", "password"));
		email.setSSLOnConnect(true);
		email.setFrom("email@gmail.com"); // sender
		email.setSubject("Test Results");
		email.setMsg("Please find Attached Report... ");
		email.addTo("vikash.busyqa@gmail.com"); // Receiver
		email.attach(url, "extent report", "please check report... ");
		email.send(); //send the email
		}catch(Exception e) {
			e.printStackTrace();
		}
		*/

    }
}
