package com.demowebshop.pages;

import com.demowebshop.utilities.PageUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;
    PageUtility page = new PageUtility();

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final String _userNameLogin = "//input[@id='Email']";
    private final WebElement userNamelogin = driver.findElement(By.xpath(_userNameLogin));
    private final String _passwordLogin = "//input[@id='Password']";
    private final WebElement passwordLogin = driver.findElement(By.xpath(_passwordLogin));
    private final String _rememberMe = "//input[@id='RememberMe']";
    private final WebElement rememberMe = driver.findElement(By.xpath(_rememberMe));
    private final String _loginButton = "//input[@class='button-1 login-button']";
    private final WebElement loginButton = driver.findElement(By.xpath(_loginButton));

    public void enterUserNameLogin(String userNameToEnter){
        page.enterText(userNamelogin,userNameToEnter);
    }
    public void  enterPasswordLogin(String passwordToEnter){
        page.enterText(passwordLogin,passwordToEnter);
    }
    public void  RememberMeLoginCheck(String rememberMeToCheck){
        if(rememberMeToCheck.equals("TRUE")) {
            page.clickOnElement(rememberMe);
        }
    }
    public UserAccountPage loginButtonClick(){
        page.clickOnElement(loginButton);
        return new UserAccountPage(driver);
    }

}