package com.demowebshop.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demowebshop.automationcore.Base;
import com.demowebshop.constants.Constants;
import com.demowebshop.listener.TestListener;
import com.demowebshop.pages.HomePage;
import com.demowebshop.pages.LoginPage;
import com.demowebshop.pages.UserAccountPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class LoginTest extends Base {

    HomePage home;
    UserAccountPage user;
    LoginPage loginPage;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

    @Test(priority = 3, enabled = true, description = "verification of User Login", groups = "Sanity")
    public void verifyUserLogin() throws IOException {

        extentTest.get().assignCategory("Sanity");

        home = new HomePage(driver);
        loginPage = new LoginPage(driver);
        loginPage = home.clickOnLoginMenu();
        List<String> readExcelData = loginPage.excel.readExcel(Constants.EXCEL_FILE_PATH, Constants.SHEET_NAME_LOGIN_PAGE);
        loginPage.enterUserNameLogin(readExcelData.get(0));
        loginPage.enterPasswordLogin(readExcelData.get(1));
        loginPage.RememberMeLoginCheck(readExcelData.get(2));

        user = loginPage.loginButtonClick();
        extentTest.get().log(Status.PASS, "");

        String actualUserName = user.verifyUserName();
        extentTest.get().log(Status.PASS, "Actual Username generated");
        String expectedUserName = readExcelData.get(0);
        extentTest.get().log(Status.PASS, "Expected Username generated");
        Assert.assertEquals(actualUserName, expectedUserName, "ERROR : Login Failed");
        extentTest.get().log(Status.PASS, "Verify Successful Login test case passed");
    }
}
