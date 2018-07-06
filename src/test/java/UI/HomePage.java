package UI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage {

    @FindBy (how = How.XPATH, using = "//input[@id='ss']")
    WebElement searchInput;

    public HomePage() {

    }

    public void setSearchInput(String text) {
        searchInput.sendKeys(text);
    }

    public String getSearchInput() {
        return "";
    }

}
