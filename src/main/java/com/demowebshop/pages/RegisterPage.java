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
import java.util.Random;

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
    public String randomStringGeneration(){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 5;
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        String randomStringEmail = sb.toString()+"@gmail.com";
        return randomStringEmail;
    }

}

