package com.demowebshop.testscripts;

import com.demowebshop.automationcore.Base;
import com.demowebshop.constants.Constants;
import com.demowebshop.pages.HomePage;
import com.demowebshop.pages.LoginPage;
import com.demowebshop.pages.RegisterPage;
import com.demowebshop.pages.UserAccountPage;
import com.demowebshop.utilities.ExcelUtility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class LoginTest extends Base {

    HomePage home;
    UserAccountPage user;

    ExcelUtility excel;
    LoginPage loginPage;


    @Test(priority = 5, enabled = true, description = "verification of User Login")
    public void verifyUserLogin() throws IOException {

        home = new HomePage(driver);
        loginPage = new LoginPage(driver);
        excel=new ExcelUtility();
        loginPage = home.clickOnLoginMenu();
        List<String> readExcelData = excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_LOGIN_PAGE);
        loginPage.enterUserNameLogin(readExcelData.get(0));
        loginPage.enterPasswordLogin(readExcelData.get(1));
        loginPage.RememberMeLoginCheck(readExcelData.get(2));

        user= loginPage.loginButtonClick();
        test.log(LogStatus.PASS, "Successfully Logged in");

        String actualUserName = readExcelData.get(0);
        String expectedUserName = user.verifyUserName();
        Assert.assertEquals(actualUserName, expectedUserName, "ERROR : Login Failed");
        test.log(LogStatus.PASS, "Successfully Asserted");
    }
}
