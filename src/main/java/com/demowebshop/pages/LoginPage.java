package com.demowebshop.pages;

import com.demowebshop.utilities.PageUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    PageUtility page = new PageUtility();

    /*** page Constructor ***/
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /*** web elements ***/
    /*private final String _userNameLogin = "//input[@id='Email']";
    private final WebElement userNamelogin = driver.findElement(By.xpath(_userNameLogin));
    private final String _passwordLogin = "//input[@id='Password']";
    private final WebElement passwordLogin = driver.findElement(By.xpath(_passwordLogin));
    private final String _rememberMe = "//input[@id='RememberMe']";
    private final WebElement rememberMe = driver.findElement(By.xpath(_rememberMe));
    private final String _loginButton = "//input[@class='button-1 login-button']";
    private final WebElement loginButton = driver.findElement(By.xpath(_loginButton));*/

    private final String _userNameLogin = "//input[@id='Email']";
    @FindBy(xpath =_userNameLogin )
    private WebElement userNamelogin;

    private final String _passwordLogin ="//input[@id='Password']";
    @FindBy(xpath =_passwordLogin )
    private WebElement passwordLogin;

    private final String _rememberMe ="//input[@id='RememberMe']";
    @FindBy(xpath =_rememberMe )
    private WebElement rememberMe;

    private final String _loginButton = "//input[@class='button-1 login-button']";
    @FindBy(xpath =_loginButton )
    private WebElement loginButton;



    /*** user action methods ***/
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