package com.demowebshop.pages;

import com.demowebshop.utilities.ExcelUtility;
import com.demowebshop.utilities.PageUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RegisterPage {

    WebDriver driver;
    PageUtility page = new PageUtility();
    ExcelUtility excel = new ExcelUtility();

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    private String gender = "//div[@class='gender']//label";
    private List<WebElement> genders = driver.findElements(By.xpath(gender));
    private String firstNameId="FirstName";
    private WebElement firstName = driver.findElement(By.id(firstNameId));
    private String lastNameId="LastName";
    private WebElement lastName = driver.findElement(By.id(lastNameId));
    private String emailId="Email";
    private WebElement email = driver.findElement(By.id(emailId));
    private String passwordId="Password";
    private WebElement password = driver.findElement(By.id(passwordId));
    private String confirmPasswordId="ConfirmPassword";
    private WebElement confirmPassword = driver.findElement(By.id(confirmPasswordId));
    private String registerButtonId="register-button";
    private WebElement registerButton = driver.findElement(By.id(registerButtonId));

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
