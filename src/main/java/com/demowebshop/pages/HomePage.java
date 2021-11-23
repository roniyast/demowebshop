package com.demowebshop.pages;

import com.demowebshop.automationcore.Base;
import com.demowebshop.constants.Constants;
import com.demowebshop.utilities.ExcelUtility;
import com.demowebshop.utilities.PageUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class HomePage {
    WebDriver driver;
    PageUtility page = new PageUtility();
    ExcelUtility excel = new ExcelUtility();


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    private String _registerMenu = "//a[@class='ico-register']";
    private final WebElement registerMenu = driver.findElement(By.xpath(_registerMenu));

    public String getActualHomePageTitle() {

        return page.getPageTitle(driver);

    }

    public String getExpectedHomePageTitle() throws IOException {
        List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME);
        return readExcelData.get(0);
    }

    public RegisterPage clickOnRegisterMenu() {
        //registerMenu.click();
        return new RegisterPage(driver);
    }
    /*public LoginPage clickOnLoginMenu(){

    }*/
}

