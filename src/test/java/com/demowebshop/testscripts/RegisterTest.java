package com.demowebshop.testscripts;

import com.demowebshop.automationcore.Base;
import com.demowebshop.pages.HomePage;
import com.demowebshop.pages.RegisterPage;
import com.demowebshop.pages.UserAccountPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends Base {

    HomePage home= new HomePage(driver);
    RegisterPage register;
    UserAccountPage user;
    public ExtentReports report;
    static ExtentTest test;

    @Test(priority=2,enabled=true,description ="verification of Registration Title")
    public void verifyUserRegistration(){
        register=home.clickOnRegisterMenu();
        register.selectGender("Female");
        register.enterFirstName("Geetha");
        register.enterLastName("James");
        register.enterEmail("geetha@gmail.com");
        register.enterPassword("123456");
        register.enterConfirmPassword("123456");
        user = register.clickOnRegisterButton();

        String actualUserName="roniyat@gmail.com";
        String expectedUserName=user.verifyUserName();
        Assert.assertEquals(actualUserName,expectedUserName,"ERROR : Login Failed");
    }
}
