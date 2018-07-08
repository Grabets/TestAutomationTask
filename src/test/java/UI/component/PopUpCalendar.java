package UI.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PopUpCalendar {

    private static final String CHECK_IN_DATE_LOCATOR_TEMPLATE = "//th[contains(text(),'%s')]/ancestor::div[@class='c2-month']//span[text()='%s']";
    private static final String MONTH_LOCATOR_TEMPLATE = "//th[contains(text(),'%s')]/ancestor::div[@class='c2-month']";
    private static final String CHECK_OUT_DATE_LOCATOR_TEMPLATE = "//div[contains(@class,'checkout')]//div[@class='c2-calendar']//th[contains(text(),'%s')]/ancestor::div[@class='c2-month']//span[text()='%s']";
    private static final String FURTHER_BUTTON_LOCATOR = "//div[contains(@class,'further')]";

    private WebDriver driver;
    private WebElement calendarElement;
    private WebDriverWait wait;

    public PopUpCalendar(WebElement webElement, WebDriver webDriver) {
        this.calendarElement = webElement;
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 2);
    }

    public PopUpCalendar(WebDriver webDriver) {
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 2);
    }

    public void setCheckInDate(int day, String month, String year) {
        String date = month + " " + year;
        scrollToProperMonth(date);

        By calendarElementLocator = By.xpath(String.format(CHECK_IN_DATE_LOCATOR_TEMPLATE, date, formatDayToXpath(day)));
        WebElement dayInCalendarElement = calendarElement.findElement(calendarElementLocator);
        dayInCalendarElement.click();

    }

    public void setCheckOutDate(int day, String month, String year) {
        String date = month + " " + year;
        By dayCheckOutLocator = By.xpath(String.format(CHECK_OUT_DATE_LOCATOR_TEMPLATE,date, formatDayToXpath(day)));
        ExpectedCondition<WebElement> waitForCalendarFullyInitialized = ExpectedConditions.elementToBeClickable(dayCheckOutLocator);
        wait.until(waitForCalendarFullyInitialized);
        driver.findElement(dayCheckOutLocator).click();
    }

    private void scrollToProperMonth(String date) {
        ExpectedCondition<WebElement> waitForPopupFullyInitialized = ExpectedConditions.elementToBeClickable(calendarElement.findElement(By.xpath(FURTHER_BUTTON_LOCATOR)));
        wait.until(waitForPopupFullyInitialized);
        WebElement monthElement = calendarElement.findElement(By.xpath(String.format(MONTH_LOCATOR_TEMPLATE, date)));
        while (!monthElement.isDisplayed()){
            swipeRight();
        }
    }

    private void swipeRight() {
        calendarElement.findElement(By.xpath(FURTHER_BUTTON_LOCATOR)).click();
    }

    private String formatDayToXpath(int day) {
        return day < 10 ? " " + day : String.valueOf(day);
    }


}
