package Tests;

import Core.TestDataProvider;
import UI.HomePage;
import UI.ResultPage;
import com.google.common.collect.Ordering;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ResultPageTest extends BaseTest {

    private HomePage homePage;
    private ResultPage resultPage;

    private String city;

    private String checkInDay;
    private String checkInMonth;
    private String checkInYear;

    private String checkOutDay;
    private String checkOutMonth;
    private String checkOutYear;

    @BeforeMethod
    public void resultPageTestSetUp(Method m) {
        initTestDataForTestCase(m);
        homePage = PageFactory.initElements(driver, HomePage.class);
        HomePage.init(driver);
        homePage.setSearchInput(city).selectFirstSuggestedElement()
                .setCheckInDate(checkInDay, checkInMonth, checkInYear)
                .setCheckOutDate(checkOutDay, checkOutMonth, checkOutYear);

        resultPage = homePage.searchButtonClick();
    }

    @Test
    public void checkCorrectCheckInOutDateTest() {
        String returnedValuePattern = "%s %s, %s";
        String dayCheckIn = String.format(returnedValuePattern, checkInMonth, checkInDay, checkInYear);
        String dayCheckOut = String.format(returnedValuePattern, checkOutMonth, checkOutDay, checkOutYear);
        Boolean isBothTrue = resultPage.isDateCorrect(dayCheckIn, dayCheckOut);
        Assert.assertTrue(isBothTrue, "Check correctness of both accommodation date");
    }

    @Test
    public void checkCorrectTownNameTest() {
        List<String> listOfCity = resultPage.cityInResultList();
        Boolean isTownNameSameInAllList = listOfCity.stream().allMatch(str -> str.trim().contains(city));
        Assert.assertTrue(isTownNameSameInAllList, "Found a city in hotel list which does not equal request city: " + city);
    }

    @Test
    public void checkFilteringByStarsTest() throws InterruptedException {
        String numberOfStars = testDataProvider.getProperty("numberOfStars");
        resultPage.starsEnableFilterClick(numberOfStars);
        Boolean isStarsCorrect = resultPage.getStarsList().stream()
                .allMatch(str -> str.trim().contains(numberOfStars));
        Assert.assertTrue(isStarsCorrect, "Found a star in hotel list with star rating not equal to applied filter: " + numberOfStars + "-stars");
    }

    @Test
    public void checkOrderingByLowPriceFirstTest() throws InterruptedException {
        List<Integer> listOfPrice = resultPage.sortedPriceOnLowest().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        Boolean isPriceSortCorrect = Ordering.natural().isOrdered(listOfPrice);
        Assert.assertTrue(isPriceSortCorrect, "Checking sorting by lower price first filter correctness in result list of searching");

    }

    private void initTestDataForTestCase(Method m) {
        testDataProvider = new TestDataProvider(m.getName());
        city = testDataProvider.getProperty("location.name");

        checkInDay = testDataProvider.getProperty("checkin.day");
        checkInMonth = testDataProvider.getProperty("checkin.month");
        checkInYear = testDataProvider.getProperty("checkin.year");

        checkOutDay = testDataProvider.getProperty("checkout.day");
        checkOutMonth = testDataProvider.getProperty("checkout.month");
        checkOutYear = testDataProvider.getProperty("checkout.year");
    }
}
