package com.demowebshop.pages;

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

public class RegisterPage {

    WebDriver driver;
    PageUtility page = new PageUtility();
    ExcelUtility excel = new ExcelUtility();

    /*** page Constructor ***/
    public RegisterPage(WebDriver driver) throws IOException {
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    /*** web elements ***/
   /* private String _gender = "//div[@class='gender']//label";
    private List<WebElement> genders = driver.findElements(By.xpath(_gender));
    private String _firstName="FirstName";
    private WebElement firstName = driver.findElement(By.id(_firstName));
    private String _lastName="LastName";
    private WebElement lastName = driver.findElement(By.id(_lastName));
    private String _email="Email";
    private WebElement email = driver.findElement(By.id(_email));
    private String _password="Password";
    private WebElement password = driver.findElement(By.id(_password));
    private String _confirmPassword="ConfirmPassword";
    private WebElement confirmPassword = driver.findElement(By.id(_confirmPassword));
    private String _registerButton="register-button";
    private WebElement registerButton = driver.findElement(By.id(registerButton));*/

    private final String _gender = "//div[@class='gender']//label";
    @FindBy(xpath =_gender )
    private List <WebElement> genders;

    private final String _firstName ="FirstName";
    @FindBy(id =_firstName )
    private WebElement firstName;

    private final String _lastName ="LastName";
    @FindBy(id =_lastName )
    private WebElement lastName;

    private final String _email ="Email";
    @FindBy(id =_email )
    private WebElement email;

    private final String _password ="Password";
    @FindBy(id =_password )
    private WebElement password;

    private final String _confirmPassword ="ConfirmPassword";
    @FindBy(id =_confirmPassword )
    private WebElement confirmPassword;

    private final String _registerButton ="register-button";
    @FindBy(id =_registerButton )
    private WebElement registerButton;



    /*** user action methods ***/
    public void selectGender(String genderToSelect) {

        for (int i = 0; i < genders.size(); i++) {
            String value = page.getElementText(genders.get(i));
            if (value.equals(genderToSelect)) {
                page.clickOnElement(genders.get(i));
            }
        }
    }

    public void enterFirstName(String firstNameToEnter){
        page.enterText(firstName,firstNameToEnter);
    }

    public void enterLastName(String lastNameToEnter){
        page.enterText(lastName,lastNameToEnter);
    }

    public void enterEmail(String emailToEnter){
        page.enterText(email,emailToEnter);
    }
    public void enterPassword(String passwordToEnter){
        page.enterText(password,passwordToEnter);
    }
    public void enterConfirmPassword(String confirmPasswordToEnter){
        page.enterText(confirmPassword,confirmPasswordToEnter);
    }
    public UserAccountPage clickOnRegisterButton(){
        page.clickOnElement(registerButton);
        return new UserAccountPage(driver);
    }
}
