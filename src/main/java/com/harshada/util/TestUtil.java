package com.harshada.util;

import java.io.File;
import com.aventstack.extentreports.ExtentTest;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.harshada.base.TestBase;

public class TestUtil {

    // Global timeout values for driver
    public static long page_load_timeout = 20;
    public static long implicit_wait = 10;

    // Takes a screenshot and returns the path (used for reporting/logging)
    public static String takeScreenshotAtEndOfTest() throws IOException {
        WebDriver driver = TestBase.driver;
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        FileUtils.copyFile(scrFile, new File(path));
        return path;
    }

    // Optional manual screenshot method
    public static void takeScreenshot() {
        try {
            WebDriver driver = TestBase.driver;
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String path = System.getProperty("user.dir") + "/screenshots/manual_" + System.currentTimeMillis() + ".png";
            FileUtils.copyFile(srcFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //for logging setup
    public static void logStep(ExtentTest logger, String message) {
        if (logger != null) {
            logger.info(message);
        }
    }
}
