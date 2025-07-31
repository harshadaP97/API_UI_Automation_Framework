package com.harshada.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;

    // ====== Locators ======
    @FindBy(xpath = "//h1[text()='Logged In Successfully']")
    WebElement loginSuccessMsg;

    @FindBy(xpath = "//a[text()='Log out']")
    WebElement logoutButton;

    // ====== Constructor ======
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ====== Actions ======

    public String validateHomePage() {
        return loginSuccessMsg.getText();
    }

    public LoginPage logoutUser() {
        logoutButton.click();
        return new LoginPage(driver);
    }
}
