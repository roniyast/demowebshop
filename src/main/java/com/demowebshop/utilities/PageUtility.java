package com.demowebshop.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageUtility {
    public void clickOnElement(WebElement element){
        element.click();
    }
    public void enterText(WebElement element,String value){
        element.sendKeys(value);
    }
    public String getAttributeValue(WebElement element, String attribute){
        return  element.getAttribute(attribute);
    }
    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }
}
