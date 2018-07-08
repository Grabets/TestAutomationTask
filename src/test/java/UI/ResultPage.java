package UI;

import UI.component.HotelFromList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;

public class ResultPage {

    private static final String FORM_CHECK_IN_DATE_LOCATOR = "//div[@data-placeholder='Check-in Date']";
    private static final String FORM_CHECK_OUT_DATE_LOCATOR = "//div[@data-placeholder='Check-out Date']";

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'header__col')]/h1")
    private WebElement headerTextElement;

    @FindBy (how = How.XPATH, using = "//*[@id='hotellist_inner']/div[@data-hotelid]")
    private List<WebElement> resultList;

    @FindBy (how = How.ID, using = "frm")
    private WebElement searchForm;


    private static WebDriver driver;
    private List<HotelFromList> listOfHotels;

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

//    public List<String> checkCorrectCityInResultList(){
//        By locator;
//        String cityLinkLocator = "//a[@data-map-caption]";
//
//
//        List<String> list = new ArrayList<>();
//        for (WebElement webElement :resultList){
//                locator = By.xpath(cityLinkLocator);
//                list.add(webElement.findElement(locator).getText());
//        }
//        return list;
//    }


    public List<String> checkCorrectCityInResultList(){
        List<String> list = new ArrayList<>();
        for (HotelFromList hotel : listOfHotels){
                list.add(hotel.getCityLinkText());
        }
        return list;
    }

    private void fillListOfHotels(){
        listOfHotels = new ArrayList<>();
        for (WebElement webElement : resultList)
            listOfHotels.add(new HotelFromList(webElement));
    }






}
