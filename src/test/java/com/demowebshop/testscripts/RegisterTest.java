package com.demowebshop.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demowebshop.automationcore.Base;
import com.demowebshop.constants.Constants;
import com.demowebshop.listener.TestListener;
import com.demowebshop.pages.HomePage;
import com.demowebshop.pages.RegisterPage;
import com.demowebshop.pages.UserAccountPage;
import com.demowebshop.utilities.ExcelUtility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class RegisterTest extends Base {

    RegisterPage register;
    UserAccountPage user;
    ExcelUtility excel;
    HomePage home;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority=2,enabled=true,description ="verification of Registration Title",groups = { "Sanity" })
    public void verifyUserRegistration() throws IOException {

        extentTest.get().assignCategory("Sanity");

        home= new HomePage(driver);
        user = new UserAccountPage(driver);
        register = new RegisterPage(driver);
        excel=new ExcelUtility();

        List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_REGISTRATION_PAGE);

        register=home.clickOnRegisterMenu();
        String email = register.randomStringGeneration();
        register.selectGender(readExcelData.get(0));
        register.enterFirstName(readExcelData.get(1));
        register.enterLastName(readExcelData.get(2));
        register.enterEmail(email);
        register.enterPassword(readExcelData.get(4));
        register.enterConfirmPassword(readExcelData.get(5));
        user = register.clickOnRegisterButton();



        String actualUserName=user.verifyUserName();
        extentTest.get().log(Status.PASS, "Actual Username generated");
        String expectedUserName=email;
        extentTest.get().log(Status.PASS, "Expected Username generated");
        Assert.assertEquals(actualUserName,expectedUserName,"ERROR : Login Failed");
        extentTest.get().log(Status.PASS, "verify Successful Registration test case passed");

    }


}
