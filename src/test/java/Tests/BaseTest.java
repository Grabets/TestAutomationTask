package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest {

    private WebDriver driver;

    @BeforeClass
    public void webDriverSetup(){
        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();

    }

    @AfterTest
    public void closeDriver() throws Exception{
        Thread.sleep(3000);
        getDriver().close();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
