package UI.component;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class HotelListItem {

    private static final String CITY_LINK_LOCATOR = ".//a[@data-map-caption]";
    private static final String HOTEL_PRICE_LOCATOR = ".//strong[contains(@class, 'price')]";
    private static final String HOTEL_STAR_LOCATOR = ".//i[contains(@class,'star_track')]/span";

    private WebElement hotelElement;
    private WebElement cityLinkElement;
    private WebElement starsRateElement;
    private WebElement priceElement;

    public HotelListItem(WebElement hotelElementArg) {
        this.hotelElement = hotelElementArg;
        this.priceElement = getElementIfExists(HOTEL_PRICE_LOCATOR);
        this.cityLinkElement = getElementIfExists(CITY_LINK_LOCATOR);
        this.starsRateElement = getElementIfExists(HOTEL_STAR_LOCATOR);
    }

    public String getCityLinkText() {
        return isElementPresent(cityLinkElement) ? cityLinkElement.getText() : "";
    }

    public String getPriceText() {
        return isElementPresent(priceElement) ? priceElement.getText() : "";
    }

    public Integer getPrice() {
        if (!isElementPresent(priceElement)) {
            return null;
        }
        String intValue = getPriceText().replaceAll("[^0-9]", "");
        return Integer.parseInt(intValue);
    }

    public String getStarsText(){
        return isElementPresent(starsRateElement)? starsRateElement.getText() : "";
    }

    private WebElement getElementIfExists(String locator) {
        WebElement result = null;
        try {
            result = hotelElement.findElement(By.xpath(locator));
        } catch (NoSuchElementException e) {
            //TODO; Write to log
        }
        return result;
    }

    private boolean isElementPresent(WebElement element) {
        return element != null;
    }
}
