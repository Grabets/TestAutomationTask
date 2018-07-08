package UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;

public class ResultPage {

    private static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        ResultPage.driver = webDriver;
    }

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'header__col')]/h1")
    WebElement headerTextElement;

    @FindBy (how = How.XPATH, using = "//*[@id='hotellist_inner']/div")
    List<WebElement> resultList;

    @FindBy (how = How.ID, using = "frm")
    WebElement searchForm;

    public String getTextHeader(){
        return headerTextElement.getText();
    }

    public String getCheckInDate(){
        return searchForm.findElement(By.xpath("//div[@data-placeholder='Check-in Date']")).getText();
    }

    public String getCheckOutDate(){
        return searchForm.findElement(By.xpath("//div[@data-placeholder='Check-out Date']")).getText();
    }

    public boolean isCheckInDateCorrect(String checkInDate){
        return getCheckInDate().contains(checkInDate);
    }

    public boolean isCheckOutDateCorrect(String checkOutDate){
        return getCheckOutDate().contains(checkOutDate);
    }


    public boolean checkCorrectCityInResultList(String correctTown){
        String text;
        for (int i = 1; i < resultList.size()+1 ; i++) {
            try {
                text = driver.findElement(By.xpath("//*[@id='hotellist_inner']/div["+i+"]/div[2]/div[1]/div[1]/div[1]/a[2]")).getText();
                if (!text.contains(correctTown))
                    System.out.println("Incorrect town: "+text);
                    return false;
            }
            catch (NoSuchElementException e){
                System.out.println("NoSuchElementException: i="+i);
            }
        }
        return true;
    }


}
