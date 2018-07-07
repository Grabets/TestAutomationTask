package Tests;

import UI.HomePage;
import UI.ResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ResultPageTest extends BaseTest{

    private HomePageTest homePageTest;
    private WebDriver driver;
    private HomePage homePage;
    private ResultPage resultPage;

    @BeforeMethod
    public void ResultPageTestsetUp()  throws InterruptedException{
        this.driver = super.getDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://booking.com");
        this.homePage = PageFactory.initElements(driver,HomePage.class);
        HomePage.setDriver(driver);
        homePage.setSearchInput("New York").selectFirstSuggestedElements()
                .setCheckInDate(1,"September","2018")
                .setCheckOutDate(30,"September","2018");
        resultPage=  homePage.searchButtonClick();
    }

    @Test
    public void openTest(){
        Assert.assertEquals(resultPage.getTextHeader(), "New York City");
    }

    @Test
    public void correctCheckInTest(){
      Assert.assertTrue(resultPage.isCheckInDateCorrect("September 1, 2018"));
    }

    @Test
    public void correctCheckOutTest(){
        Assert.assertTrue(resultPage.isCheckOutDateCorrect("September 30, 2018"));
    }

    @Test
    public void checkCorrectTownNameTest(){
        String townName = "New York";
        Assert.assertTrue(resultPage.checkCorrectSityInResultList(townName));
    }



}