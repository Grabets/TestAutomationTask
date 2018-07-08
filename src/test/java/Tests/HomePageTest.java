package Tests;

import UI.HomePage;
import UI.ResultPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
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
        HomePage.init(driver);
    }

    @Test
    public void searchTownForBookingTest(){
        String town = "New York";
        homePage.setSearchInput(town).selectFirstSuggestedElement();
        Assert.assertEquals(homePage.getSearchInput(),"New York, New York State, USA");
    }

    @Test
    public void setSimpleCheckInOutDateTest() throws InterruptedException{
        homePage.setCheckInDate(1,"September", "2018");
        homePage.setCheckOutDate(30,"September", "2018");

    }

    @Test
    public void searchTest() throws InterruptedException{
        int startDay = 1;
        int finishDay = 30;
        String month = "September";
        int year = 2018;
        ResultPage resultPage = homePage.setSearchInput("New York").selectFirstSuggestedElement()
            .setCheckInDate(startDay,month,String.valueOf(year))
                .setCheckOutDate(finishDay,month,String.valueOf(year))
                    .searchButtonClick();

        Assert.assertEquals(resultPage.getTextHeader(), "New York City");
    }

    @Test
    public void checkIncorrectSearchTest(){

        homePage.setSearchInput("@^^^").searchButtonClick();

    }








}
