package UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

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


    public boolean checkCorrectSityInResultList(String correctTown){
        System.out.println(resultList.size());
        System.out.println(driver.findElement(By.xpath("//*[@id='hotellist_inner']/div[1]//div[@class='address']/a[2]")).getText());



        //System.out.println(resultList.get(0).findElement(By.xpath("//div[@class='address']")).getText());
//        for (WebElement webElement : resultList) {
//            String text = webElement.findElement(By.xpath("//div[@class='address']/a[2]")).getText();
//            System.out.println(text);
//            if (!text.contains(correctTown))
//                return false;
//        }
        return true;
    }


}
