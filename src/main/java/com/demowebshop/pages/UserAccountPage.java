package com.demowebshop.pages;

import com.demowebshop.utilities.ExcelUtility;
import com.demowebshop.utilities.PageUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserAccountPage {
    WebDriver driver;
    PageUtility page = new PageUtility();
    ExcelUtility excel = new ExcelUtility();

    public UserAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private final String _userName="//div[@class='header-links']//a[@class='account']";
    private WebElement userName = driver.findElement(By.xpath(_userName));

    public String verifyUserName(){
        return page.getElementText(userName);
    }


}
