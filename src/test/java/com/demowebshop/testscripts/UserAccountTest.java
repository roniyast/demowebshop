package com.demowebshop.testscripts;

import com.aventstack.extentreports.ExtentTest;
import com.demowebshop.automationcore.Base;
import com.demowebshop.listener.TestListener;
import com.demowebshop.pages.HomePage;
import com.demowebshop.pages.UserAccountPage;

public class UserAccountTest extends Base {
    HomePage home;
    UserAccountPage userAccount;

    ThreadLocal<ExtentTest> extentTest = TestListener.getTestInstance();

}
