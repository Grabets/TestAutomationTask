package UI;

import UI.component.PopUpCalendar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private static final String CHECK_IN_ELEMENT_LOCATOR = "//div[contains(@data-calendar2-type,'checkin')]//div[@class='sb-date-field__display']";
    private static final String CALENDAR_LOCATOR = "//div[@class='c2-calendar']";

    @FindBy (how = How.XPATH, using = "//input[@id='ss']")
    private WebElement searchInput;

    @FindBy (how = How.XPATH, using = "//div/ul[1][contains(@class,'c-auto')]")
    private WebElement autoCompleteSearchList;

    @FindBy (how = How.XPATH, using = "//div[@class='xp__dates-inner xp__dates__checkin']")
    private WebElement checkInElement;

    @FindBy (how = How.XPATH, using = "//div[@class='xp__dates-inner xp__dates__checkout']")
    private WebElement checkOutElement;

    @FindBy (how = How.XPATH, using = "//span[text()='Search']/ancestor::button")
    private WebElement searchButton;

    private static WebDriverWait wait;

    private static WebDriver driver;

    public static void init(WebDriver webDriver) {
        HomePage.driver = webDriver;
        HomePage.wait = new WebDriverWait(driver, 5);
    }

    public HomePage setSearchInput(String text) {
        searchInput.sendKeys(text);
        return this;
    }

    public String getSearchInput() {
        return searchInput.getAttribute("value");
    }

    public String getCheckInDate(){
        return driver.findElement(By.xpath(CHECK_IN_ELEMENT_LOCATOR)).getText();
    }

    public HomePage selectFirstSuggestedElement(){
        String firstSuggestedElementLocator = "//li[@data-i='0'][contains(text(),'New')]";
        By locator = By.xpath(firstSuggestedElementLocator);
        ExpectedCondition<WebElement> expectedCondition = ExpectedConditions.elementToBeClickable(locator);
        wait.until(expectedCondition).click();
        return this;
    }

    public HomePage setCheckInDate(int day, String month, String year){
        WebElement checkInCalendar = checkInElement.findElement(By.xpath(CALENDAR_LOCATOR));
        PopUpCalendar calendar = new PopUpCalendar(checkInCalendar,driver);
        calendar.setCheckInDate(day, month,year);
        return this;
    }

    public HomePage setCheckOutDate(int day, String month, String year){
        //TODO: incorect logics
        PopUpCalendar calendar = new PopUpCalendar(driver);
        try {
            calendar.setCheckOutDate(day, month,year);
        }
        catch (org.openqa.selenium.TimeoutException e){
            checkOutElement.click();
            calendar.setCheckOutDate(day, month,year);
        }
        return this;
    }

    public ResultPage searchButtonClick(){
        String resultListLocator = "//*[@id='hotellist_inner']";
        searchButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(resultListLocator)));
        ResultPage resultPage = PageFactory.initElements(driver,ResultPage.class);
        resultPage.init(driver);
        return resultPage;
    }


}
