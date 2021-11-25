package com.demowebshop.pages;

import com.demowebshop.automationcore.Base;
import com.demowebshop.constants.Constants;
import com.demowebshop.utilities.ExcelUtility;
import com.demowebshop.utilities.PageUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class HomePage {
    WebDriver driver;
    PageUtility page = new PageUtility();
    ExcelUtility excel = new ExcelUtility();

    /*** page Constructor ***/
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /*** web elements ***/
    /*private String _registerMenu = "//a[@class='ico-register']";
    private final WebElement registerMenu = driver.findElement(By.xpath(_registerMenu));
    private String _loginMenu = "//a[@class='ico-login']";
    private final WebElement loginMenu = driver.findElement(By.xpath(_loginMenu));*/

    private final String _registerMenu ="//a[@class='ico-register']";
    @FindBy(xpath =_registerMenu)
    private WebElement registerMenu;

    private final String _loginMenu ="//a[@class='ico-login']";
    @FindBy(xpath =_loginMenu)
    private WebElement loginMenu;

    /*** user action methods ***/
    public String getActualHomePageTitle() {
        return page.getPageTitle(driver);
    }

    public String getExpectedHomePageTitle() throws IOException {
        List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_HOME_PAGE);
        return readExcelData.get(0);
    }

    public RegisterPage clickOnRegisterMenu() throws IOException {
        page.clickOnElement(registerMenu);
        return new RegisterPage(driver);
    }
    public LoginPage clickOnLoginMenu(){
        System.out.println(driver);
        page.clickOnElement(loginMenu);
        return new LoginPage(driver);
    }
}

