package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.apache.logging.log4j.LogManager; // Log4j
import org.apache.logging.log4j.Logger;     //Log4j
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {
    public Logger logger;
    public static WebDriver driver;
    public Properties p;

    @BeforeClass(groups={"Sanity", "Regression", "Master"})
    @Parameters({"os","browser"})
    public void setUp(String os, String browser) throws IOException {


        FileInputStream file = new FileInputStream("./src//main//resources//config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass()); //log4j2

        //Grid Setup
        if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
			/*
			capabilities.setPlatform(Platform.WIN11); //os
			capabilities.setBrowserName("chrome"); //browser
			*/

            //os
            if(os.equalsIgnoreCase("windows"))
            {
                capabilities.setPlatform(Platform.WIN10);
            }
            else if(os.equalsIgnoreCase("mac"))
            {
                capabilities.setPlatform(Platform.MAC);
            }
            else {
                System.out.println("No matching os");
                return;
            }

            //browser
            switch(browser.toLowerCase())
            {
                case "chrome" : capabilities.setBrowserName("chrome"); break;
                case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
                default : System.out.println("No matching browser"); return;
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

        }

        if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("Invalid Browser ");
                    return;
            }

        }
        //driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.get("https://tutorialsninja.com/demo/");
        driver.get(p.getProperty("appURL1")); //reading data from properties file
        driver.manage().window().maximize();
    }

    //This method generate the random String multiple time depends upon the calling
    public String randomString(){
        String genratedstring = RandomStringUtils.randomAlphabetic(5);
        return genratedstring;
    }
    //This method is return the random number
    public String randomNumber(){
        String genratednumber = RandomStringUtils.randomNumeric(10);
        return genratednumber;
    }

    public String randomAlphaNumeric(){
        String genratedstring = RandomStringUtils.randomAlphabetic(4);
        String genratednumber = RandomStringUtils.randomNumeric(4);
        return (genratedstring+"@"+genratednumber);
    }

    @AfterClass(groups={"Sanity", "Regression", "Master"})
    public void tearDown(){
        driver.quit();
    }

    public String captureScreen(String tname) throws IOException{

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        TakesScreenshot takesScreenShot = (TakesScreenshot) driver;
        File sourceFile = takesScreenShot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
}
