package com.demowebshop.testscripts;

import com.demowebshop.automationcore.Base;
import com.demowebshop.constants.Constants;
import com.demowebshop.pages.HomePage;
import com.demowebshop.pages.RegisterPage;
import com.demowebshop.pages.UserAccountPage;
import com.demowebshop.utilities.ExcelUtility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class RegisterTest extends Base {

    HomePage home= new HomePage(driver);
    RegisterPage register;
    UserAccountPage user;
    public ExtentReports report;
    static ExtentTest test;
    ExcelUtility excel;

    @Test(priority=2,enabled=true,description ="verification of Registration Title")
    public void verifyUserRegistration() throws IOException {

        List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_REGISTRATION_PAGE);

        register=home.clickOnRegisterMenu();
        register.selectGender(readExcelData.get(0));
        register.enterFirstName(readExcelData.get(1));
        register.enterLastName(readExcelData.get(2));
        register.enterEmail(readExcelData.get(3));
        register.enterPassword(readExcelData.get(4));
        register.enterConfirmPassword(readExcelData.get(5));
        user = register.clickOnRegisterButton();

        String actualUserName="roniyat@gmail.com";
        String expectedUserName=user.verifyUserName();
        Assert.assertEquals(actualUserName,expectedUserName,"ERROR : Login Failed");
    }
}
