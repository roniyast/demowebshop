package com.demowebshop.automationcore;

import com.demowebshop.constants.Constants;
import com.demowebshop.utilities.WaitUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

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
        driver.manage().timeouts().pageLoadTimeout(WaitUtility.PAGE_LOAD_WAIT, TimeUnit.SECONDS);
    }

    @BeforeMethod
    @Parameters("browser")
    public void setup(String browserName) {
        String url= prop.getProperty("url");
        testInitialize(browserName);
        driver.get(url);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
       takeScreenshot(result);
        driver.close();
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
