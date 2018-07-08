package UI;

import UI.component.HotelListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ResultPage {

    private static final String FORM_CHECK_IN_DATE_LOCATOR = "//div[@data-placeholder='Check-in Date']";
    private static final String FORM_CHECK_OUT_DATE_LOCATOR = "//div[@data-placeholder='Check-out Date']";
    private static final String FILTER_STAR_LOCATOR = "//*[@id='filter_class']//a[@data-id='class-%s']";

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'header__col')]/h1")
    private WebElement headerTextElement;

    @FindBy (how = How.XPATH, using = "//*[@id='hotellist_inner']/div[@data-hotelid]")
    private List<WebElement> resultList;

    @FindBy (how = How.ID, using = "frm")
    private WebElement searchForm;


    private static WebDriver driver;
    private List<HotelListItem> listOfHotels;

    public void init(WebDriver webDriver) {
        ResultPage.driver = webDriver;
        fillListOfHotels();
    }


    public String getTextHeader(){
        return headerTextElement.getText();
    }

    public String getCheckInDate(){
        return searchForm.findElement(By.xpath(FORM_CHECK_IN_DATE_LOCATOR)).getText();
    }

    public String getCheckOutDate(){
        return searchForm.findElement(By.xpath(FORM_CHECK_OUT_DATE_LOCATOR)).getText();
    }

    public boolean isCheckInDateCorrect(String checkInDate){
        return getCheckInDate().contains(checkInDate);
    }

    public boolean isCheckOutDateCorrect(String checkOutDate){
        return getCheckOutDate().contains(checkOutDate);
    }

    public List<String> checkCorrectCityInResultList(){
        List<String> list = new ArrayList<>();
        for (HotelListItem hotel : listOfHotels){
                list.add(hotel.getCityLinkText());
        }
        return list;
    }
    //TODO:doesnt work
    public List<Integer> checkPriceInResultList(){
        List<Integer> priceList = new ArrayList<>();
        for (HotelListItem hotel : listOfHotels){
            priceList.add(hotel.getPrice());
        }
        System.out.println(Arrays.toString(priceList.toArray()));
        return priceList;
    }

    public void filterStarsEnableClick(String stars){
        String starsFilterLocator = gerFormatStarsToXPath(stars);
        By locator = By.xpath(starsFilterLocator);
        driver.findElement(locator).click();
    }

    private void fillListOfHotels(){
        listOfHotels = new ArrayList<>();
        for (WebElement webElement : resultList)
            listOfHotels.add(new HotelListItem(webElement));
    }

    private String gerFormatStarsToXPath(String numOfStars){
        return String.format(FILTER_STAR_LOCATOR,numOfStars);
    }
    //ONLY FOR TEST
    public void printPrice(){
      listOfHotels.stream()
              .map(hotelListItem -> hotelListItem.getPrice())
              .filter(Objects::nonNull)
              .forEach(price -> System.out.println(price));
    }







}
