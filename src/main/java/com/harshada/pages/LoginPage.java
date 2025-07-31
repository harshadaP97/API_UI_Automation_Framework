package com.harshada.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    // ====== Locators ======
    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "submit")
    WebElement loginButton;

    @FindBy(id = "error")
    WebElement errorMessage;

    @FindBy(xpath = "//h2[text()='Test login']")
    WebElement loginPageText;

    // ====== Constructor ======
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ====== Actions ======

    public HomePage login(String username, String password) {
        usernameField.clear();
        usernameField.sendKeys(username.trim());
        passwordField.clear();
        passwordField.sendKeys(password.trim());
        loginButton.click();
        return new HomePage(driver);
    }

    public String validateLoginPage() {
        return loginPageText.getText();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
