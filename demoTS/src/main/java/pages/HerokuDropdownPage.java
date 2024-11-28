package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HerokuDropdownPage {
    private WebDriver driver;
    public HerokuDropdownPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id ="dropdown")
    private WebElement dropdown;

    @FindBy(xpath ="//option[contains(@value, '1')]")
    private WebElement opcion1;

    @FindBy(xpath ="//option[contains(@value, '2')]")
    private WebElement opcion2;


    public void clickDropdown(){
        dropdown.click();
    }
    public void clickOpcion1(){
        opcion1.click();
    }
    public void clickOpcion2(){
        opcion2.click();
    }

}
