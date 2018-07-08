package Tests;

import UI.HomePage;
import UI.ResultPage;
import com.google.common.collect.Ordering;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ResultPageTest extends BaseTest{

    private WebDriver driver;
    private HomePage homePage;
    private ResultPage resultPage;

    @BeforeMethod
    public void ResultPageTestSetUp(){
        this.driver = super.getDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://booking.com");
        this.homePage = PageFactory.initElements(driver,HomePage.class);
        HomePage.init(driver);
        homePage.setSearchInput("New York").selectFirstSuggestedElement()
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
        List<String> listOfCity = resultPage.cityInResultList();
        Boolean isTownNameSameInAllList = listOfCity.stream().allMatch(str ->str.trim().contains(townName));
        Assert.assertTrue(isTownNameSameInAllList);
    }

    @Test
    public void clickOnStartFilterTest() throws InterruptedException {
        String numberOfStars = "5";
        resultPage.starsEnableFilterClick(numberOfStars);
        Boolean isStarsCorrect = resultPage.getStarsList().stream()
                .allMatch(str->str.trim().contains(numberOfStars));
        Assert.assertTrue(isStarsCorrect);
    }

    @Test
    public void clickOnLowestPriceFirstFilter() throws InterruptedException {

        List<Integer> listOfPrice = resultPage.sortedPriceOnLowest().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Boolean isPriceSortCorrect = Ordering.natural().isOrdered(listOfPrice);
        Assert.assertTrue(isPriceSortCorrect);

    }



}
