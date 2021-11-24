package com.demowebshop.pages;

import com.demowebshop.utilities.ExcelUtility;
import com.demowebshop.utilities.PageUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserAccountPage {
    WebDriver driver;
    PageUtility page = new PageUtility();
    ExcelUtility excel = new ExcelUtility();

    /*** page Constructor ***/
    public UserAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    /*** web elements ***/
    /*private final String _userName="//div[@class='header-links']//a[@class='account']";
    private WebElement userName = driver.findElement(By.xpath(_userName));*/

    private final String _userName="//div[@class='header-links']//a[@class='account']";
    @FindBy(xpath = _userName)
    private WebElement userName;

    /*** user action methods ***/
    public String verifyUserName(){
        return page.getElementText(userName);
    }

}
