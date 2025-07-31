package com.harshada.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.harshada.base.TestBase;
import com.harshada.pages.HomePage;
import com.harshada.pages.LoginPage;
import com.harshada.util.TestUtil;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;

	@BeforeMethod
	public void setup() {
		loginPage = new LoginPage(driver);
	}

	@Test
	public void validateLoginWithValidCredentials() {
		TestUtil.logStep(logger, "Validate User login with valid credentials");
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		String message = homePage.validateHomePage();
		Assert.assertEquals(message, "Logged In Successfully", "Login success message mismatch.");
		logger.pass("Login successful");
	}

	@Test
	public void validateLoginWithInvalidUsername() {
		TestUtil.logStep(logger, "Validate User login with invalid username");

		loginPage.login(prop.getProperty("incorrectUname"), prop.getProperty("password"));
		String error = loginPage.getErrorMessage();
		Assert.assertEquals(error, "Your username is invalid!", "Expected invalid username error not displayed.");
		logger.pass("Correct error message display");

	}

	@Test
	public void validateLoginWithInvalidPassword() {
		TestUtil.logStep(logger, "Validate User login with invalid password");

		loginPage.login(prop.getProperty("username"), prop.getProperty("incorrectPassword"));
		String error = loginPage.getErrorMessage();
		Assert.assertEquals(error, "Your password is invalid!", "Expected invalid password error not displayed.");
		logger.pass("Correct error message display");

	}
}
