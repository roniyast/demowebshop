package com.demowebshop.pages;

import com.demowebshop.utilities.TestHelperUtility;
import com.demowebshop.utilities.WaitUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserAccountPage extends TestHelperUtility {
    WebDriver driver;

    /*** page Constructor ***/
    public UserAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*** web elements ***/
    private final String _userName = "//div[@class='header-links']//a[@class='account']";
    @FindBy(xpath = _userName)
    private WebElement userName;

    /*** user action methods ***/
    public String verifyUserName() {
        //wait.waitForVisibilityOfElement(driver, WaitUtility.LocatorType.Xpath,_userName);
        return page.getElementText(userName);
    }

}
