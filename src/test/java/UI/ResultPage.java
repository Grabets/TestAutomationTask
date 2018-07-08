package UI;

import UI.component.HotelListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ResultPage {

    private static final String FORM_CHECK_IN_DATE_LOCATOR = "//div[@data-placeholder='Check-in Date']";
    private static final String FORM_CHECK_OUT_DATE_LOCATOR = "//div[@data-placeholder='Check-out Date']";
    private static final String FILTER_STAR_LOCATOR = "//*[@id='filter_class']//a[@data-id='class-%s']";
    private static final String LIST_OF_HOTELS_LOCATOR = "//*[@id='hotellist_inner']/div[@data-hotelid]";

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'header__col')]/h1")
    private WebElement headerTextElement;

    @FindBy(how = How.XPATH, using = "//a[@data-category='price']")
    private WebElement lowestPriceFirstElement;

    @FindBy (how = How.XPATH, using = LIST_OF_HOTELS_LOCATOR)
    private List<WebElement> resultList;

    @FindBy (how = How.ID, using = "frm")
    private WebElement searchForm;

    private static WebDriver driver;
    private List<HotelListItem> listOfHotels;

    public void init(WebDriver webDriver) {
        ResultPage.driver = webDriver;
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

    public List<String> cityInResultList(){
        fillListOfHotels(resultList);
        List<String> list = new ArrayList<>();
        for (HotelListItem hotel : listOfHotels){
                list.add(hotel.getCityLinkText());
        }
        return list;
    }


    public void starsEnableFilterClick(String stars){
        String starsFilterLocator = gerFormatStarsToXPath(stars);
        By locator = By.xpath(starsFilterLocator);
        driver.findElement(locator).click();
    }

    public List<String> getStarsList() throws InterruptedException {
        List<String> listOfStars = new ArrayList<>();
        initializeListOfHotels();
        for (HotelListItem hotel: listOfHotels)
            listOfStars.add(hotel.getStarsText());

        return listOfStars;
    }

    public List<Integer> sortedPriceOnLowest() throws InterruptedException {
        lowestPriceFirstElementClick();
        initializeListOfHotels();
        List<Integer> priceList = new ArrayList<>();
        for (HotelListItem hotel : listOfHotels){
            priceList.add(hotel.getPrice());
        }
        return priceList;
    }

    private void initializeListOfHotels() throws InterruptedException {
        By listOfHotelsLocator = By.xpath(LIST_OF_HOTELS_LOCATOR);
        Thread.sleep(10000);
//        By popUpFrameLocator = By.xpath("//iframe[contains(@class,'sr-usp-overlay')]");
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        ExpectedCondition<Boolean> expectedCondition = ExpectedConditions.not(presenceOfElementLocated(popUpFrameLocator));
//        wait.until(expectedCondition);
        List<WebElement> list = driver.findElements(listOfHotelsLocator);
        fillListOfHotels(list);
    }

    private void fillListOfHotels(List<WebElement> list){
        listOfHotels = new ArrayList<>();
        for (WebElement webElement : list)
            listOfHotels.add(new HotelListItem(webElement));
    }

    private void lowestPriceFirstElementClick(){
        lowestPriceFirstElement.click();
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
