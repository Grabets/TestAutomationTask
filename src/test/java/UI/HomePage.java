package UI;

import UI.Addition.PopUpCalendar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    @FindBy (how = How.XPATH, using = "//input[@id='ss']")
    WebElement searchInput;
    @FindBy (how = How.XPATH, using = "//div/ul[1][contains(@class,'c-auto')]")
    WebElement autoCompleteSearchList;
    @FindBy (how = How.XPATH, using = "//div[@class='xp__dates-inner xp__dates__checkin']")
    WebElement checkInElement;
    @FindBy (how = How.XPATH, using = "//div[@class='xp__dates-inner xp__dates__checkout']")
    WebElement checkOutElement;

    @FindBy (how = How.XPATH, using = "//span[text()='Search']/ancestor::button")
    WebElement searchButton;


    private static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        HomePage.driver = webDriver;
    }

    public HomePage setSearchInput(String text) {
        searchInput.sendKeys(text);
        return this;
    }

    public String getSearchInput() {
        return searchInput.getAttribute("value");
    }

    public String getCheckInDate(){
        return driver.findElement(By.xpath("//div[contains(@data-calendar2-type,'checkin')]//div[@class='sb-date-field__display']")).getText();
    }

    public HomePage selectFirstSuggestedElements(){
        WebDriverWait wait = new WebDriverWait(driver, 1);
        autoCompleteSearchList = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@data-i='0'][contains(text(),'New')]")));
        autoCompleteSearchList.findElement(By.xpath("//li[@data-i='0'][contains(text(),'New')]")).click();
        return this;
    }

    public HomePage setCheckInDate(int day, String month, String year){
        //checkInElement.click();
        WebElement checkInCalendar = checkInElement.findElement(By.xpath("//div[@class='c2-calendar']"));
        PopUpCalendar calendar = new PopUpCalendar(checkInCalendar,driver);
        calendar.setCheckInDate(day, month,year);
        return this;
    }

    public HomePage setCheckOutDate(int day, String month, String year) throws InterruptedException{
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
        searchButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='hotellist_inner']")));
        ResultPage resultPage = PageFactory.initElements(driver,ResultPage.class);
        resultPage.setDriver(driver);
        return resultPage;
    }


}
