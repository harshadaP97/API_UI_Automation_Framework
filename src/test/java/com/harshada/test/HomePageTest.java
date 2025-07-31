package com.harshada.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.harshada.base.TestBase;
import com.harshada.pages.HomePage;
import com.harshada.pages.LoginPage;
import com.harshada.util.TestUtil;

public class HomePageTest extends TestBase {

    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void setup() {
        loginPage = new LoginPage(driver);
        homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
    }

    @Test
    public void validateLogout() {
		TestUtil.logStep(logger, "Validate User logout");
        loginPage = homePage.logoutUser();
        String text = loginPage.validateLoginPage();
        Assert.assertEquals(text, "Test login", "Logout failed or redirected to incorrect page.");
		logger.pass("User logout successfully");

    }
}
