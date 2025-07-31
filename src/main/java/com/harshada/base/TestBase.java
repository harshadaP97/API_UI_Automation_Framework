package com.harshada.base;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.harshada.api.base.BaseAPITest;
import com.harshada.util.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static Properties prop;

    public static ExtentReports extent;
    public static ExtentTest logger;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter(
                System.getProperty("user.dir") + "/test-output/Reports/UnifiedReport_" + System.currentTimeMillis() + ".html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }

    @BeforeClass
    public void loadConfig() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "/src/main/java/com/harshada/config/config.properties"
            );
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void initializeBrowser(Method method) {
        logger = extent.createTest(method.getName());

        if (this instanceof BaseAPITest) {
            logger.info("Skipping browser launch for API test");
            return;
        }

        String bName = prop.getProperty("browser");

        if (bName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(new ChromeOptions());
        } else if (bName.equalsIgnoreCase("ff")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(TestUtil.page_load_timeout, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(TestUtil.implicit_wait, TimeUnit.SECONDS);
        driver.get(prop.getProperty("url"));
    }


    @AfterMethod
    public void tearDown(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                String screenshotPath = TestUtil.takeScreenshotAtEndOfTest();
                logger.fail("Test Failed. Screenshot saved at: " + screenshotPath);
                logger.addScreenCaptureFromPath(screenshotPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (ITestResult.SUCCESS == result.getStatus()) {
            logger.pass("Test Passed");
        } else if (ITestResult.SKIP == result.getStatus()) {
            logger.skip("Test Skipped");
        }

        if (driver != null) {
            driver.quit();
        }
    }

    private boolean isUITest() {
        return !(this.getClass().getSimpleName().toLowerCase().contains("api"));
    }
}
