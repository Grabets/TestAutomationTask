package UI.component;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HotelFromList {

    private static final String CITY_LINK_LOCATOR = "//a[@data-map-caption]";

    private WebDriver driver;

    private WebElement hotelElement;
    private WebElement hotelNameElement;

    private WebElement cityLinkElement;
    private WebElement starsRateElement;
    private WebElement priceElement;

    public HotelFromList(WebElement hotelElementArg) {
        this.hotelElement = hotelElementArg;

        //this.driver = webDriver;
        //this.hotelNameElement = hotelNameElement;

        this.cityLinkElement = hotelElement.findElement(By.xpath(CITY_LINK_LOCATOR));
    }

    public String getCityLinkText() {
        return cityLinkElement.getText();
    }


}
