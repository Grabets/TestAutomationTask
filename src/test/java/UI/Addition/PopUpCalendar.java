package UI.Addition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PopUpCalendar {

    private WebDriver driver;
    private WebElement calendarElement;
    private WebDriverWait wait;

    public PopUpCalendar(WebElement webElement,WebDriver webDriver) {
        this.calendarElement = webElement;
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 2);
    }

    public PopUpCalendar (WebDriver webDriver){
        this.driver = webDriver;
        wait = new WebDriverWait(driver, 2);
    }

    public void setCheckInDate(int day, String month, String year){
        //Here you can faced with space issue to add non-breaking space please type Alt+0160 for day<10
        String date = month+" "+year;
        wait.until(ExpectedConditions.elementToBeClickable(calendarElement.findElement(By.xpath("//div[contains(@class,'further')]"))));
        getMonthsElements(date);
        if (day < 10)
            calendarElement.findElement(By.xpath("//th[contains(text(),'"+date+"')]/ancestor::div[@class='c2-month']//span[text()=' "+day+"']")).click();
        else
            calendarElement.findElement(By.xpath("//th[contains(text(),'"+date+"')]/ancestor::div[@class='c2-month']//span[text()='"+day+"']")).click();
    }

    public void setCheckOutDate(int day, String month, String year){
        String xPath;
        String date = month+" "+year;

        if (day < 10){
            xPath = "//div[contains(@class,'checkout')]//div[@class='c2-calendar']//th[contains(text(),'"+date+"')]/ancestor::div[@class='c2-month']//span[text()=' "+day+"']";
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
            driver.findElement(By.xpath(xPath)).click();
        }
        else {
            xPath = "//div[contains(@class,'checkout')]//div[@class='c2-calendar']//th[contains(text(),'"+date+"')]/ancestor::div[@class='c2-month']//span[text()='"+day+"']";
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
            driver.findElement(By.xpath(xPath)).click();
        }

    }

    private void swipeRight(){

        calendarElement.findElement(By.xpath("//div[contains(@class,'further')]")).click();
    }

    private void swipeLeft(){
        calendarElement.findElement(By.xpath("//div[contains(@class,'earlier')]")).click();
    }

    private void getMonthsElements(String date){
        WebElement monthElement = calendarElement.findElement(By.xpath("//th[contains(text(),'"+date+"')]/ancestor::div[@class='c2-month']"));
        while (!monthElement.isDisplayed())
            swipeRight();
    }



}
