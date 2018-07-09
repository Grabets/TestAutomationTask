package Tests;

import Core.TestDataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;
import java.util.logging.Logger;

public abstract class BaseTest {

    private static final Logger TEST_EXECUTION_LOGGER = Logger.getLogger("TestExecutionLogger");
    protected WebDriver driver;
    protected TestDataProvider testDataProvider = new TestDataProvider();

    @BeforeTest
    public void webDriverSetup() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void navigateToHomeScreen() {
        driver.manage().deleteAllCookies();
        driver.get(testDataProvider.getProperty("URL"));
    }

    @BeforeMethod
    public void logTestStart(Method m) {
        TEST_EXECUTION_LOGGER.info("============================================\n" +
                "Test execution started " + m.getName());
    }

    @AfterTest
    public void closeDriver(){
        getDriver().close();
    }

    @AfterMethod
    public void logTestFinish(Method m) {
        TEST_EXECUTION_LOGGER.info("Test execution finished " + m.getName());
    }


    public WebDriver getDriver() {
        return driver;
    }
}
