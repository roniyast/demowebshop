package com.demowebshop.automationcore;

import com.demowebshop.constants.Constants;
import com.demowebshop.utilities.EmailUtility;
import com.demowebshop.utilities.WaitUtility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Base {
    public WebDriver driver;
    FileInputStream file;
    public Properties prop;
    public ExtentReports report;
    static ExtentTest test;
    public Base()   {
        try {
            file = new FileInputStream(System.getProperty("user.dir")+ Constants.CONFIG_FILE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prop=new Properties();
        try {
            prop.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Deprecated
    public void testInitialize(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        //driver.manage().timeouts().pageLoadTimeout(WaitUtility.PAGE_LOAD_WAIT, TimeUnit.SECONDS);
        test.log(LogStatus.PASS, "Successfully initialized test");
    }

    @BeforeTest
    public void errorLogging(){
        report = new ExtentReports(System.getProperty("user.dir")+"//test-output//Extent.html",true);
        test = report.startTest("Demo Web Shop");
    }

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browserName) {
        String url= prop.getProperty("url");
        testInitialize(browserName);
        driver.get(url);
        test.log(LogStatus.PASS, "Successfully Navigated to the url ");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
       takeScreenshot(result);
        test.log(LogStatus.PASS, "Successfully captured screen shot ");
        driver.close();
    }
    @AfterSuite
    public void sendingEmail(){
       EmailUtility.sendEmail(System.getProperty("user.dir")+"//test-output//","Extent.html", prop.getProperty("to_email"));
       test.log(LogStatus.PASS, "Successfully triggered Email ");
    }
    public void takeScreenshot(ITestResult result) throws IOException {
        if(ITestResult.FAILURE == result.getStatus()){

            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            Date date = new Date();
            String dateFormatted = new SimpleDateFormat("dd-MM-yyyy").format(date);
            String FileName = result.getName()+"_"+dateFormatted;
            FileUtils.copyFile(screenshot,new File("./Screenshots/"+FileName+".png"));
        }

    }

}
