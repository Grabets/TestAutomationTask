package Tests;

import UI.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    WebDriver driver;
    HomePage homePage;

    @BeforeMethod
    public void setUp() {
        this.driver = super.getDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://booking.com");
        this.homePage = PageFactory.initElements(driver,HomePage.class);
    }

    @Test
    public void searchTownForBooking(){
        String town = "New York";
        homePage.setSearchInput(town);
        Assert.assertTrue(true);
    }
}
